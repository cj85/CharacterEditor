package charactorEditor.drag;

import java.awt.Graphics2D;
import java.awt.geom.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import SpriteTree.LimbNode;

public class MyComponent {
	private int sortID = 0;
	private String text = null;
	private File img = null;
	private int sort = -1;
	private Rectangle2D.Double border = null;
	private Rectangle2D.Double dragSize = null;
	private HashMap<String, String> properties = new HashMap<String, String>();
	private MyComponent parent = null;
	private ArrayList<MyComponent> children = new ArrayList<MyComponent>();
	private LimbNode myLimbNode;

	public MyComponent(Point2D.Double p, int theSort) {
		sort = theSort;
		border = new Rectangle2D.Double(p.getX() - 20, p.getY() - 10, 40, 20);
		dragSize = new Rectangle2D.Double(border.getMaxX() - 10,
				border.getMaxY() - 10, 10, 10);
	}

	public void setText(String t) {
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

	public void drawTree(Graphics2D g) {
		if (children.size() != 0) {
			for (MyComponent c : children) {
				g.drawLine((int) border.getCenterX(),
						(int) border.getCenterY(), (int) c.border.getCenterX(),
						(int) c.border.getCenterY());
				c.drawTree(g);
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

	public void setParent(MyComponent toSet) {
		parent = toSet;
	}

	public void addChild(MyComponent toAdd) {
		children.add(toAdd);
	}
   
	public void  setImg(File img){
		this.img=img;
	}
	public File getImg(){
		return img;
	}
	
	public boolean hasNoImg(){
		return img==null;
	}
	public boolean isRoot() {
		return (parent == null);
	}
	public Rectangle2D.Double getBorder(){
		return border;
	}
	public int getBorderX(){
		return (int) border.getX();
	}
	public int getBorderY(){
		return (int) border.getY();
	}
	public int getBorderWidth(){
		return (int) border.getWidth();
	}
	public int getBorderHeight(){
		return (int) border.getHeight();
	}
	public Rectangle2D getBorderBounds2D(){
		return border.getBounds2D();
	}
	public String getText(){
		return text;
	}
	
	public void setBorderFrame(Rectangle2D p){
		border.setFrame(p);
	}
	public int getSort(){
		return sort;
	}
	public boolean isInDragSize(Point2D p){
		return dragSize.contains(p);
	}
	public boolean isInBorder(Point2D p){
		return border.contains(p);
	}
	public int getSortID(){
		return sortID;
	}
	public void setSortID(int toSet){
		sortID=toSet;
	}
}