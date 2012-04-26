package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import charactorEditor.Controller;
import charactorEditor.drag.AttributePane;
import charactorEditor.drag.Update;

@SuppressWarnings("serial")
public class AddPropertyButton extends JButton implements Update {
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
				updateOther();
			}
		});
		outer.register(this);
		outer.add(this);
	}

	public void updateOther() {
		outer.update();
	}

	public void update() {
		setEnabled(myController.getFoucsedComponent() != null);
	}
}