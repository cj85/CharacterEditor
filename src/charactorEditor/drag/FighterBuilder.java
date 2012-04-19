package charactorEditor.drag;

import javax.swing.*;

import javax.swing.event.*;

import com.google.gson.Gson;

import java.awt.geom.*;
import java.awt.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class FighterBuilder extends JFrame implements ChangeListener {
	public ArrayList<String> properties = new ArrayList<String>();
	public final Sort SORT = new Sort();
	MyComponentPanel componentPane = null;
	JPanel centerPane = null;
	AttributePane attributePane = new AttributePane(this);
	JTabbedPane tab = new JTabbedPane();
	MainPane drawPane = new MainPane(this);

	public ArrayList<MyComponent> componentList = new ArrayList<MyComponent>();
	public ArrayList<MyComponent> myConnectedComponent = new ArrayList<MyComponent>();
	boolean setSizeFlag = false;
	int willPut = -1;// component that will be put in MainPane
	public MyComponent focusCMP = null;

	public FighterBuilder() throws FileNotFoundException {
		setTitle("FighterBuilder");
		loadPropertyList();

		componentPane = new MyComponentPanel(this);
		this.setDefaultCloseOperation(3);
		setBounds(10, 50, 1000, 645);
		JPanel jp = new JPanel();
		jp.setLayout(null);
		init();
		jp.add(componentPane);
		jp.add(centerPane);
		jp.add(attributePane);
		setContentPane(jp);
		setVisible(true);
		setResizable(false);
	}

	private void init() {

		centerPane = new JPanel();
		centerPane.setBounds(115, 0, 700, 615);
		centerPane.setLayout(new BorderLayout());
		tab.add("View", drawPane);
		tab.addChangeListener(this);
		centerPane.add(tab);
	}

	MyComponent findComponent(Point2D e) {
		for (int i = 0; i < componentList.size(); i++) {
			if (componentList.get(i).dragSize.contains(e)) {
				setSizeFlag = true;
				return componentList.get(i);
			}
			if (componentList.get(i).border.contains(e)) {
				setSizeFlag = false;
				return componentList.get(i);
			}
		}
		return null;
	}

	String getName(MyComponent myComponent) {
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

	public void stateChanged(ChangeEvent e) {

	}

	private void loadPropertyList() throws FileNotFoundException {
		Gson gson = new Gson();
		Scanner scanner2 = new Scanner(new File("Properties.json"));
		String wholeFile2 = scanner2.useDelimiter("\\A").next();
		java.lang.reflect.Type collectionType2 = new TypeToken<ArrayList<String>>() {
		}.getType();
		properties = gson.fromJson(wholeFile2, collectionType2);
	}

	public void changesize() {
		setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
	}

	public void cross() {
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void deletecross() {
		setCursor(Cursor.getDefaultCursor());
	}
}
