package charactorEditor.drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.*;
import charactorEditor.drag.Component.*;

public class AttributePane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 ArrayList<Update> observers =new ArrayList<Update>();
	NameDisplayLabel myNameDisplayLabel = new NameDisplayLabel(this);

	public AddPropertyButton myAddPropertyButton = new AddPropertyButton(this);

	public JustLabel label_7 = new JustLabel("Properties", new Rectangle(4,
			220, 100, 20), this);
	public PropertyDisplayLabel myPropertyDisplayLabel = new PropertyDisplayLabel(
			this);

	public SetMyComponentTextField mySetComponentText = new SetMyComponentTextField(
			this);
	public PropertySelectCombo myPropertySelectCombo =new PropertySelectCombo(this);
	public AddImgButton myAddImgButton = new AddImgButton(this);
	public SaveButton mySaveButton = new SaveButton(this);
	public LoadButton myLoadButton = new LoadButton(this);
	Graphics2D g;

	public AttributePane() {
		setBounds(817, 0, 171, 615);
		setLayout(null);
		new JustLabel("Name", new Rectangle(4, 76, 55, 20), this);
		new JustLabel("Img", new Rectangle(4, 130, 55, 20), this);
	}

	public void paintComponent(Graphics e) {// only for test component area, will be deleted once the program is done
		super.paintComponent(e);
		g = (Graphics2D) e;
		g.setColor(Color.BLUE);
		// g.fill( label_8.getBounds());
	}

	public void update() {
		for(Update u:observers)
			u.update();
		repaint();
	}
	public void register(Update up){
		observers.add(up);
	}

}