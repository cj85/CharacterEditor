import java.util.ArrayList;

import javax.swing.JFrame;

public class CharactorEditorModel{
	private ArrayList<Fighter> myFighters;
    private ArrayList<Head> myHeads;	
    public void creatFighter(){
    	
    	JFrame frame = new JFrame();
    	FighterCreaterView display = new FighterCreaterView(new FighterCreaterModel(),frame);
    	frame.getContentPane().add(display);
    	frame.pack();
    	frame.setSize(700, 500);
    	frame.setVisible(true);
    }
    public void editFighter(){
    	//TODO 
    	
    	JFrame frame = new JFrame();
    	ChoiceFighter display = new ChoiceFighter(myFighters,null,frame);
    	frame.getContentPane().add(display);
    	frame.pack();
    	frame.setVisible(true);
    }
}