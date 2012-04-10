package charactorEditor.drag.JComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import charactorEditor.drag.AttributePane;

public class SaveButton extends JButton{
	private AttributePane outer;
	public SaveButton(AttributePane e){
		super("save");
		outer=e;
		setBounds(4, 190, 70, 20);
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				outer.save();
				outer.update();
			}});
		outer.add(this);
		
	}
}