package charactorEditor.Model;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import charactorEditor.drag.MyComponent;
import charactorEditor.drag.Sort;

public class Model {
	private ArrayList<MyComponent> componentList = new ArrayList<MyComponent>();
	public ArrayList<MyComponent> myConnectedComponent = new ArrayList<MyComponent>();
	public boolean setSizeFlag = false;
	public int willPut = -1;// component that will be put in MainPane
	public boolean next = false;
	public final Sort SORT = new Sort();
	private ArrayList<String> properties = new ArrayList<String>();
     private static Model instance;
     public static Model Instance(){
    	 if(instance==null)
    		 instance=new Model();
    	 return instance;
     }
	private Model() {
		try {
			loadPropertyList();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MyComponent> getComponnetList() {
		return componentList;
	}

	public void load(ArrayList<MyComponent> t) {
		componentList = t;
	}

	public MyComponent findComponent(Point2D e) {
		for (MyComponent m : componentList) {
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

	public ArrayList<String> getProperties() {
		return properties;
	}

	@SuppressWarnings("unchecked")
	public void setProperties(Object toSet) {
		properties = (ArrayList<String>) toSet;
	}

	@SuppressWarnings("unchecked")
	private void loadPropertyList() throws FileNotFoundException {
		Gson gson = new Gson();
		Scanner scanner = new Scanner(new File("Properties.json"));
		String wholeFile = scanner.useDelimiter("\\A").next();
		java.lang.reflect.Type collectionType = new TypeToken<ArrayList<String>>() {
		}.getType();
		setProperties(gson.fromJson(wholeFile, collectionType));
	}
}