package charactorEditor;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import charactorEditor.drag.AttributePane;
import charactorEditor.drag.FighterBuilder;
import charactorEditor.drag.MainPane;
import charactorEditor.drag.MainPaneModel;
import charactorEditor.drag.MyComponent;
import charactorEditor.drag.MyComponentPanel;
import charactorEditor.drag.Component.AddImgButton;
import charactorEditor.drag.Component.AddPropertyButton;
import charactorEditor.drag.Component.LoadButton;
import charactorEditor.Model.Loader;
import charactorEditor.Model.Model;

public class Controller implements MouseListener, MouseMotionListener {
	FighterBuilder myFighterBuilder;
	Model myModel;
	MyComponentPanel myComponentPanel;
	AttributePane myAttributePane;
	private MainPane myMainPane;
	private AddImgButton myAddImgButton;
	private AddPropertyButton myAddPropertyButton;
	private LoadButton myLoadButton;
	private Object message;
	private MainPaneModel myMainPaneModel;
	private static Controller instance;

	JMenuItem myConnect;

	public static Controller Instance() {
		if (instance == null)
			instance = new Controller();
		return instance;
	}

	private Controller() {

	}

	public void register(FighterBuilder f) {
		myFighterBuilder = f;
		myMainPane = myFighterBuilder.drawPane;
		myComponentPanel = myFighterBuilder.myComponentPanel;
		myAttributePane = myFighterBuilder.attributePane;
		myAddImgButton = myAttributePane.myAddImgButton;
		myAddPropertyButton = myAttributePane.myAddPropertyButton;
		myLoadButton = myAttributePane.myLoadButton;
		myConnect = myMainPane.mConnect;
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
		// TODO Auto-generated method stub
		if(e.getSource()==myMainPane){
			if(myMainPaneModel.isDraging()){
				myMainPaneModel.dragingComponent(e.getPoint());
			}
			if(myMainPaneModel.isDragingSize()){
				myMainPaneModel.dragSize(e.getPoint());
			}
			if(myMainPaneModel.isdragRectangle()){
				myMainPaneModel.dragRectangle(e.getPoint());
			}
			return;
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
			myModel.willPut = myComponentPanel.find(e.getPoint());
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
							if (myModel.setSizeFlag == true)// 边缘拖拽
							{
								myMainPaneModel
										.setFoucsComponent(myMainPaneModel
												.getDragingSize());
							} else// 点在中间了
							{
								if (myModel.next) {
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
						myMainPane.update();
					}

					else// 没选上component 就啥也不干/////////////////////////////
						// 拉框框啊！！
					{
						Point2D p = e.getPoint();
						myMainPaneModel.setPutFrame(p);
						myMainPaneModel.creatSelectingRectangle();
						myMainPaneModel.resetFoucsComponent();
						myMainPaneModel.resetNextFoucsComponent();
						updateFigherBuilder();
					}
				} else// 第一次放置进来才走这里
				{
					Point2D p = e.getPoint();
					myMainPaneModel.setPutFrame(p);
					myMainPaneModel.getNearestPoint();
					myMainPaneModel.setFoucsComponent(new MyComponent(
							myMainPaneModel.getNearest(), myModel.willPut));
					myModel.getComponnetList().add(
							myMainPaneModel.getFoucsComponent());
					myMainPaneModel.getFoucsComponent()
							.setText(
									myModel.getName(myMainPaneModel
											.getFoucsComponent()));
					myModel.willPut = -1;
					myAttributePane.update();
					updateFigherBuilder();
				}
			}
			return;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == myMainPane) {
			if (myMainPaneModel.isDragingComponent()) {
				myMainPaneModel.setComponents();
			}
			if (myMainPaneModel.isDragSize()) {
				myMainPaneModel.setSize(e.getPoint());
			}
			if (myMainPaneModel.isSelectingComponent()) {
				myMainPaneModel.selectComponent(myMainPaneModel
						.getSelectingRectangle());
			}
			myMainPaneModel.clear();
			return;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}