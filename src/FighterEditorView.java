import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FighterEditorView extends JPanel{
	private JFrame currentFrame;
	private FighterEditorModel myModel;
	private JButton myChangeHead;
	private JButton myMakeChange;
	private JLabel headLabel;
	
	public FighterEditorView(FighterEditorModel m,JFrame p) {
	    myModel=m;
	    currentFrame=p;
		setLayout(new BorderLayout());
		add(makeOperatePanel(), BorderLayout.NORTH);
		add(makeInformationPanel(), BorderLayout.CENTER);
		enableButtons();
		update();
	}
	private JComponent makeOperatePanel(){
		JPanel panel=new JPanel();
		myChangeHead=new JButton("Change Head");
		myChangeHead.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                     myModel.changeHead(FighterEditorView.this);
                     update();
			}});
		panel.add(myChangeHead);
		
		myMakeChange=new JButton("Make the Change");
		myMakeChange.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myModel.makeTheChange();
				currentFrame.dispose();
			}});
		panel.add(myMakeChange);
		return panel;
	}
	private JComponent makeInformationPanel(){
		JPanel panel=new JPanel();
		headLabel=new JLabel();
		panel.add(headLabel);
		return panel;
	}
	private void enableButtons(){
		myChangeHead.setEnabled(true);
	}
	public void update(){
		headLabel.setText(myModel.getFighter().getHead());
	}
}