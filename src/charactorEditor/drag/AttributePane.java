package charactorEditor.drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.*;

import charactorEditor.Model.Model;
import charactorEditor.drag.Component.*;

public class AttributePane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NameDisplayLabel myNameDisplayLabel = new NameDisplayLabel(this);

	AddPropertyButton myAddPropertyButton = new AddPropertyButton(this);

	public JustLabel label_7 = new JustLabel("Properties", new Rectangle(4,
			220, 100, 20), this);
	public PropertyDisplayLabel myPropertyDisplayLabel = new PropertyDisplayLabel(
			this);

	private SetMyComponentTextField mySetComponentText = new SetMyComponentTextField(
			this);
	public PropertySelectCombo myPropertySelectCombo =new PropertySelectCombo(this);
	private AddImgButton myAddImgButton = new AddImgButton(this);
	public SaveButton mySaveButton = new SaveButton(this);
	@SuppressWarnings("unused")
	private LoadButton myLoadButton = new LoadButton(this);
	public FighterBuilder outerFighterBuilder;
	public Model myModel;
	Graphics2D g;

	public AttributePane(FighterBuilder e) {
		outerFighterBuilder = e;
		setBounds(817, 0, 171, 615);
		setLayout(null);
		myModel=e.myModel;
		new JustLabel("Name", new Rectangle(4, 76, 55, 20), this);
		new JustLabel("Img", new Rectangle(4, 130, 55, 20), this);
		update();

	}

	public void paintComponent(Graphics e) {// only for test component area, will be deleted once the program is done
		super.paintComponent(e);
		g = (Graphics2D) e;
		g.setColor(Color.BLUE);
		// g.fill( label_8.getBounds());
	}

	public void update() {
		myPropertyDisplayLabel.update();
		mySetComponentText.update();
		myAddImgButton.update();
		myAddPropertyButton.update();
		myNameDisplayLabel.update();
		myPropertySelectCombo.update();
		repaint();
	}

}