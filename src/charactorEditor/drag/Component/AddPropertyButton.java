package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import charactorEditor.drag.AttributePane;

public class AddPropertyButton extends JButton{
	private AttributePane outer;
	public AddPropertyButton(AttributePane e){
		super("add");
		outer=e;
		setBounds(0, 103, 55, 21);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = (String) outer.cbo_1.getSelectedItem();
				String value = JOptionPane.showInputDialog("input "
						+ "the value of " + key);
				if (value != null) {
					if (value.equalsIgnoreCase(""))
						outer.outerFighterBuilder.focusCMP.remove(key);
					else
						outer.outerFighterBuilder.focusCMP.setProperty(key, value);
				}
				outer.update();
			}
		});
		outer.add(this);
	}
	public void update() {
		setEnabled(outer.outerFighterBuilder.focusCMP != null);
	}
}