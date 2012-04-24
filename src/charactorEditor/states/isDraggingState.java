package charactorEditor.states;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDraggingState extends MouseDraggedState {
	private static isDraggingState instance;

	public static isDraggingState Instance(MainPaneModel jc, MouseEvent e) {
		if (instance == null)
			instance = new isDraggingState(jc, e);
		instance.e=e;
		return instance;
	}

	private isDraggingState(MainPaneModel jc, MouseEvent e) {
		super(jc, e);
	}

	public isDraggingState getState() {
		if (model.isDraging())
			return instance;
		return null;
	}

	public void action() {
		model.dragingComponent(e.getPoint());
	};
}