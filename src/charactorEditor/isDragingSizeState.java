package charactorEditor;

import java.awt.event.MouseEvent;

import charactorEditor.Model.MainPaneModel;

public class isDragingSizeState extends MouseDraggedState {
	public isDragingSizeState(MainPaneModel jc, MouseEvent e) {
		super(jc, e);
	}

	public isDragingSizeState getState() {
		if (jc.isDragingSize())
			return this;
		return null;
	}

	public void action() {
		jc.dragSize(e.getPoint());
	};
}