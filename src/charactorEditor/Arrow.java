package charactorEditor;


import charactorEditor.Model.MyComponent;

import java.awt.*;

/**
 * 
 * 在两个Swing控件之间画箭头
 * 
 * @author <a:href = "mailto:seth_yang@21cn.com">seth yang</a>
 */
public class Arrow {
	/** 源控件，即箭头的出发控件 */
	private MyComponent source;

	/** 目标控件，即箭头的指向控件 */
	private MyComponent target;

	/** 箭头中间的文本，通常是该箭头标识的关系的名称 */
	private String name = "";

	/** 源控件关联出去的字段 */
	private String sourceField = "";

	/** 目标控件的关联字段 */
	private String targetField = "";

	/** 箭头扇形的开始角度 */
	private int startAngle = 0;
	/** 箭头扇形的半径 */
	private static final int ARROW_HEIGHT = 20;

	/** 箭头扇形的角度 */
	private static final int ARROW_ANGLE = 30;

	public Arrow(MyComponent source, MyComponent target) {
		this("", source, target);

	}

	/**
	 * 
	 * 构造函数
	 * 
	 * 
	 * 
	 * 创建指定名称，源控件和目标控件的箭头
	 * 
	 * @param name
	 *            箭头的名称
	 * 
	 * @param source
	 *            箭头的出发控件
	 * 
	 * @param target
	 *            箭头的指向控件
	 */
	public Arrow(String name, MyComponent source, MyComponent target) {
		this(name, source, target, "", "");

	}

	/**
	 * 
	 * 构造函数
	 * 
	 * 
	 * 
	 * 创建指定名称，源控件和目标控件的箭头
	 * 
	 * @param name
	 *            箭头的名称
	 * 
	 * @param source
	 *            箭头的出发控件
	 * 
	 * @param target
	 *            箭头的指向控件
	 * 
	 * @param sourceField
	 *            箭头的出发字段
	 * 
	 * @param targetField
	 *            箭头的指向字段
	 */
	public Arrow(String name, MyComponent source, MyComponent target,
			String sourceField, String targetField) {
		setName(name);

		setSource(source);

		setTarget(target);

		this.setSourceField(sourceField);

		this.setTargetField(targetField);

	}

	private Point getCenter(MyComponent c) {
		int x = c.getBorderX();

		int y = c.getBorderY();

		int w = c.getBorderWidth();

		int h = c.getBorderHeight();
		return new Point(x + w / 2, y + h / 2);

	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		Point ps = getCenter(source);
		Point pt =  getCenter(target);
		g.drawLine(ps.x, ps.y, pt.x, pt.y);
		if (ps.x != pt.x && ps.y != pt.y) {
			double k = getK(ps, pt);
			if (ps.x > pt.x)
				startAngle = 360 - (int) (Math.atan(k) * 180 / Math.PI) - 15;
			else
				startAngle = 180 - (int) (Math.atan(k) * 180 / Math.PI) - 15;
		}
		Point pc = new Point(pt.x - ARROW_HEIGHT, pt.y - ARROW_HEIGHT);
		g.fillArc(pc.x, pc.y, 2 * ARROW_HEIGHT, 2 * ARROW_HEIGHT, startAngle,
				ARROW_ANGLE);
		FontMetrics fm = g.getFontMetrics();
		int ascent = fm.getAscent();
		int descent = fm.getDescent(); // 在线条中心点处显示名称
		int mx = (ps.x + pt.x) / 2;
		int my = (ps.y + pt.y) / 2;
		g.drawString(name, mx, my + ascent);
		if (sourceField == null)
			sourceField = "";
		if (targetField == null)
			targetField = "";
		if (ps.y < pt.y) {// 源在目标上方，目标文字应该在更上面一些，源文字应该更下面一些
			if (ps.x > pt.x) // 目标在源的左方
				g.drawString(getTargetField(),
						pt.x - fm.stringWidth(getTargetField()), pt.y - ascent
								- descent);
			else
				g.drawString(getTargetField(), pt.x, pt.y - ascent - descent);
			g.drawString(getSourceField(), ps.x, ps.y + ascent);
		} else {
			if (ps.x > pt.x) // 目标在源的左方
				g.drawString(getTargetField(),
						pt.x - fm.stringWidth(getTargetField()), pt.y + ascent
								+ descent);
			else
				g.drawString(getTargetField(), pt.x, pt.y + ascent + descent);
			g.drawString(getSourceField(), ps.x, ps.y - descent);
		}
	}

	/**
	 * 
	 * @param source
	 *            源控件
	 * 
	 * @param target
	 *            目的控件
	 */
	@SuppressWarnings("unused")
	private Point getClosestPoint(MyComponent source, MyComponent target) {
		Point ps = getCenter(source);

		Point pt = getCenter(target);
		if (ps.x == pt.x) { // 垂直线
			if (ps.y < pt.y) {// 源在目标上方
				this.startAngle = 90 - ARROW_ANGLE / 2;
				return new Point(ps.x, target.getBorderY());
			}
			startAngle = 270 - ARROW_ANGLE / 2;
			return new Point(ps.x, target.getBorderY()
					+ target.getBorderHeight());
		}
		if (Math.abs(ps.y - pt.y) < 15) { // 水平线
			if (ps.x < pt.x) {// 源在目标左边
				startAngle = 180 - ARROW_ANGLE / 2;
				return new Point(target.getBorderX(), ps.y);
			}
			startAngle = -ARROW_ANGLE / 2;
			return new Point(target.getBorderX() + target.getBorderWidth(),
					ps.y);
		}
		double k0 = getK(ps, pt);
		double b = getB(ps, pt);
		int xt = target.getBorderX();

		int yt = target.getBorderY();

		int w = target.getBorderWidth();

		int h = target.getBorderHeight();
		class DPoint extends Point {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public static final int D_H = 0;

			public static final int D_V = 1;

			private int d;

			public DPoint(int x, int y, int d) {
				super(x, y);

				this.d = d;

			}

			public int getD() {
				return d;

			}
		} // 取于水平边相交的点

		DPoint[] pts = new DPoint[4];

		// 取上边的点

		DPoint pq = new DPoint((int) ((yt - b) / k0), yt, DPoint.D_H);

		pts[0] = pq; // 取下边的点

		pq = new DPoint((int) ((yt + h - b) / k0), yt + h, DPoint.D_H);

		pts[1] = pq;

		// 取左边的点

		pq = new DPoint(xt, (int) (k0 * xt + b), DPoint.D_V);

		pts[2] = pq;

		// 取右边的点

		pq = new DPoint(xt + w, (int) (k0 * (xt + w) + b), DPoint.D_V);

		pts[3] = pq;
		DPoint p1 = null;

		DPoint p2 = null;

		for (int i = 0; i < pts.length; i++) {
			DPoint p = pts[i];

			if (p.x < target.getBorderX()
					|| p.x > target.getBorderX() + target.getBorderWidth() ||

					p.y < target.getBorderY()
					|| p.y > target.getBorderY() + target.getBorderHeight())

				continue;

			if (p1 == null)

				p1 = p;

			else

				p2 = p;

		}
		if (p1.getD() == DPoint.D_H) { // 水平线

			if (Math.abs(p1.y - ps.y) < Math.abs(p2.y - ps.y))

				return p1;

			return p2;

		} else { // 垂直线

			if (Math.abs(p1.x - ps.x) < Math.abs(p2.x - ps.x))

				return p1;

			return p2;

		}
	}

	/**
	 * 
	 * 获取线段的斜率
	 * 
	 * 
	 * 
	 * <i>注意，该方法可能抛出异常，当p1.x = p2.x时将抛出被0除的异常</i>
	 * 
	 * @param p1
	 *            线段的端点
	 * 
	 * @param p2
	 *            线段的另一个端点
	 * 
	 * @return 线段的斜率
	 */
	private double getK(Point p1, Point p2) {
		return ((double) p2.y - p1.y) / (p2.x - p1.x);

	}

	/**
	 * 
	 * 划去直线方程中的b值
	 * 
	 * @param ps
	 *            线段的端点
	 * 
	 * @param pt
	 *            线段的另一个端点
	 * 
	 * @return 直线方程中的b值
	 */
	private double getB(Point ps, Point pt) {
		double x1 = ps.x;

		double y1 = ps.y;

		double x2 = pt.x;

		double y2 = pt.y;
		return ((double) y1 * x2 - y2 * x1) / (x2 - x1);

	}

	public MyComponent getSource() {
		return source;

	}

	public MyComponent getTarget() {
		return target;

	}

	public String getName() {
		return name;

	}

	public void setName(String name) {
		this.name = name;

	}

	public void setSource(MyComponent source) {
		this.source = source;

	}

	public void setTarget(MyComponent target) {
		this.target = target;

	}

	public String getSourceField() {
		return sourceField;

	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;

	}

	public String getTargetField() {
		return targetField;

	}

	public void setTargetField(String targetField) {
		this.targetField = targetField;

	}
}
