package charactorEditor.states.Released;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDragSizeState extends MouseReleasedState {
	private static isDragSizeState instance;

	public static isDragSizeState Instance(MainPaneModel model, MouseEvent e) {
		if (instance == null)
			instance = new isDragSizeState(model, e);
		instance.e = e;
		return instance;
	}

	private isDragSizeState(MainPaneModel model, MouseEvent e) {
		super(model, e);
	}

	public isDragSizeState getState() {
		if (model.isDragSize())
			return instance;
		return null;
	}

	public void subAction() {
		model.setSize(e.getPoint());
	}
}