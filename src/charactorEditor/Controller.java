package charactorEditor;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import charactorEditor.drag.AttributePane;
import charactorEditor.drag.FighterBuilder;
import charactorEditor.drag.MyComponentPanel;
import charactorEditor.drag.Component.AddImgButton;
import charactorEditor.Model.Model;

public class Controller implements MouseListener, MouseMotionListener {
	FighterBuilder myFighterBuilder;
	Model myModel;
	MyComponentPanel myComponentPanel;
	AttributePane myAttributePane;
	AddImgButton myAddImgButton;
	private Object message;
	private static Controller instance;

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
	}

	public void register(Model m) {
		myModel = m;
	}

	public void getMessage(Object msg, ActionEvent e) {
		message = msg;
		if (e.getSource() == myAddImgButton) {
			myModel.focusCMP.img = (File) message;
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
			myModel.focusCMP = null;
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