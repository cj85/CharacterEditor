package charactorEditor.drag.Component;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import charactorEditor.drag.AttributePane;

public class SetMyComponentTextField extends JTextField {
	private AttributePane outer;

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

	}

	public void update() {
		if (outer.outerFighterBuilder.focusCMP != null)
			setText(outer.outerFighterBuilder.focusCMP.text);
		else
			setText("");
		outer.outerFighterBuilder.setText(outer.outerFighterBuilder.focusCMP,
				getText());
	}

}
