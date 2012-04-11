package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import charactorEditor.Loader;
import charactorEditor.Writer;
import charactorEditor.drag.AttributePane;

public class LoadButton extends JButton{
	AttributePane outer;
	public LoadButton(AttributePane outer){
		super("load");
		this.outer=outer;
		setBounds(outer.save_btn.getX()+outer.save_btn.getWidth()+10,outer.save_btn.getY(),70,20);
	    addActionListener(new ActionListener(){

			@Override
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