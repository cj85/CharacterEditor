package charactorEditor;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDraggingState extends MouseDraggedState {
	public isDraggingState(MainPaneModel jc, MouseEvent e) {
		super(jc, e);
	}

	public isDraggingState getState() {
		if (jc.isDraging())
			return this;
		return null;
	}

	public void action() {
		jc.dragingComponent(e.getPoint());
	};
}