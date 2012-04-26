package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import charactorEditor.Controller;
import charactorEditor.drag.AttributePane;
import charactorEditor.drag.Update;

@SuppressWarnings("serial")
public class AddImgButton extends JButton implements Update {
	private AttributePane outer;
	Controller myController = Controller.Instance();

	public AddImgButton(AttributePane e) {
		super("add");
		outer = e;
		setBounds(55, 129, 115, 23);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(".");
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					myController.getMessage(fc.getSelectedFile(), e);
					updateOther();
				}
			}
		});
		outer.register(this);
		outer.add(this);
	}


	public void update() {
		setEnabled(myController.getFoucsedComponent() != null);

	}
		public void updateOther() {
		myController.updateFigherBuilder();
	}

	
}