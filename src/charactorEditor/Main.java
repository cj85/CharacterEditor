package charactorEditor;
import java.io.FileNotFoundException;


import charactorEditor.Model.Model;
import charactorEditor.drag.FighterBuilder;

public class Main{
	public static void main(String[] args) throws FileNotFoundException{
		Model myModel=Model.Instance();
		Controller myController=Controller.Instance();
		myController.register(myModel);
		new FighterBuilder();
	}
}