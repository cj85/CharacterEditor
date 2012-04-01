import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FighterCreaterView extends JPanel {
	private FighterCreaterModel myModel;
	private JButton addHead;
	private JButton rmHead;
	private JButton addName;
	private JButton creatButton;
	private JLabel nameLabel;
	private JLabel headLabel;
	private JFrame currentFrame;

	public FighterCreaterView(FighterCreaterModel model, JFrame p) {
		currentFrame = p;
		myModel = model;
		setLayout(new BorderLayout());
		add(makeOperatePanel(), BorderLayout.NORTH);
		add(makeInformationPanel(), BorderLayout.CENTER);
		enableButtons();
	}

	private void enableButtons() {
		addHead.setEnabled(true);
		rmHead.setEnabled(true);
		addName.setEnabled(true);
	}

	private JComponent makeOperatePanel() {
		JPanel panel = new JPanel();
		addName = new JButton("add Name");
		addName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myModel.getFighter().setName(
						JOptionPane.showInputDialog("please input name"));
				update();
			}
		});
		panel.add(addName);

		addHead = new JButton("add Head");
		addHead.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				ChoiceHead display = new ChoiceHead(FighterCreaterView.this,
						myModel.getFighter(), frame);
				frame.getContentPane().add(display);
				frame.pack();
				frame.setVisible(true);
			}
		});
		panel.add(addHead);

		rmHead = new JButton("remove Head NOT IMPLEMENT");
		rmHead.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(rmHead);

		creatButton = new JButton("creat Fighter");
		creatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myModel.creatFighter();
				currentFrame.dispose();
			}
		});
		panel.add(creatButton);

		return panel;
	}

	private JComponent makeInformationPanel() {
		JPanel panel = new JPanel();

		nameLabel = new JLabel("name to add");
		panel.add(nameLabel);

		headLabel = new JLabel("head to add");
		panel.add(headLabel);

		return panel;
	}

	void update() {
		nameLabel.setText(myModel.getFighter().getName());
		headLabel.setText(myModel.getFighter().getHead());
	}

	public FighterCreaterModel getFighterCreaterModel() {
		return myModel;
	}
}