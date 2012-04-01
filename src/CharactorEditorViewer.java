import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CharactorEditorViewer extends JPanel{
	protected CharactorEditorModel myModel;
	private JButton myCreatFighter;
	private JButton myEditFighter;
	
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
        myCreatFighter=new JButton("creat a new Fighter");
        myCreatFighter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                  myModel.creatFighter();
			}});
        panel.add(myCreatFighter);
        
        myEditFighter=new JButton("Edit a Fighter");
        myEditFighter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				myModel.editFighter();
			}});
        panel.add(myEditFighter);
		return panel;
	}
}