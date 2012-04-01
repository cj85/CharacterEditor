import javax.swing.JFrame;

public class Main{
	public static void main(String[] args){
        CharactorEditorModel model = new CharactorEditorModel();
     
        CharactorEditorViewer display = new CharactorEditorViewer(model);
        	// create container that will work with Window manager
        	JFrame frame = new JFrame();
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	// add our user interface components to Frame and show it
        	frame.getContentPane().add(display);
        	frame.pack();
        	frame.setSize(700, 500);
        	frame.setVisible(true);
        	
	}
}