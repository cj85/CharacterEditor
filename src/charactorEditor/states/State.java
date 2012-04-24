package charactorEditor.states;

public interface State{
	abstract void creat();
	abstract State getState();
	abstract void action();
}