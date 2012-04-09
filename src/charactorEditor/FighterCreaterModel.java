package charactorEditor;
import java.util.ArrayList;

public class FighterCreaterModel{
	private Fighter myFighter;
	private ArrayList<Fighter> myFighters;
//	private ArrayList<Head> myHeads;
	
	public FighterCreaterModel(){
		myFighter=new Fighter();
		myFighters=FighterParser.parse();
//		myHeads=HeadParser.parse();
	}
	
	
    public boolean readyToCreat(){
        for (String s:Fighter.item)
        	if(myFighter.get(s)!=null) continue;
        	else return false;
    	return true;
    }
    
	public Fighter getFighter(){
		return myFighter;
	}
	public ArrayList<Fighter> getFighters(){
		return myFighters;
	}
//	public ArrayList<Head> getHeads(){
//		return myHeads;
//	}
	public void creatFighter(){
		myFighters.add(myFighter);
		WriteFighterFile writer=new WriteFighterFile(myFighters);
		writer.write();
	}
}