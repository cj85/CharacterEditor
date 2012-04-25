package charactorEditor.states.Moved;

import java.awt.event.MouseEvent;

import charactorEditor.drag.FighterBuilder;

public class NeedToDecross extends MouseMovedState {
	private static NeedToDecross instance;

	public static NeedToDecross Instance(FighterBuilder f, MouseEvent e) {
		if (instance == null)
			instance = new NeedToDecross(f, e);
		instance.e=e;
		return instance;
	}

	private NeedToDecross(FighterBuilder f, MouseEvent e) {
		super(f, e);
	}

	public NeedToDecross getState() {
		if (needToDecross())
			return instance;
		return null;
	}

	public void action() {
		myFighterBuilder.deletecross();
	}

	private boolean needToDecross() {
		return (e.getSource()==myFighterBuilder.getComponentPanel()&&myFighterBuilder.getComponentPanel().find(e.getPoint()) == -1)
				|| (e.getSource()==myFighterBuilder.getDrawPane()&&myModel.findComponent(e.getPoint()) == null);
	}
}