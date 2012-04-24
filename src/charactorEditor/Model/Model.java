package charactorEditor.Model;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Model {
	private ArrayList<MyComponent> componentList = new ArrayList<MyComponent>();
	private boolean setSizeFlag = false;
	private int willPut = -1;
	private boolean next = false;
	private ArrayList<String> properties = new ArrayList<String>();
	private static Model instance;

	public static Model Instance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}

	private Model() {
		try {
			loadPropertyList();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MyComponent> getComponentList() {
		return componentList;
	}

	public void load(ArrayList<MyComponent> t) {
		componentList = t;
	}

	public MyComponent findComponent(Point2D e) {
		for (MyComponent m : componentList) {
			if (m.isInDragSize(e)) {
				setSizeFlag = true;
				return m;
			}
			if (m.isInBorder(e)) {
				setSizeFlag = false;
				return m;
			}

		}
		return null;
	}

	public String getName(MyComponent myComponent) {
		int count = 1;
		for (MyComponent m : componentList) {
			if (m.getSort() == myComponent.getSort()) {
				if (count <= m.getSortID()) {
					count = m.getSortID() + 1;
				}
			}
		}
		myComponent.setSortID(count);
		return Sort.NAME[myComponent.getSort()] + count;
	}

	public ArrayList<String> getProperties() {
		return properties;
	}

	@SuppressWarnings("unchecked")
	public void setProperties(Object toSet) {
		properties = (ArrayList<String>) toSet;
	}

	public boolean isPuttingComponent() {
		return willPut == -1 ? false : true;
	}

	private void loadPropertyList() throws FileNotFoundException {
		Gson gson = new Gson();
		Scanner scanner = new Scanner(new File("Properties.json"));
		String wholeFile = scanner.useDelimiter("\\A").next();
		java.lang.reflect.Type collectionType = new TypeToken<ArrayList<String>>() {
		}.getType();
		setProperties(gson.fromJson(wholeFile, collectionType));
	}

	public int getWillPut() {
		return willPut;
	}

	public void setWillPut(int toSet) {
		willPut = toSet;
	}

	public void reSetWillPut() {
		willPut = -1;
	}

	public boolean isNext() {
		return next;
	}

	public void trueNext() {
		next = true;
	}

	public void falseNext() {
		next = false;
	}

	public boolean getSetSizeFlag() {
		return setSizeFlag;
	}
}