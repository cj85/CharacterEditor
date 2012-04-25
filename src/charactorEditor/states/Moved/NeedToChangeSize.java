package charactorEditor.states.Moved;

import java.awt.event.MouseEvent;

import charactorEditor.drag.FighterBuilder;

public class NeedToChangeSize extends MouseMovedState {
	private static NeedToChangeSize instance;

	public static NeedToChangeSize Instance(FighterBuilder f, MouseEvent e) {
		if (instance == null)
			instance = new NeedToChangeSize(f, e);
		instance.e=e;
		return instance;
	}

	private NeedToChangeSize(FighterBuilder f, MouseEvent e) {
		super(f, e);
	}

	public NeedToChangeSize getState() {
		if (needToChangeSize())
			return instance;
		return null;
	}

	public void action() {
		myFighterBuilder.changesize();
	}

	private boolean needToChangeSize() {
		return (e.getSource()==myFighterBuilder.getDrawPane())&&(myModel.findComponent(e.getPoint()) != null)
				&& (myModel.getSetSizeFlag() == true);
	}
}