package charactorEditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import charactorEditor.drag.AttributePane;
import charactorEditor.drag.FighterBuilder;
import charactorEditor.drag.MyComponentPanel;
import charactorEditor.Model.Model;

public class Controller implements MouseListener, MouseMotionListener {
	FighterBuilder myFighterBuilder;
	Model myModel;
	MyComponentPanel myComponentPanel;
	AttributePane myAttributePane;

	public Controller(Model m) {
		myModel = m;
	}

	public void register(FighterBuilder f) {
		myFighterBuilder = f;
		myComponentPanel = myFighterBuilder.myComponentPanel;
		myAttributePane=myFighterBuilder.attributePane;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
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