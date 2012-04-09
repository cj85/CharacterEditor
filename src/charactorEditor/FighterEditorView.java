package charactorEditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class FighterEditorView extends JPanel {
	private JFrame currentFrame;
	private FighterEditorModel myModel;
	// private JButton myChangeHead;
	private JButton myMakeChange;
	private ArrayList<JLabel> myLabels = new ArrayList<JLabel>();

	public FighterEditorView(FighterEditorModel m, JFrame p) {
		myModel = m;
		currentFrame = p;
		// setLayout(new BorderLayout());
		// add(makeOperatePanel(), BorderLayout.NORTH);
		// add(makeInformationPanel(), BorderLayout.CENTER);
		this.setBounds(0, 0, 700, 500);
		this.setLayout(null);
		add(makeOperatePanel());
		add(makeInformationPanel());
		enableButtons();
		update();
	}

	private JComponent makeOperatePanel() {
		JPanel panel = new JPanel();
		// myChangeHead=new JButton("Change Head");
		// myChangeHead.addActionListener(new ActionListener(){
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// myModel.changeHead(FighterEditorView.this);
		// update();
		// }});
		// panel.add(myChangeHead);

		panel.setBounds(0, 0, 200, 500);
		panel.setLayout(null);
		int i = 0;
		int j = 0;
		for (final String s : Fighter.item) {
			JButton toAdd = new JButton("change " + s);
			toAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					myModel.getFighter().set(s,
							JOptionPane.showInputDialog("please input " + s));
					update();
				}
			});
			toAdd.setBounds(30, 10 + i * 40, 130, 30);
			panel.add(toAdd);
			i++;

		}

		myMakeChange = new JButton("Done");
		myMakeChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myModel.makeTheChange();
				currentFrame.dispose();
			}
		});
		panel.add(myMakeChange);
		myMakeChange.setBounds(30, 10 + 6 * 40, 130, 30);
		return panel;
	}

	private JComponent makeInformationPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(200, 0, 200, 500);
		panel.setLayout(null);
		int i = 0;
		for (String s : Fighter.item) {
			JLabel l = new JLabel();
			l.setBounds(30, 10 + i * 40, 130, 30);
			panel.add(l);
			myLabels.add(l);
			i++;
		}
		return panel;
	}

	private void enableButtons() {
		// myChangeHead.setEnabled(true);
	}

	public void update() {
		for (int i = 0; i < Fighter.item.size(); i++)
			myLabels.get(i).setText(
					myModel.getFighter().get(Fighter.item.get(i)));
	}
}