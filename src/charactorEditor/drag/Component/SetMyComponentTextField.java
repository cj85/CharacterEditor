package charactorEditor.drag.Component;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import charactorEditor.Controller;
import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class SetMyComponentTextField extends JTextField {
	private AttributePane outer;
	Controller myController = Controller.Instance();

	public SetMyComponentTextField(AttributePane e) {
		super();
		outer = e;
		setBounds(55, 75, 114, 23);
		addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				update();
			}
		});
		outer.add(this);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					myController.focusCMP.text = getText();
					outer.outerFighterBuilder.repaint();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
	}

	public void update() {
		if (myController.focusCMP != null)
			setText(myController.focusCMP.text);
		else
			setText("");
	}

}
