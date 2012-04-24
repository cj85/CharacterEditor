package charactorEditor.states.Pressed;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;
import charactorEditor.Model.Model;

public class isDragingSelectingState extends MousePressedState {
	private static isDragingSelectingState instance;

	public static isDragingSelectingState Instance(MainPaneModel mainPaneModel,
			Model model, MouseEvent e) {
		if (instance == null)
			instance = new isDragingSelectingState(mainPaneModel, model, e);
		instance.e = e;
		return instance;
	}

	private isDragingSelectingState(MainPaneModel mainPaneModel, Model model,
			MouseEvent e) {
		super(mainPaneModel, model, e);
	}

	public isDragingSelectingState getState() {
		if (isDragingSelecting())
			return instance;
		return null;
	}

	public void action() {
		mainPaneModel.setPutFrame(e.getPoint());
		mainPaneModel.creatSelectingRectangle();
		mainPaneModel.resetFoucsComponent();
		mainPaneModel.resetNextFoucsComponent();
	}
}
