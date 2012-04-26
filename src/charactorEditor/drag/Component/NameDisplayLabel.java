package charactorEditor.drag.Component;

import java.awt.Font;

import javax.swing.JLabel;

import charactorEditor.Controller;
import charactorEditor.Model.Sort;
import charactorEditor.drag.AttributePane;
import charactorEditor.drag.Update;

@SuppressWarnings("serial")
public class NameDisplayLabel extends JLabel  implements Update{
	private AttributePane outer;
    private Controller myController;
	public NameDisplayLabel(AttributePane e) {
		super("");
		outer = e;
		myController=Controller.Instance();
		setBounds(4, 5, 173, 37);
		setFont(new Font("", Font.BOLD, 16));
		outer.register(this);
		outer.add(this);
	}

	public void update() {
		if (myController.getFoucsedComponent() != null)
			setText("   "
					+ Sort.CMP[myController.getFoucsedComponent().getSort()]);
		else
			setText("");
	}
}