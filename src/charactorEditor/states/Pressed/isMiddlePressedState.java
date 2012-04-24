package charactorEditor.states.Pressed;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.Model.Model;

public class isMiddlePressedState extends MousePressedState {
	private static isMiddlePressedState instance;

	public static isMiddlePressedState Instance(MainPaneModel mainPaneModel,
			Model model, MouseEvent e) {
		if (instance == null)
			instance = new isMiddlePressedState(mainPaneModel, model, e);
		instance.e = e;
		return instance;
	}

	private isMiddlePressedState(MainPaneModel mainPaneModel, Model model,
			MouseEvent e) {
		super(mainPaneModel, model, e);
	}

	public isMiddlePressedState getState() {
		if (isMiddlePressed())
			return instance;
		return null;
	}

	public void action() {
		if (model.isNext()) {
			mainPaneModel.setNextFocusComponnet(mainPaneModel.getDragingSize());
		} else {
			mainPaneModel.setFoucsComponent(mainPaneModel.getDragingSize());
		}
		mainPaneModel.setDraging(mainPaneModel.getDragingSize());
		mainPaneModel.setDragingSize(null);
	}

}