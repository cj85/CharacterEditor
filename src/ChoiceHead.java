import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

public class ChoiceHead extends Choice{
public ChoiceHead(Object obj,Fighter fgt,JFrame p){
	super(obj,fgt,p);
}

	@Override
	void addElement() {
		// TODO Auto-generated method stub
		for (Head h: (ArrayList<Head>)((FighterCreaterView)myModel).getFighterCreaterModel().getHeads())
			mylitem.addElement(h.getName());
		
	}

	@Override
	void setValue(Object s) {
		// TODO Auto-generated method stub
		myFighter.setHead((String)s);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		((FighterCreaterView)myModel).update();
	}
	
}