package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import SpriteTree.LimbNode;

import charactorEditor.Controller;
import charactorEditor.drag.AttributePane;
import charactorEditor.drag.Update;

@SuppressWarnings("serial")
public class LoadButton extends JButton  implements Update{
	AttributePane outer;
	private Controller myController = Controller.Instance();

	public LoadButton(AttributePane outer) {
		super("load");
		this.outer = outer;
		setBounds(outer.mySaveButton.getX() + outer.mySaveButton.getWidth()
				+ 10, outer.mySaveButton.getY(), 70, 20);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					load(e);
					updateOther();
					LimbNode root=myController.getLimbNodeTree();
					for(LimbNode node:root.getChildren())
						node.print();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		outer.register(this);
		outer.add(this);
	}

	private void load(ActionEvent e) throws FileNotFoundException {
		JFileChooser fc = new JFileChooser(".");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			myController.getMessage(file, e);
		}
	}

	private  void updateOther() {
		
		myController.updateAttributPane();
		myController.updateFigherBuilder();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}