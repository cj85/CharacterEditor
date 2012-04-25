package charactorEditor.drag.Component;

import javax.swing.JComboBox;

import charactorEditor.Controller;
import charactorEditor.drag.AttributePane;

@SuppressWarnings({ "rawtypes", "serial" })
public class PropertySelectCombo extends JComboBox {
	private AttributePane outer;
	Controller myController = Controller.Instance();
	public PropertySelectCombo(AttributePane e) {
		super();
		outer = e;
		setBounds(56, 102, 115, 23);
		outer.add(this);
	}

	@SuppressWarnings("unchecked")
	public void update() {
		removeAllItems();
		if (myController.getFoucsedComponent() != null)
			for (String s : myController.getProperties()) {
				addItem(s);
			}
	}
}