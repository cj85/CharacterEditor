package charactorEditor.drag.Component;

import javax.swing.JComboBox;

import charactorEditor.drag.AttributePane;

public class PropertySelectCombo extends JComboBox {
	private AttributePane outer;

	public PropertySelectCombo(AttributePane e) {
		super();
		outer = e;
		setBounds(56, 102, 115, 23);
		outer.add(this);
	}

	public void update() {
		removeAllItems();
		if (outer.outerFighterBuilder.focusCMP != null)
			for (String s : outer.outerFighterBuilder.properties) {
				addItem(s);
			}
	}
}