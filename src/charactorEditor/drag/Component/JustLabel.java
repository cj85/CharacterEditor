package charactorEditor.drag.Component;

import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class JustLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JustLabel(String s,Rectangle r,JComponent outer){
		super(s);
		setBounds(r);
		outer.add(this);
	}
}