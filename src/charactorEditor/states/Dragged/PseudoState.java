package charactorEditor.states.Dragged;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class PseudoState extends MouseDraggedState{

	PseudoState(MainPaneModel model, MouseEvent e) {
		super(model, e);
	}

	@Override
	public void create() {
		
	}

	@Override
	public MouseDraggedState getState() {
		return this;
	}

	@Override
	public void action() {
		
	}
	
}