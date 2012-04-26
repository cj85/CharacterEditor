package charactorEditor.states;

public interface State{
	abstract void create();
	abstract State getState();
	abstract void action();
}