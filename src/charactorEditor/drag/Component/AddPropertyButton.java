package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import charactorEditor.Controller;
import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class AddPropertyButton extends JButton {
	private AttributePane outer;
	private Controller myController = Controller.Instance();
	private ArrayList<String> msg = new ArrayList<String>();

	public AddPropertyButton(AttributePane e) {
		super("add");
		outer = e;
		setBounds(0, 103, 55, 21);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msg.clear();
				String key = (String) outer.myPropertySelectCombo
						.getSelectedItem();
				String value = JOptionPane.showInputDialog("input "
						+ "the value of " + key);
				msg.add(key);
				msg.add(value);
				myController.getMessage(msg, e);
				outer.update();
			}
		});
		outer.add(this);
	}

	public void update() {
		setEnabled(myController.focusCMP != null);
	}
}