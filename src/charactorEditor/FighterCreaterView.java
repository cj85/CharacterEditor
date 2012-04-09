package charactorEditor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class FighterCreaterView extends JPanel {
	private FighterCreaterModel myModel;
	private JButton create;
	private ArrayList<JLabel> myLabels = new ArrayList<JLabel>();
	private JFrame currentFrame;

	public FighterCreaterView(FighterCreaterModel model, JFrame p) {
		currentFrame = p;
		myModel = model;
		setLayout(new BorderLayout());
		add(makeOperatePanel(), BorderLayout.NORTH);
		add(makeInformationPanel(), BorderLayout.CENTER);
		enableButtons();
	}

	private JComponent makeOperatePanel() {
		JPanel panel = new JPanel();
		for (final String s : Fighter.item) {
			JButton toAdd = new JButton("add " + s);
			toAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					myModel.getFighter().set(s,
							JOptionPane.showInputDialog("please input " + s));
					update();
				}
			});
			panel.add(toAdd);

		}
		create = new JButton("Create");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myModel.creatFighter();
				currentFrame.dispose();
			}
		});
		panel.add(create);
		return panel;
	}

	private JComponent makeInformationPanel() {
		JPanel panel = new JPanel();
		for (String s : Fighter.item) {
			JLabel nameLabel = new JLabel(s + " to add");
			panel.add(nameLabel);
			myLabels.add(nameLabel);
		}
		return panel;
	}

	void update() {
		for (int i = 0; i < Fighter.item.size(); i++)
			myLabels.get(i).setText(
					myModel.getFighter().get(Fighter.item.get(i)));
		enableButtons();

	}

	private void enableButtons() {
		create.setEnabled(myModel.readyToCreat());
	}

	public FighterCreaterModel getFighterCreaterModel() {
		return myModel;
	}
}