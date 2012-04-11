package charactorEditor.drag.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import charactorEditor.drag.AttributePane;

public class AddImgButton extends JButton {
	private AttributePane outer;

	public AddImgButton(AttributePane e) {
		super("add");
		outer = e;
		setBounds(55, 129, 115, 23);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser(".");
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					outer.outerFighterBuilder.focusCMP.img = fc
							.getSelectedFile();
				}
			}
		});
		
		outer.add(this);
//		update();
	}
	public void update(){
		if(outer.outerFighterBuilder.focusCMP!=null){
			setEnabled(true);System.out.println(outer.outerFighterBuilder.focusCMP.text);}
		else{
			setEnabled(false);System.out.println("nonono");}
	}
}