package charactorEditor.states.Moved;

import java.awt.event.MouseEvent;
import java.util.HashSet;

import charactorEditor.Model.Model;
import charactorEditor.drag.FighterBuilder;
import charactorEditor.states.State;

public class MouseMovedState implements State {
	private HashSet<MouseMovedState> allStates=new HashSet<MouseMovedState>();
	FighterBuilder myFighterBuilder;
	MouseEvent e;
	Model myModel;
	private static MouseMovedState instance;
	
	public static MouseMovedState Instance(FighterBuilder f, MouseEvent e){
		if(instance==null)
			instance=new MouseMovedState(f,e);
		instance.e=e;
		return instance;
	}
	MouseMovedState(FighterBuilder f, MouseEvent e){
		this.myFighterBuilder=f;
		this.e=e;
		myModel=Model.Instance();
	}

	public void creat() {
        allStates.add(NeedToCross.Instance(myFighterBuilder, e));
        allStates.add(NeedToDecross.Instance(myFighterBuilder, e));
        allStates.add(NeedToChangeSize.Instance(myFighterBuilder, e));
	}

	public State getState() {
		
		for(MouseMovedState state:allStates){
			if(state.getState()!=null)
				return state.getState();
		}
		return null;
	}

	public void action() {

	}
	

}