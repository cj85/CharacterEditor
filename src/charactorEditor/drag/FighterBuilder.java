package charactorEditor.drag;

import javax.swing.*;

import javax.swing.event.*;

import charactorEditor.Controller;
import charactorEditor.Model.Model;

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

	public Model myModel = null;
	public Controller myController;
	public MyComponentPanel myComponentPanel = null;
	JPanel centerPane = null;
	public AttributePane attributePane = null;
	JTabbedPane tab = null;
	public MainPane drawPane = null;

	public FighterBuilder(Model m) throws FileNotFoundException {
		setTitle("FighterBuilder");
		myModel = m;
		myController=Controller.Instance();
		
		loadPropertyList();
		myComponentPanel = new MyComponentPanel(this);
		this.setDefaultCloseOperation(3);
		setBounds(10, 50, 1000, 645);
		JPanel jp = new JPanel();
		jp.setLayout(null);
		attributePane = new AttributePane(this);
		drawPane = new MainPane(this);
		tab = new JTabbedPane();
		init();
		
	    myController.register(this);
		jp.add(myComponentPanel);
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

	public void stateChanged(ChangeEvent e) {
	}

	@SuppressWarnings("unchecked")
	private void loadPropertyList() throws FileNotFoundException {
		Gson gson = new Gson();
		Scanner scanner2 = new Scanner(new File("Properties.json"));
		String wholeFile2 = scanner2.useDelimiter("\\A").next();
		java.lang.reflect.Type collectionType2 = new TypeToken<ArrayList<String>>() {
		}.getType();
		myModel.setProperties(gson.fromJson(wholeFile2, collectionType2));
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
