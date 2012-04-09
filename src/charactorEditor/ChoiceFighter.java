package charactorEditor;
import javax.swing.JFrame;
import java.util.*;

public class ChoiceFighter extends Choice{

	public ChoiceFighter(Object obj, Fighter fgt, JFrame p) {
		super(obj, fgt, p);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void addElement() {
		// TODO Auto-generated method stub
		ArrayList<Fighter> myModel=FighterParser.parse();
		for(Fighter f:myModel)
			mylitem.addElement(f.get("name"));
	}

	@Override
	void setValue(Object s) {
		// TODO Auto-generated method stub
    	
    	JFrame frame = new JFrame();
    	FighterEditorView display = new FighterEditorView(new FighterEditorModel((String) s),frame);
//    	frame.getContentPane().add(display);
    	frame.add(display);
//    	frame.pack();
//    	frame.setSize(700, 500);
    	frame.setBounds(0, 0, 700, 500);
    	frame.setVisible(true);
	}
	
}