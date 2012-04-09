package charactorEditor;
import java.util.ArrayList;

import javax.swing.JFrame;

public class FighterEditorModel {
	protected Fighter fighterToEdit;
	protected ArrayList<Fighter> fighterList;
//	protected ArrayList<Head> headList;

	public FighterEditorModel(String s) {
		fighterList = FighterParser.parse();
//		headList = HeadParser.parse();
		for (Fighter f : fighterList)
			if (s.equals(f.get("name")))
	             fighterToEdit = f;
	}

	public Fighter getFighter() {
		return fighterToEdit;
	}
	
//	public void changeHead(FighterEditorView viewer){
//    	JFrame frame = new JFrame();
//    	ChoiceHeadToChange display = new ChoiceHeadToChange(headList,fighterToEdit,frame,viewer);
//    	frame.getContentPane().add(display);
//    	frame.pack();
//    	frame.setVisible(true);
//	}
	public void makeTheChange(){
		WriteFighterFile writer=new WriteFighterFile(fighterList);
		writer.write();
	}
}