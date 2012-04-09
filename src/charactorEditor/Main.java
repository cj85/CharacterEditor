package charactorEditor;
import javax.swing.JFrame;

public class Main{
	public static void main(String[] args){
        CharactorEditorModel model = new CharactorEditorModel();
     
        CharactorEditorViewer display = new CharactorEditorViewer(model);
        	JFrame frame = new JFrame();
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.getContentPane().add(display);
        	frame.pack();
        	frame.setSize(700, 500);
        	frame.setVisible(true);
        	
	}
}