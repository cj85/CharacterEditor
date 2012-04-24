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
			}

			@Override
			public void focusLost(FocusEvent e) {
				update();
			}
		});
		outer.add(this);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					myController.getFoucsedComponent().setText(getText());
					myController.updateFigherBuilder();
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
		if (myController.getFoucsedComponent() != null)
			setText(myController.getFoucsedComponent().getText());
		else
			setText("");
	}

}
