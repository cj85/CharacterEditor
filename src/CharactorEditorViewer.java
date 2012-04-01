import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CharactorEditorViewer extends JPanel{
	protected CharactorEditorModel myModel;
	private JButton myCreatFighter;
	
	public CharactorEditorViewer(CharactorEditorModel model) {
		myModel = model;
		setLayout(new BorderLayout());
		add(makeOperatePanel(), BorderLayout.NORTH);
		enableButtons();
	}
	private void enableButtons(){
		myCreatFighter.setEnabled(true);
	}
	private JComponent makeOperatePanel() {
		JPanel panel = new JPanel();
        myCreatFighter=new JButton("creat new Fighter");
        myCreatFighter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
	        	
	        	JFrame frame = new JFrame();
	        	FighterCreaterView display = new FighterCreaterView(new FighterCreaterModel(),frame);
	        	frame.getContentPane().add(display);
	        	frame.pack();
	        	frame.setSize(700, 500);
	        	frame.setVisible(true);
			}});
        panel.add(myCreatFighter);
		return panel;
	}
}