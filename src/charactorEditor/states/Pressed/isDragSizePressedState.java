package charactorEditor.states.Pressed;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.Model.Model;

public class isDragSizePressedState extends MousePressedState {
	private static isDragSizePressedState instance;

	public static isDragSizePressedState Instance(MainPaneModel mainPaneModel,
			Model model, MouseEvent e) {
		if (instance == null)
			instance = new isDragSizePressedState(mainPaneModel, model, e);
		instance.e = e;
		return instance;
	}

	private isDragSizePressedState(MainPaneModel mainPaneModel, Model model,
			MouseEvent e) {
		super(mainPaneModel, model, e);
	}

	public isDragSizePressedState getState() {
		if (isDragSizePressed())
			return instance;
		return null;
	}

	public void action() {
		mainPaneModel.setFoucsComponent(mainPaneModel.getDragingSize());
	}

}