package charactorEditor.drag.Component;

import java.awt.Font;

import javax.swing.JLabel;

import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class NameDisplayLabel extends JLabel {
	private AttributePane outer;

	public NameDisplayLabel(AttributePane e) {
		super("");
		outer = e;
		setBounds(4, 5, 173, 37);
		setFont(new Font("", Font.BOLD, 16));
		outer.add(this);
	}

	public void update() {
		if (outer.myModel.focusCMP != null)
			setText("   "
					+ outer.myModel.SORT.CMP[outer.myModel.focusCMP.sort]);
		else
			setText("");
	}
}