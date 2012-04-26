package charactorEditor.states.Dragged;

import java.awt.event.MouseEvent;
import java.util.HashSet;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.states.State;

public class MouseDraggedState implements State {
	private HashSet<MouseDraggedState> allStates = new HashSet<MouseDraggedState>();
	MainPaneModel model;
	MouseEvent e;
	private static MouseDraggedState instance;

	public static MouseDraggedState Instance(MainPaneModel model, MouseEvent e) {
		if (instance == null)
			instance = new MouseDraggedState(model, e);
		instance.e = e;
		return instance;
	}

	MouseDraggedState(MainPaneModel model, MouseEvent e) {
		this.model = model;
		this.e = e;
	}

	public void create() {
		allStates.add(isDraggingState.Instance(model, e));
		allStates.add(isDragingSizeState.Instance(model, e));
		allStates.add(isDragingRectangleState.Instance(model, e));
	}

	public MouseDraggedState getState() {
		for (MouseDraggedState state : allStates) {
			if (state.getState() != null)
				return state.getState();
		}
		return new PseudoState(model, e);
	}

	public void action() {
	}

}