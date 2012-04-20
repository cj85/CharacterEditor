package charactorEditor.drag;

import java.awt.Graphics2D;
import java.awt.geom.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import SpriteTree.LimbNode;

public class MyComponent {
	int sortID = 0;
	public String text = null;
	public File img = null;
	public int sort = -1;
	Rectangle2D.Double border = null;
	Rectangle2D.Double dragSize = null;
	private HashMap<String, String> properties = new HashMap<String, String>();
	MyComponent parent = null;
	ArrayList<MyComponent> children = new ArrayList<MyComponent>();
	private LimbNode myLimbNode;

	MyComponent(Point2D.Double p, int theSort) {
		sort = theSort;
		border = new Rectangle2D.Double(p.getX() - 20, p.getY() - 10, 40, 20);
		dragSize = new Rectangle2D.Double(border.getMaxX() - 10,
				border.getMaxY() - 10, 10, 10);
	}

	void setText(String t) {
		text = t;
	}

	void setLocation(Point2D p, int indicator) {
		double x = p.getX();
		double y = p.getY();
		double w = border.getWidth();
		double h = border.getHeight();
		double t1 = w / 2;
		double t2 = h / 2;
		if (t1 % 10 == 5) {
			t1 += 5;
		}
		if (t2 % 10 == 5) {
			t2 += 5;
		}
		border.setFrame(x - t1 * indicator, y - t2 * indicator, w, h);
		dragSize = new Rectangle2D.Double(border.getMaxX() - 10,
				border.getMaxY() - 10, 10, 10);
	}

	void setSize(Point2D p) {
		double x = border.getX();
		double y = border.getY();
		if (p.getX() - x > 10 && p.getY() - y > 10) {
			border.setFrame(x, y, p.getX() - x, p.getY() - y);
			dragSize = new Rectangle2D.Double(border.getMaxX() - 10,
					border.getMaxY() - 10, 10, 10);
		} else {
			border.setFrame(x, y, 10, 10);
			dragSize = new Rectangle2D.Double(x, y, 10, 10);
		}
	}

	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

	public HashMap<String, String> getProperties() {
		return properties;
	}

	public void remove(String key) {
		if (properties.keySet().contains(key))
			properties.remove(key);
	}

	public void draw(Graphics2D g) {
		if (children.size() != 0) {
			for (MyComponent c : children) {
				g.drawLine((int) border.getCenterX(),
						(int) border.getCenterY(), (int) c.border.getCenterX(),
						(int) c.border.getCenterY());
				c.draw(g);
			}
		}
	}

	public void getOutofTree() {
		if (parent != null) {
			if (children.size() != 0)
				for (MyComponent m : children) {
					parent.children.add(m);
					m.parent = parent;
				}
			parent.children.remove(this);
		}
	}

}