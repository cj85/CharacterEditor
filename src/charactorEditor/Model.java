package charactorEditor;

import java.util.ArrayList;

import charactorEditor.drag.MyComponent;
import charactorEditor.drag.Sort;

public class Model {
	private ArrayList<MyComponent> componentList = new ArrayList<MyComponent>();
	public ArrayList<MyComponent> myConnectedComponent = new ArrayList<MyComponent>();
	public boolean setSizeFlag = false;
	public int willPut = -1;// component that will be put in MainPane
	public MyComponent focusCMP = null;
	public MyComponent next_focusCMP = null;
	public boolean next = false;
	public final Sort SORT = new Sort();
	public ArrayList<MyComponent> getComponnetList() {
		return componentList;
	}
	public void load(ArrayList<MyComponent> t){
		componentList=t;
	}
}