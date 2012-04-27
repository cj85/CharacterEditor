package charactorEditor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import SpriteTree.LimbNode;

import charactorEditor.drag.AttributePane;
import charactorEditor.drag.FighterBuilder;
import charactorEditor.drag.MainPane;
import charactorEditor.drag.MyComponentPanel;
import charactorEditor.drag.Component.AddImgButton;
import charactorEditor.drag.Component.AddPropertyButton;
import charactorEditor.drag.Component.LoadButton;
import charactorEditor.states.State;
import charactorEditor.states.Dragged.MouseDraggedState;
import charactorEditor.states.Moved.MouseMovedState;
import charactorEditor.states.Pressed.MousePressedState;
import charactorEditor.states.Released.MouseReleasedState;
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

	public void updateAttributPane() {
		myAttributePane.update();
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

	public ArrayList<String> getProperties() {
		return myModel.getProperties();
	}

	public ArrayList<MyComponent> getMyComponentToSave() {
		ArrayList<MyComponent> toReturn = new ArrayList<MyComponent>();
		for (MyComponent m : myModel.getComponentList()) {
			toReturn.add(new MyComponent(m));
		}
		for (MyComponent m : toReturn) {
			m.resetParent();
			m.clearChildern();
		}
		return toReturn;
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
		state.create();
		state = state.getState();
		state.action();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		state = MouseMovedState.Instance(myFighterBuilder, e);
		action();
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
				state = MousePressedState.Instance(myMainPaneModel, myModel, e);
				action();
				myAttributePane.update();
				updateFigherBuilder();
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

	public boolean noSelectingRectangle() {
		return myMainPaneModel.noSelectingRectangle();
	}

	public Rectangle2D getSelectingRectangle() {
		return myMainPaneModel.getSelectingRectangle();
	}

	public ArrayList<MyComponent> getSelectedComponnet() {
		return myMainPaneModel.getSelectedComponnet();
	}

	public MyComponent getNextFocusComponent() {
		return myMainPaneModel.getNextFocusComponent();
	}

	public LimbNode getLimbNodeTree() {
		ArrayList<MyComponent> list = myModel.getComponentList();
		// HashMap<String,MyComponent> map=new HashMap<String,MyComponent>();
		// for(MyComponent m:list)
		// map.put(m.getText(), m);
		//
		MyComponent root = null;
		for (MyComponent m : list)
			if (m.isRoot())
				root = m;
    return myModel.buildLimbNodeTree(root, null);
	}

}