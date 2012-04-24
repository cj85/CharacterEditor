package charactorEditor.states.Pressed;

import java.awt.event.MouseEvent;
import java.util.HashSet;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.Model.Model;
import charactorEditor.states.State;

public class MousePressedState implements State {
	private HashSet<MousePressedState> allStates = new HashSet<MousePressedState>();
	MainPaneModel mainPaneModel;
	Model model;
	MouseEvent e;
	private static MousePressedState instance;

	public static MousePressedState Instance(MainPaneModel mainPaneModel,
			Model model, MouseEvent e) {
		if (instance == null)
			instance = new MousePressedState(mainPaneModel, model, e);
		instance.e = e;
		return instance;
	}

	MousePressedState(MainPaneModel mainPaneModel, Model model, MouseEvent e) {
		this.mainPaneModel = mainPaneModel;
		this.model = model;
		this.e = e;
	}

	public void creat() {
		allStates.add(isPutingComponentState.Instance(mainPaneModel, model, e));
		allStates.add(isDragingSelectingState.Instance(mainPaneModel, model, e));
		allStates.add(isRemoveComponentState.Instance(mainPaneModel, model, e));
		allStates.add(isMiddlePressedState.Instance(mainPaneModel, model, e));
		allStates.add(isDragSizePressedState.Instance(mainPaneModel, model, e));

	}

	public MousePressedState getState() {
		for (MousePressedState state : allStates)
			if (state.getState() != null)
				return state.getState();
		return null;
	}

	public void action() {

	}

	boolean isPuttingComponent() {
		return model.isPuttingComponent();
	}

	boolean isDragingSelecting() {
		return (!isPuttingComponent() && (mainPaneModel.setDragingSize(model
				.findComponent(e.getPoint())) == null));
	}

	boolean isRemoveComponent() {
		return (middleState() && e.getClickCount() >= 2);
	}

	boolean isDragSizePressed() {
		return (middleState() && e.getClickCount() < 2 && model
				.getSetSizeFlag() == true);
	}

	boolean isMiddlePressed() {
		return (middleState() && e.getClickCount() < 2 && model
				.getSetSizeFlag() == false);
	}

	boolean middleState() {
		return (!isPuttingComponent() && (mainPaneModel.setDragingSize(model
				.findComponent(e.getPoint()))!= null) );
	}
}