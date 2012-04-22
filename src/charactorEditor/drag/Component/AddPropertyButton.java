package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class AddPropertyButton extends JButton {
	private AttributePane outer;

	public AddPropertyButton(AttributePane e) {
		super("add");
		outer = e;
		setBounds(0, 103, 55, 21);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = (String) outer.myPropertySelectCombo
						.getSelectedItem();
				String value = JOptionPane.showInputDialog("input "
						+ "the value of " + key);
				if (value != null) {
					if (value.equalsIgnoreCase(""))
						outer.myModel.focusCMP.remove(key);
					else
						outer.myModel.focusCMP.setProperty(key,
								value);
				}
				outer.update();
			}
		});
		outer.add(this);
	}

	public void update() {
		setEnabled(outer.myModel.focusCMP != null);
	}
}