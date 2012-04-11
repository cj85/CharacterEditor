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
	JustLabel label_3 = new JustLabel("Text", new Rectangle(4, 76, 55, 20),
			this);
	AddPropertyButton addProperty_btn = new AddPropertyButton(this);
	JustLabel label_5 = new JustLabel("Img", new Rectangle(4, 130, 55, 20),
			this);
	JLabel label_6 = null;
	public JustLabel label_7 = new JustLabel("Properties", new Rectangle(4,
			220, 100, 20), this);
	public PropertyDisplayLabel myPropertyDisplayLabel = new PropertyDisplayLabel(this);

	SetMyComponentTextField mySetComponentText = new SetMyComponentTextField(this);
	public JComboBox cbo_1 = null;
	AddImgButton imgbtn = new AddImgButton(this);
	JButton btn2 = null;
	JButton btn_tab = null;
	JButton btn_list = null;
	public SaveButton save_btn = new SaveButton(this);
	JButton load_btn = new LoadButton(this);
	public FighterBuilder outerFighterBuilder;
	Graphics2D g;

	public AttributePane(FighterBuilder e) {
		outerFighterBuilder = e;
		setBounds(817, 0, 171, 615);
		setLayout(null);
		label_1 = new JLabel("");
		label_1.setBounds(4, 5, 173, 37);
		label_1.setFont(new Font("", Font.BOLD, 16));
		cbo_1 = new JComboBox();// ÏÂÀ­¿ò
		cbo_1.setBounds(56, 102, 115, 23);
		label_6 = new JLabel("Label6");
		label_6.setBounds(4, 157, 55, 21);

		btn2 = new JButton("NO ACTION2");
		btn2.setBounds(55, 157, 115, 22);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		add(label_1);
		add(cbo_1);
		add(label_6);

		add(btn2);
		update();

	}

	public void paintComponent(Graphics e) {
		super.paintComponent(e);
		g = (Graphics2D) e;
		g.setColor(Color.BLUE);
		// g.fill( label_8.getBounds());
	}

	public void update() {
		myPropertyDisplayLabel.update();
		mySetComponentText.update();
		imgbtn.update();

	}

}