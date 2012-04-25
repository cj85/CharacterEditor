package charactorEditor.states.Moved;

import java.awt.event.MouseEvent;

import charactorEditor.drag.FighterBuilder;

public class NeedToCross extends MouseMovedState {
	private static NeedToCross instance;

	public static NeedToCross Instance(FighterBuilder f, MouseEvent e) {
		if (instance == null)
			instance = new NeedToCross(f, e);
		instance.e=e;
		return instance;
	}

	private NeedToCross(FighterBuilder f, MouseEvent e) {
		super(f, e);
	}

	public NeedToCross getState() {
		if (needToCross())
			return instance;
		return null;
	}

	public void action() {
		myFighterBuilder.cross();
	}

	private boolean needToCross() {
		return (e.getSource()==myFighterBuilder.getComponentPanel()&& myFighterBuilder.getComponentPanel().find(e.getPoint()) != -1)
				|| (e.getSource()==myFighterBuilder.getDrawPane() && myModel.findComponent(e.getPoint()) != null && myModel
						.getSetSizeFlag() == false);
	}
}