package charactorEditor.states.Released;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDragingComponentState extends MouseReleasedState {
	private static isDragingComponentState instance;

	public static isDragingComponentState Instance(MainPaneModel model,
			MouseEvent e) {
		if (instance == null)
			instance = new isDragingComponentState(model, e);
		instance.e = e;
		return instance;
	}

	private isDragingComponentState(MainPaneModel model, MouseEvent e) {
		super(model, e);
	}

	public isDragingComponentState getState() {
		if (model.isDragingComponent())
			return instance;
		return null;
	}

	public void subAction() {
		model.setComponents();
	}
}