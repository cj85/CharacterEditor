package charactorEditor.drag.Component;

import java.awt.Font;

import javax.swing.JLabel;

import charactorEditor.Controller;
import charactorEditor.Model.Model;
import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class NameDisplayLabel extends JLabel {
	private AttributePane outer;
    private Controller myController;
    private Model myModel=Model.Instance();
	public NameDisplayLabel(AttributePane e) {
		super("");
		outer = e;
		myController=Controller.Instance();
		setBounds(4, 5, 173, 37);
		setFont(new Font("", Font.BOLD, 16));
		outer.add(this);
	}

	public void update() {
		if (myController.focusCMP != null)
			setText("   "
					+ myModel.SORT.CMP[myController.focusCMP.sort]);
		else
			setText("");
	}
}