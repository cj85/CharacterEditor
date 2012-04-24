package charactorEditor;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDragingRectangleState extends MouseDraggedState {
	public isDragingRectangleState(MainPaneModel jc, MouseEvent e) {
		super(jc, e);
	}

	public isDragingRectangleState getState() {
		if (jc.isDragingRectangle())
			return this;
		return null;
	}

	public void action() {
		jc.dragRectangle(e.getPoint());
	};
}