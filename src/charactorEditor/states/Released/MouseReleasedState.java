package charactorEditor.states.Released;

import java.awt.event.MouseEvent;
import java.util.HashSet;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.states.State;

public class MouseReleasedState implements State {
	private HashSet<MouseReleasedState> allStates = new HashSet<MouseReleasedState>();
	MainPaneModel model;
	MouseEvent e;
	private static MouseReleasedState instance;

	public static MouseReleasedState Instance(MainPaneModel model, MouseEvent e) {
		if (instance == null)
			instance = new MouseReleasedState(model, e);
		instance.e = e;
		return instance;
	}

	MouseReleasedState(MainPaneModel model, MouseEvent e) {
		this.model = model;
		this.e = e;
	}

	public void create() {
		allStates.add(isDragingComponentState.Instance(model, e));
		allStates.add(isDragSizeState.Instance(model, e));
		allStates.add(isSelectingComponentState.Instance(model, e));
	}

	public MouseReleasedState getState() {
		for (MouseReleasedState state : allStates) {
			if (state.getState() != null)
				return state.getState();
		}
		return null;
	}

	public void action() {
		subAction();
		model.clear();
	};

	public void subAction() {
	}
}