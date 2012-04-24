package charactorEditor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import charactorEditor.drag.AttributePane;
import charactorEditor.drag.FighterBuilder;
import charactorEditor.drag.MainPane;
import charactorEditor.drag.MyComponentPanel;
import charactorEditor.drag.Component.AddImgButton;
import charactorEditor.drag.Component.AddPropertyButton;
import charactorEditor.drag.Component.LoadButton;
import charactorEditor.states.MouseDraggedState;
import charactorEditor.states.MouseReleasedState;
import charactorEditor.states.State;
import charactorEditor.Model.Loader;
import charactorEditor.Model.MainPaneModel;
import charactorEditor.Model.Model;
import charactorEditor.Model.MyComponent;

public class Controller implements MouseListener, MouseMotionListener,
		KeyListener {
	private FighterBuilder myFighterBuilder;
	private Model myModel;
	private MyComponentPanel myComponentPanel;
	private AttributePane myAttributePane;
	private MainPane myMainPane;
	private AddImgButton myAddImgButton;
	private AddPropertyButton myAddPropertyButton;
	private LoadButton myLoadButton;
	private Object message;
	private MainPaneModel myMainPaneModel;
	private static Controller instance;
	private State state;

	private JMenuItem myConnect;

	public static Controller Instance() {
		if (instance == null)
			instance = new Controller();
		return instance;
	}

	private Controller() {

	}

	public void register(FighterBuilder f) {
		myFighterBuilder = f;
		myMainPane = myFighterBuilder.getDrawPane();
		myComponentPanel = myFighterBuilder.getComponentPanel();
		myAttributePane = myFighterBuilder.getAttributePane();
		myAddImgButton = myAttributePane.myAddImgButton;
		myAddPropertyButton = myAttributePane.myAddPropertyButton;
		myLoadButton = myAttributePane.myLoadButton;
		myConnect = myMainPane.getItem();
		myMainPaneModel = MainPaneModel.Instance();

	}

	public void register(JMenuItem toUpdate) {
		myConnect = toUpdate;
	}

	public void updateFigherBuilder() {
		myFighterBuilder.repaint();
	}

	public void register(Model m) {
		myModel = m;
	}

	public MyComponent getFoucsedComponent() {
		return myMainPaneModel.getFoucsComponent();
	}

	public int getWillPut() {
		return myModel.getWillPut();
	}

	public void getMessage(Object msg, ActionEvent e) {
		message = msg;
		if (e.getSource() == myAddImgButton) {
			myMainPaneModel.getFoucsComponent().setImg((File) message);
			return;
		}
		if (e.getSource() == myAddPropertyButton) {
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) message;
			String key = list.get(0);
			String value = list.get(1);
			if (value != null) {
				if (value.equalsIgnoreCase(""))
					myMainPaneModel.getFoucsComponent().remove(key);
				else
					myMainPaneModel.getFoucsComponent().setProperty(key, value);
			}
			return;
		}
		if (e.getSource() == myLoadButton) {
			try {
				myModel.load(Loader.load(message.toString()));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			return;
		}
		if (e.getSource() == myConnect) {
			myMainPaneModel.getFoucsComponent().addChild(
					myMainPaneModel.getNextFocusComponent());
			myMainPaneModel.getNextFocusComponent().setParent(
					myMainPaneModel.getFoucsComponent());
			return;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == myMainPane) {
			state = MouseDraggedState.Instance(myMainPaneModel, e);
			action();
		}
	}

	private void action() {
		state.creat();
		state = state.getState();
		try {
			state.action();
		} catch (java.lang.NullPointerException ecp) {
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getSource() == myComponentPanel) {
			if (myComponentPanel.find(e.getPoint()) != -1) {
				myFighterBuilder.cross();
			} else {
				myFighterBuilder.deletecross();
			}
			return;
		}
		if (e.getSource() == myMainPane) {
			if (myModel.findComponent(e.getPoint()) != null) {
				if (myModel.getSetSizeFlag() == true) {
					myFighterBuilder.changesize();
				} else {
					myFighterBuilder.cross();
				}
			} else {
				myFighterBuilder.deletecross();
			}
			return;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == myMainPane) {
			if (e.getButton() == MouseEvent.BUTTON3
					&& myMainPaneModel.getFoucsComponent() != null
					&& myMainPaneModel.getNextFocusComponent() != null) {
				myMainPane.addMenu(e);
			}
			return;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == myComponentPanel) {
			myModel.setWillPut(myComponentPanel.find(e.getPoint()));
			myMainPaneModel.resetFoucsComponent();
			myAttributePane.update();
			myFighterBuilder.repaint();
			return;
		}
		if (e.getSource() == myMainPane) {
			if (e.getButton() == 1) {
				int count = e.getClickCount();
				if (!myModel.isPuttingComponent()) {
					if ((myMainPaneModel.setDragingSize(myModel.findComponent(e
							.getPoint()))) != null) {
						if (count < 2) {
							if (myModel.getSetSizeFlag() == true)// 边缘拖拽
							{
								myMainPaneModel
										.setFoucsComponent(myMainPaneModel
												.getDragingSize());
							} else// 点在中间了
							{
								if (myModel.isNext()) {
									myMainPaneModel
											.setNextFocusComponnet(myMainPaneModel
													.getDragingSize());
								} else {
									myMainPaneModel
											.setFoucsComponent(myMainPaneModel
													.getDragingSize());
								}
								myMainPaneModel.setDraging(myMainPaneModel
										.getDragingSize());
								myMainPaneModel.setDragingSize(null);

							}
						} else// 两下，取消
						{
							if (myMainPaneModel
									.getSelectedComponnet()
									.contains(
											myMainPaneModel.getFoucsComponent())) {
								int toRemove = myMainPaneModel
										.getSelectedComponnet().size();
								for (int i = 0; i < toRemove; i++) {
									myMainPaneModel.removeCMP(myMainPaneModel
											.getSelectedComponnet().get(0));
								}
							} else {
								myMainPaneModel.removeCMP(myMainPaneModel
										.getFoucsComponent());
							}
						}
						myAttributePane.update();
					}

					else// 没选上component 就啥也不干/////////////////////////////
						// 拉框框啊！！
					{
						myMainPaneModel.setPutFrame(e.getPoint());
						myMainPaneModel.creatSelectingRectangle();
						myMainPaneModel.resetFoucsComponent();
						myMainPaneModel.resetNextFoucsComponent();
						updateFigherBuilder();
					}
				} else// 第一次放置进来才走这里
				{

					myMainPaneModel.setPutFrame(e.getPoint());
					myMainPaneModel.getNearestPoint();
					myMainPaneModel
							.setFoucsComponent(new MyComponent(myMainPaneModel
									.getNearest(), myModel.getWillPut()));
					myModel.getComponentList().add(
							myMainPaneModel.getFoucsComponent());

					myMainPaneModel.getFoucsComponent()
							.setText(
									myModel.getName(myMainPaneModel
											.getFoucsComponent()));
					myModel.reSetWillPut();
					myAttributePane.update();
					updateFigherBuilder();
				}
			}
			return;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == myMainPane) {
			state = MouseReleasedState.Instance(myMainPaneModel, e);
			action();
			return;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == myMainPane) {
			if (e.isControlDown()) {
				myModel.trueNext();
			}
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == myMainPane) {
			myModel.falseNext();
			return;
		}
	}

	public boolean isComponentListEmpty() {
		return myModel.getComponentList().isEmpty();
	}

	public ArrayList<MyComponent> getComponentList() {
		return myModel.getComponentList();
	}

}