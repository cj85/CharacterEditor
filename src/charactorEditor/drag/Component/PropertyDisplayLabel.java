package charactorEditor.drag.Component;

import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import charactorEditor.drag.AttributePane;

public class PropertyDisplayLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AttributePane outer;

	public PropertyDisplayLabel(AttributePane e) {
		super();
		outer = e;
		setBounds(outer.label_7.getX(),
				outer.label_7.getY() + outer.label_7.getHeight() + 10, 150, 300);
		outer.add(this);
	}

	public void update() {
		setVerticalAlignment(SwingConstants.TOP);
		if (outer.myModel.focusCMP != null) {
			HashMap<String, String> properties = outer.myModel.focusCMP
					.getProperties();
			String toSet = "<html><body>";
			for (String s : properties.keySet())
				toSet += "<br>" + s + ": " + properties.get(s) + "<br>";
			toSet += "</body> </html>";
			setText(toSet);
		} else {
			setText("");
		}
	}
}