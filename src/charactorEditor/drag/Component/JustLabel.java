package charactorEditor.drag.Component;

import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JLabel;

import charactorEditor.drag.Update;

public class JustLabel extends JLabel implements Update{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JustLabel(String s,Rectangle r,JComponent outer){
		super(s);
		setBounds(r);
		outer.add(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}