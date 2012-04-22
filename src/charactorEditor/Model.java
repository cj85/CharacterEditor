package charactorEditor;

import java.awt.geom.Point2D;
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
	public ArrayList<String> properties = new ArrayList<String>();

	public ArrayList<MyComponent> getComponnetList() {
		return componentList;
	}

	public void load(ArrayList<MyComponent> t) {
		componentList = t;
	}
	
	public MyComponent findComponent(Point2D e) {
		for (MyComponent m:componentList) {
			if (m.dragSize.contains(e)) {
				setSizeFlag = true;
				return m;
			}
			if (m.border.contains(e)) {
				setSizeFlag = false;
				return m;
			}
			
		}
		return null;
	}
	public String getName(MyComponent myComponent) {
		int count = 1;
		for (int i = 0; i < componentList.size(); i++) {
			if (componentList.get(i).sort == myComponent.sort) {
				if (count <= componentList.get(i).sortID) {
					count = componentList.get(i).sortID + 1;
				}
			}
		}
		myComponent.sortID = count;
		return SORT.NAME[myComponent.sort] + count;
	}
}