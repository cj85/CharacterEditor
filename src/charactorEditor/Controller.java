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
	private static Controller instance;
   public  MyComponent focusCMP = null;
	public MyComponent next_focusCMP = null;
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

	public void getMessage(Object msg, ActionEvent e) {
		message = msg;
		if (e.getSource() == myAddImgButton) {
			focusCMP.img = (File) message;
			return;
		}
		if (e.getSource() == myAddPropertyButton) {
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) message;
			String key = list.get(0);
			String value = list.get(1);
			if (value != null) {
				if (value.equalsIgnoreCase(""))
					focusCMP.remove(key);
				else
					focusCMP.setProperty(key, value);
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
			focusCMP.addChild(next_focusCMP);
			next_focusCMP.setParent(focusCMP);
			return;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

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
			if (e.getButton() == MouseEvent.BUTTON3 && focusCMP != null
					&& next_focusCMP != null) {
				myMainPane.addMenu(e);
			}
			return;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == myComponentPanel) {
			myModel.willPut = myComponentPanel.find(e.getPoint());
			focusCMP = null;
			myAttributePane.update();
			myFighterBuilder.repaint();
			return;
		}if(e.getSource()==myMainPane){
			if (e.getButton() == 1) {
				int count = e.getClickCount();
				if (myModel.willPut == -1) {
					if ((myMainPane.setDragingSize( myModel.findComponent(e.getPoint())) ) != null) {
						if (count < 2) {
							if (myModel.setSizeFlag == true)// 边缘拖拽
							{
								focusCMP =myMainPane.getDragingSize();
							} else// 点在中间了
							{
								if (myModel.next) {
									next_focusCMP =myMainPane.getDragingSize();
								} else {
							        focusCMP = myMainPane.getDragingSize();
								}
								myMainPane.setDraging(myMainPane.getDragingSize()) ;
								myMainPane.setDragingSize(null);

							}
						} else// 两下，取消
						{
							if (myMainPane.mySelectedComponent.contains(focusCMP)) {
								int toRemove = myMainPane.mySelectedComponent.size();
								for (int i = 0; i < toRemove; i++) {
									myMainPane.removeCMP(myMainPane.mySelectedComponent.get(0));
								}
							} else {
								myMainPane.removeCMP(focusCMP);
							}
						}
						myMainPane.update();
					}

					else// 没选上component 就啥也不干/////////////////////////////
					{
						Point2D p = e.getPoint();
						myMainPane.put.setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
						myMainPane.mySelectingRectangle = new Rectangle2D.Double();
						myMainPane.	mySelectingRectangle.setFrame(myMainPane.put);
						focusCMP = null;
						next_focusCMP = null;
						updateFigherBuilder();
					}
				} else// 第一次放置进来才走这里
				{
					Point2D p = e.getPoint();
					myMainPane.put.setFrame(p.getX(), p.getY(), 10, 10);
					myMainPane.getNearestPoint();
					myModel.getComponnetList().add(
							(focusCMP = new MyComponent(myMainPane.nearest,
									myModel.willPut)));
					focusCMP.setText(myModel
							.getName(focusCMP));
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