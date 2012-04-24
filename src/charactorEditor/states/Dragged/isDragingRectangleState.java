package charactorEditor.states.Dragged;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDragingRectangleState extends MouseDraggedState {
	private static isDragingRectangleState instance;

	public static isDragingRectangleState Instance(MainPaneModel jc,
			MouseEvent e) {
		if (instance == null)
			instance = new isDragingRectangleState(jc, e);
		instance.e = e;
		return instance;
	}

	private isDragingRectangleState(MainPaneModel jc, MouseEvent e) {
		super(jc, e);
	}

	public isDragingRectangleState getState() {
		if (model.isDragingRectangle())
			return instance;
		return null;
	}

	public void action() {
		model.dragRectangle(e.getPoint());
	};
}