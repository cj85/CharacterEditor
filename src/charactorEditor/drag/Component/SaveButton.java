package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import charactorEditor.Model.Model;
import charactorEditor.Model.Writer;
import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class SaveButton extends JButton{
	private AttributePane outer;
	private Model myModel=Model.Instance();
	public SaveButton(AttributePane e){
		super("save");
		outer=e;
		setBounds(4, 190, 70, 20);
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					save();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				outer.update();
			}});
		outer.add(this);
		
	}
	private void save() throws IOException{
		JFileChooser fc = new JFileChooser(".");
		
		int returnVal =fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			Writer.write(myModel.getComponnetList(),file.toString());
		}
	}
}