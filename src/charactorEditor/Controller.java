package charactorEditor;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import charactorEditor.drag.AttributePane;
import charactorEditor.drag.FighterBuilder;
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
	private AddImgButton myAddImgButton;
	private AddPropertyButton myAddPropertyButton;
	private LoadButton myLoadButton;
	private Object message;
	private static Controller instance;
	public  MyComponent focusCMP = null;
	public  MyComponent next_focusCMP = null;

	public static Controller Instance() {
		if (instance == null)
			instance = new Controller();
		return instance;
	}

	private Controller() {

	}

	public void register(FighterBuilder f) {
		myFighterBuilder = f;
		myComponentPanel = myFighterBuilder.myComponentPanel;
		myAttributePane = myFighterBuilder.attributePane;
		myAddImgButton = myAttributePane.myAddImgButton;
		myAddPropertyButton = myAttributePane.myAddPropertyButton;
		myLoadButton = myAttributePane.myLoadButton;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == myComponentPanel) {
			myModel.willPut = myComponentPanel.find(e.getPoint());
			focusCMP = null;
			myAttributePane.update();
			myFighterBuilder.repaint();
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