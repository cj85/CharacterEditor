package charactorEditor.states;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isSelectingComponentState extends MouseReleasedState {
	private static isSelectingComponentState instance;

	public static isSelectingComponentState Instance(MainPaneModel model,
			MouseEvent e) {
		if (instance == null)
			instance = new isSelectingComponentState(model, e);
		instance.e = e;
		return instance;
	}

	private isSelectingComponentState(MainPaneModel model, MouseEvent e) {
		super(model, e);
	}
    
	public isSelectingComponentState getState(){
		if(model.isSelectingComponent())
			return instance;
		return null;
	}
	
	public void subAction(){
		model.selectComponent(model.getSelectingRectangle());
	}
}