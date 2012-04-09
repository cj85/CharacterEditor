package charactorEditor.drag;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.*;
import java.io.File;
import java.util.HashMap;

class MyComponent {
	int id = 0;
	int sortID = 1;
	String name = null;
	String text = null;
	 File img=null;
	int sort = -1;
	Rectangle2D border = null;
	Point2D point = null;
	Rectangle2D dragSize = null;
	FighterBuilder outer = null;
    private HashMap<String,String> properties=new HashMap<String,String>();
	MyComponent(Point2D p, int theSort, int ID, FighterBuilder out) {
		point = p;
		sort = theSort;
		id = ID;
		outer = out;
		border = new Rectangle2D.Double(p.getX() - 20, p.getY() - 10, 40, 20);
		dragSize = new Rectangle2D.Double(border.getMaxX() - 10,
				border.getMaxY() - 10, 10, 10);
		setName();
		setText(name);
		//
	}

	void setName() {
		name = outer.getName(this);
	}

	void setText(String t) {
		text = t;
	}

	void setLocation(Point2D p) {
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
		border.setFrame(x - t1, y - t2, w, h);
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
	public void setProperty(String key,String value){
		properties.put(key, value);
	}
	public HashMap<String,String> getProperties(){
		return properties;
	}
	public void remove(String key){
		if(properties.keySet().contains(key))
			properties.remove(key);
	}
}