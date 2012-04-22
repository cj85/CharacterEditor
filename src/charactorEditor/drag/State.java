package charactorEditor.drag;

public abstract class State {
	MainPane outer = null;

	public State(MainPane mp) {
		outer = mp;
	}

	abstract void mousePressed();

}