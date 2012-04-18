package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import charactorEditor.Loader;
import charactorEditor.drag.AttributePane;

@SuppressWarnings("serial")
public class LoadButton extends JButton{
	AttributePane outer;
	public LoadButton(AttributePane outer){
		super("load");
		this.outer=outer;
		setBounds(outer.mySaveButton.getX()+outer.mySaveButton.getWidth()+10,outer.mySaveButton.getY(),70,20);
	    addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                  try {
					load();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}});
		outer.add(this);
	}
	private void load() throws FileNotFoundException{
	JFileChooser fc = new JFileChooser(".");
		
		int returnVal =fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			outer.outerFighterBuilder.componentList= Loader.load(file.toString());
		}
	}
}