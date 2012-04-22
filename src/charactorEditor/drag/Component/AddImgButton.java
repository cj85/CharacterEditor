package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import charactorEditor.Controller;
import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class AddImgButton extends JButton {
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
					myController.getMessage(fc.getSelectedFile(),e);
					update();
				}
			}
		});
		outer.add(this);
	}

	public void update() {
		setEnabled(myController.focusCMP != null);
		outer.outerFighterBuilder.repaint();
	}
}