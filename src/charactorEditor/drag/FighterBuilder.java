package charactorEditor.drag;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.event.*;

import com.google.gson.Gson;

import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class FighterBuilder extends JFrame implements ChangeListener {
	final int NUM = 2;
	ArrayList<String> properties = new ArrayList<String>();
	final Sort SORT = new Sort();
	final CmpSort SORTSTRING = new CmpSort();
	MyComponentPanel componentPane = null;
	JPanel centerPane = null;
	AttributePane attributePane = new AttributePane(this);
	JTabbedPane tab = new JTabbedPane();
	MainPane drawPane = new MainPane(this);

	ArrayList<MyComponent> cmp = new ArrayList<MyComponent>(); // components
																// that be added
																// on Main Pain

	// boolean dragRange = false;

	boolean setSizeFlag = false;

	int willPut = -1;// component that will be put in MainPane

	int maxID = 1;
	MyComponent focusCMP = null;
	Color B = new Color(100, 200, 100);

	void removeCMP(MyComponent e) {
		for (int i = 0; i < cmp.size(); i++) {
			if (cmp.get(i) == this.focusCMP) {
				focusCMP = null;
				cmp.remove(i);
				break;
			}
		}
	}

	void drawViewComponent(Graphics2D g) throws IOException {
		for (int i = 0; i < cmp.size(); i++) {
			if (cmp.get(i) == focusCMP) {
				g.setColor(Color.orange);
				g.fill(cmp.get(i).border);
				g.setColor(B);
			} else {
				g.fill(cmp.get(i).border);
			}
			if (cmp.get(i).img != null) {
				BufferedImage img = ImageIO.read(cmp.get(i).img);

				g.drawImage(img, (int) cmp.get(i).border.getX(),
						(int) cmp.get(i).border.getY(),
						(int) cmp.get(i).border.getWidth(),
						(int) cmp.get(i).border.getHeight(), null);

			}
		}
		g.setColor(Color.white);
		for (int i = 0; i < cmp.size(); i++) {
			Rectangle2D tem = cmp.get(i).border.getBounds2D();
			g.drawString(cmp.get(i).text, (int) tem.getX(), (int) (tem.getY()
					+ tem.getHeight() / 2 + 5));
		}

	}

	boolean setCmpName(MyComponent me, String name) {
		if (me == null) {
			return false;
		}
		for (int i = 0; i < cmp.size(); i++) {
			if (me != cmp.get(i)) {
				if (name.equals(cmp.get(i).name)) {
					return false;
				}
			}
		}
		me.name = name;
		return true;
	}

	void setText(MyComponent me, String t) {
		if (me != null)
			me.text = t;
	}

	public FighterBuilder() throws FileNotFoundException {
		this.setTitle("FighterBuilder");
		Gson gson = new Gson();
		Scanner scanner2 = new Scanner(new File("Properties.json"));
		String wholeFile2 = scanner2.useDelimiter("\\A").next();
		java.lang.reflect.Type collectionType2 = new TypeToken<ArrayList<String>>() {
		}.getType();

		properties = gson.fromJson(wholeFile2, collectionType2);

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
		for (int i = 0; i < cmp.size(); i++) {
			if (cmp.get(i).dragSize.contains(e)) {
				setSizeFlag = true;
				return cmp.get(i);
			}
			if (cmp.get(i).border.contains(e)) {
				setSizeFlag = false;
				return cmp.get(i);
			}
		}
		return null;
	}

	String getName(MyComponent myComponent) {
		int count = 1;
		for (int i = 0; i < cmp.size(); i++) {
			if (cmp.get(i).sort == myComponent.sort) {
				if (count <= cmp.get(i).sortID) {
					count = cmp.get(i).sortID + 1;
				}
			}
		}
		myComponent.sortID = count;
		return SORT.NAME[myComponent.sort] + count;
	}

	public void stateChanged(ChangeEvent e) {
		// TODO i dont know

	}

}
