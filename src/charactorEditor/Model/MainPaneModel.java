package charactorEditor.Model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class MainPaneModel {
	private MyComponent draging = null;
	private MyComponent dragingSize = null;
	private ArrayList<MyComponent> mySelectedComponent = new ArrayList<MyComponent>();
	private boolean dragingCMP = false;
	private Rectangle2D put = new Rectangle2D.Double();// /∑≈÷√æÿ–Œ
	private Point2D.Double nearest = new Point2D.Double(40, 40);// nearest point
	private Rectangle2D mySelectingRectangle;

	public static int ROW = 59;// y coordinate
	public static int COL = 70;// x coordinate
	public static Point2D.Double[][] point = new Point2D.Double[COL][ROW];
	public static Line2D[] rows = new Line2D.Double[59];
	public static Line2D[] cols = new Line2D.Double[70];

	private MyComponent focusCMP = null;
	private MyComponent next_focusCMP = null;
	private static MainPaneModel instance;
	private Model myModel;
	static {
		for (int x = 0; x < COL; x++) {
			for (int y = 0; y < ROW; y++) {
				point[x][y] = new Point2D.Double(x * 10, y * 10);
			}
		}
		for (int i = 0; i < COL; i++) {
			cols[i] = new Line2D.Double(i * 10, 0, i * 10, 580);
		}
		for (int i = 0; i < ROW; i++) {
			rows[i] = new Line2D.Double(0, i * 10, 690, i * 10);
		}
	}

	public static MainPaneModel Instance() {
		if (instance == null)
			instance = new MainPaneModel();
		return instance;
	}

	private MainPaneModel() {
		myModel = Model.Instance();

	}

	public ArrayList<MyComponent> getSelectedComponnet() {
		return mySelectedComponent;
	}

	public boolean isDragingComponent() {
		return (draging != null && dragingCMP == true);
	}

	public boolean isDragSize() {
		return (draging == null && dragingSize != null);
	}

	public boolean isSelectingComponent() {
		return (draging == null && dragingSize == null);
	}

	public boolean isDraging() {
		return draging != null;
	}

	public void setComponents() {
		mySelectedComponent.add(draging);
		for (MyComponent m : mySelectedComponent) {
			Point2D p = new Point2D.Double(m.getBorderX(), m.getBorderY());
			setPutFrame(p);
			getNearestPoint();
			m.setLocation(nearest, 0);
		}
		mySelectedComponent.remove(draging);
	}

	public void setSize(Point2D p) {
		setPutFrame(p);
		getNearestPoint();
		dragingSize.setSize(nearest);
	}

	public void setPutFrame(Point2D p) {
		((Rectangle2D) put).setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
	}

	public boolean getNearestPoint() {
		for (int x = 0; x < COL; x++) {
			for (int y = 0; y < ROW; y++) {
				if (put.contains(point[x][y])) {
					nearest = point[x][y];
					return true;
				}
			}
		}
		return false;
	}

	public void clear() {
		draging = null;
		dragingSize = null;
		dragingCMP = false;
		mySelectingRectangle = null;
	}

	public void setFoucsComponent(MyComponent toSet) {
		focusCMP = toSet;
	}

	public MyComponent getFoucsComponent() {
		return focusCMP;
	}

	public void resetFoucsComponent() {
		focusCMP = null;
	}

	public void resetNextFoucsComponent() {
		next_focusCMP = null;
	}

	public void selectComponent(Rectangle2D mySelectingRectangle) {
		mySelectedComponent = new ArrayList<MyComponent>();
		if (myModel.getComponentList().size() != 0
				&& mySelectingRectangle != null) {
			for (MyComponent m : myModel.getComponentList())
				if (mySelectingRectangle.contains(m.getBorder())) {
					mySelectedComponent.add(m);
				}
		}
	}

	public MyComponent setDragingSize(MyComponent toSet) {
		dragingSize = toSet;
		return toSet;
	}

	public MyComponent getDragingSize() {
		return dragingSize;
	}

	public void setDraging(MyComponent toSet) {
		draging = toSet;
	}

	public MyComponent getNextFocusComponent() {
		return next_focusCMP;
	}

	public void setNextFocusComponnet(MyComponent toSet) {
		next_focusCMP = toSet;
	}

	public void removeCMP(MyComponent e) {

		myModel.getComponentList().remove(e);
		mySelectedComponent.remove(e);
		e.getOutofTree();
	}

	public Point2D.Double getNearest() {
		return nearest;
	}

	public void creatSelectingRectangle() {
		mySelectingRectangle = new Rectangle2D.Double();
		mySelectingRectangle.setFrame(put);
	}

	public boolean noSelectingRectangle() {
		return mySelectingRectangle == null;
	}

	public boolean isDragingSize() {
		return !isDraging() && dragingSize != null;
	}

	public boolean isDragingRectangle() {
		return !isDragingSize() && mySelectingRectangle != null;
	}

	public void dragRectangle(Point2D p) {
		double x = mySelectingRectangle.getX();
		double y = mySelectingRectangle.getY();
		if (p.getX() - x > 10 && p.getY() - y > 10) {
			mySelectingRectangle.setFrame(x, y, Math.abs(p.getX() - x),
					Math.abs(p.getY() - y));
		} else {
			mySelectingRectangle.setFrame(x, y, 10, 10);
		}
	}

	public void dragSize(Point2D p) {
		dragingSize.setSize(p);
		dragingCMP = true;
	}

	public Rectangle2D getSelectingRectangle() {
		return mySelectingRectangle;
	}

	public void dragingComponent(Point2D currentpoint) {

		double x = draging.getBorderX();
		double y = draging.getBorderY();
		draging.setLocation(currentpoint, 1);
		double dx;
		double dy;
		dx = draging.getBorderX() - x;
		dy = draging.getBorderY() - y;

		if (mySelectedComponent.contains(draging))
			for (MyComponent m : mySelectedComponent) {
				if (m != draging) {
					Rectangle2D p = new Rectangle2D.Double(
							m.getBorderX() + dx, m.getBorderY() + dy,
							m.getBorderWidth(), m.getBorderHeight());
					m.setBorderFrame(p);
				}
			}
		dragingCMP = true;
	}

}