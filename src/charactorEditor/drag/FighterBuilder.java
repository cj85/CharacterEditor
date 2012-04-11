package charactorEditor.drag;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.event.*;

import com.google.gson.Gson;

import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class FighterBuilder extends JFrame implements ChangeListener {
	ArrayList<String> properties = new ArrayList<String>();
	final Sort SORT = new Sort();
	final CmpSort SORTSTRING = new CmpSort();
	MyComponentPanel componentPane = null;
	JPanel centerPane = null;
	AttributePane attributePane = new AttributePane(this);
	JTabbedPane tab = new JTabbedPane();
	MainPane drawPane = new MainPane(this);

	public ArrayList<MyComponent> componentList = new ArrayList<MyComponent>();
	boolean setSizeFlag = false;
	int willPut = -1;// component that will be put in MainPane
	int maxID = 1;
	public MyComponent focusCMP = null;
	Color B = new Color(100, 200, 100);

	void removeCMP(MyComponent e) {
		for (int i = 0; i < componentList.size(); i++) {
			if (componentList.get(i) == this.focusCMP) {
				focusCMP = null;
				componentList.remove(i);
				break;
			}
		}
	}

	void drawViewComponent(Graphics2D g) throws IOException {
		for (int i = 0; i < componentList.size(); i++) {
			if (componentList.get(i) == focusCMP) {
				g.setColor(Color.orange);
				g.fill(componentList.get(i).border);
				g.setColor(B);
			} else {
				g.fill(componentList.get(i).border);
			}
			if (componentList.get(i).img != null) {
				BufferedImage img = ImageIO.read(componentList.get(i).img);

				g.drawImage(img, (int) componentList.get(i).border.getX(),
						(int) componentList.get(i).border.getY(),
						(int) componentList.get(i).border.getWidth(),
						(int) componentList.get(i).border.getHeight(), null);

			}
		}
		g.setColor(Color.white);
		for (int i = 0; i < componentList.size(); i++) {
			Rectangle2D tem = componentList.get(i).border.getBounds2D();
			g.drawString(componentList.get(i).text, (int) tem.getX(),
					(int) (tem.getY() + tem.getHeight() / 2 + 5));
		}

	}

	boolean setCmpName(MyComponent me, String name) {
		if (me == null) {
			return false;
		}
		for (int i = 0; i < componentList.size(); i++) {
			if (me != componentList.get(i)) {
//				if (name.equals(componentList.get(i).name)) {
//					return false;
//				}
			}
		}
//		me.name = name;
		return true;
	}

	public void setText(MyComponent me, String t) {
		if (me != null)
			me.text = t;
	}

	public FighterBuilder() throws FileNotFoundException {
		this.setTitle("FighterBuilder");
		loadPropertyList();

		componentPane = new MyComponentPanel(this);
		componentPane.initComponents();// ×ó±ß¶ùÄÇÁï
		componentPane.setBounds(0, 0, 113, 615);
		this.setDefaultCloseOperation(1);
		setBounds(10, 50, 1000, 645);
		JPanel jp = new JPanel();// ´ó¿ò¼Ü£¬×ócomponentPane ÖÐcenterPane ÓÒattribute
		jp.setLayout(null);
		init();// componentPane centerPane init
		jp.add(componentPane);
		jp.add(centerPane);

		jp.add(attributePane);
		setContentPane(jp);

		setVisible(true);
		this.setResizable(false);
	}

	private void init() {

		centerPane = new JPanel();
		centerPane.setBounds(115, 0, 700, 615);
		centerPane.setLayout(new BorderLayout());
		tab.add("View", drawPane);
		tab.addChangeListener(this);
		centerPane.add(tab);
	}

	public void cross() {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void changesize() {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
	}

	public void deletecross() {
		this.setCursor(Cursor.getDefaultCursor());
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

}
