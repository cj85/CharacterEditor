package charactorEditor.drag;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

import charactorEditor.Writer;
import charactorEditor.drag.Component.*;

public class AttributePane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label_1 = null;
	JLabel label_2 = null;
	JLabel label_3 = null;
	AddPropertyButton addProperty_btn =new AddPropertyButton(this);
	JLabel label_5 = null;
	JLabel label_6 = null;
	JLabel label_7 = null;
	JLabel label_8 = null;

	JTextField text_1 = null;
	JTextField text_2 = null;
	public JComboBox cbo_1 = null;
	JButton imgbtn = null;
	JButton btn2 = null;
	JButton btn_tab = null;
	JButton btn_list = null;
	public SaveButton save_btn=new SaveButton(this);
	JButton load_btn=new LoadButton(this);
	public FighterBuilder outerFighterBuilder;
	Graphics2D g;

	public AttributePane(FighterBuilder e) {
		outerFighterBuilder = e;
		setBounds(817, 0, 171, 615);
		setLayout(null);
		label_1 = new JLabel("");
		label_1.setBounds(4, 5, 173, 37);
		label_1.setFont(new Font("", Font.BOLD, 16));
		label_2 = new JLabel("Name");
		label_2.setBounds(4, 48, 55, 20);
		label_3 = new JLabel("Text");
		label_3.setBounds(4, 76, 55, 20);
		label_5 = new JLabel("img");
		label_5.setBounds(4, 130, 55, 20);
		text_1 = new JTextField();// aside Name
		text_1.setBounds(55, 46, 115, 24);
		text_2 = new JTextField();// aside Text
		text_2.setBounds(55, 75, 114, 23);
		cbo_1 = new JComboBox();// ÏÂÀ­¿ò
		cbo_1.setBounds(56, 102, 115, 23);
		imgbtn = new JButton("add");
		imgbtn.setBounds(55, 129, 115, 23);
		imgbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser(".");
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					outerFighterBuilder.focusCMP.img = fc.getSelectedFile();
					
				}
			}});
		label_6 = new JLabel("Label6");
		label_6.setBounds(4, 157, 55, 21);

		label_7 = new JLabel("Properties");
		label_7.setBounds(4, 220, 100, 20);
		label_8 = new JLabel();
		label_8.setBounds(label_7.getX(), label_7.getY() + label_7.getHeight()
				+ 10, 150, 300);
		btn2 = new JButton("NO ACTION2");
		btn2.setBounds(55, 157, 115, 22);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		FocusListener l = new FocusHandler();
		text_1.addFocusListener(l);
		text_2.addFocusListener(l);
	

		add(label_1);
		add(label_2);
		add(label_3);
		add(label_5);
		add(text_1);
		add(text_2);
		add(cbo_1);
		add(imgbtn);
		add(label_6);
		add(label_7);
		add(label_8);
		add(btn2);
	
	}

	class FocusHandler implements FocusListener {
		public void focusGained(FocusEvent e) {

		}

		public void focusLost(FocusEvent e) {
			outerFighterBuilder.setCmpName(outerFighterBuilder.focusCMP, text_1.getText());
			outerFighterBuilder.setText(outerFighterBuilder.focusCMP, text_2.getText());
		}
	}

	public void paintComponent(Graphics e) {
		super.paintComponent(e);
		g = (Graphics2D) e;
		g.setColor(Color.BLUE);
		// g.fill( label_8.getBounds());
	}

	public void update() {
		label_8update();

	}

	public void label_8update() {
		label_8.setVerticalAlignment(SwingConstants.TOP);
		if (outerFighterBuilder.focusCMP != null) {
			HashMap<String, String> properties = outerFighterBuilder.focusCMP.getProperties();
			String toSet = "<html><body>";
			for (String s : properties.keySet())
				toSet += "<br>" + s + ": " + properties.get(s) + "<br>";
			toSet += "</body> </html>";
			label_8.setText(toSet);
		} else {
			label_8.setText("");
		}
	}

}