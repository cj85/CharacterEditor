package charactorEditor.drag;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

class MyPoint {
	Point2D.Double point = null;
	Color cor = Color.orange;
	Ellipse2D circle = new Ellipse2D.Double();

	MyPoint(Point2D.Double p) {
		point = p;
		double x = p.getX();
		double y = p.getY();
		circle.setFrameFromCenter(x, y, x + 2, y + 2);
	}

	void draw(Graphics2D g) {
		g.setColor(Color.pink);
		g.draw(circle);
	}
}


