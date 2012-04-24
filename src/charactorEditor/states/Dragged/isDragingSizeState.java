package charactorEditor.states.Dragged;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDragingSizeState extends MouseDraggedState {
	private static isDragingSizeState instance;

	public static isDragingSizeState Instance(MainPaneModel jc, MouseEvent e) {
		if (instance == null)
			instance = new isDragingSizeState(jc, e);
		instance.e = e;
		return instance;
	}

	private isDragingSizeState(MainPaneModel jc, MouseEvent e) {
		super(jc, e);
	}

	public isDragingSizeState getState() {
		if (model.isDragingSize())
			return this;
		return null;
	}

	public void action() {
		model.dragSize(e.getPoint());
	};
}