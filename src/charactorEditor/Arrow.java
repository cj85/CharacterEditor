package charactorEditor;

import charactorEditor.Model.MyComponent;

import java.awt.*;

/**
 * 
 * ������Swing�ؼ�֮�仭��ͷ
 * 
 * @author <a:href = "mailto:seth_yang@21cn.com">seth yang</a>
 */
public class Arrow {
	/** Դ�ؼ�������ͷ�ĳ����ؼ� */
	private MyComponent source;

	/** Ŀ��ؼ�������ͷ��ָ��ؼ� */
	private MyComponent target;

	/** ��ͷ�м���ı���ͨ���Ǹü�ͷ��ʶ�Ĺ�ϵ������ */
	private String name = "";

	/** Դ�ؼ�������ȥ���ֶ� */
	private String sourceField = "";

	/** Ŀ��ؼ��Ĺ����ֶ� */
	private String targetField = "";

	/** ��ͷ���εĿ�ʼ�Ƕ� */
	private int startAngle = 0;
	/** ��ͷ���εİ뾶 */
	private static final int ARROW_HEIGHT = 20;

	/** ��ͷ���εĽǶ� */
	private static final int ARROW_ANGLE = 30;

	public Arrow(MyComponent source, MyComponent target) {
		this("", source, target);

	}

	/**
	 * 
	 * ���캯��
	 * 
	 * 
	 * 
	 * ����ָ�����ƣ�Դ�ؼ���Ŀ��ؼ��ļ�ͷ
	 * 
	 * @param name
	 *            ��ͷ������
	 * 
	 * @param source
	 *            ��ͷ�ĳ����ؼ�
	 * 
	 * @param target
	 *            ��ͷ��ָ��ؼ�
	 */
	public Arrow(String name, MyComponent source, MyComponent target) {
		this(name, source, target, "", "");

	}

	/**
	 * 
	 * ���캯��
	 * 
	 * 
	 * 
	 * ����ָ�����ƣ�Դ�ؼ���Ŀ��ؼ��ļ�ͷ
	 * 
	 * @param name
	 *            ��ͷ������
	 * 
	 * @param source
	 *            ��ͷ�ĳ����ؼ�
	 * 
	 * @param target
	 *            ��ͷ��ָ��ؼ�
	 * 
	 * @param sourceField
	 *            ��ͷ�ĳ����ֶ�
	 * 
	 * @param targetField
	 *            ��ͷ��ָ���ֶ�
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
		Point pt = getCenter(target);
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
		int descent = fm.getDescent(); // ���������ĵ㴦��ʾ����
		int mx = (ps.x + pt.x) / 2;
		int my = (ps.y + pt.y) / 2;
		g.drawString(name, mx, my + ascent);
		if (sourceField == null)
			sourceField = "";
		if (targetField == null)
			targetField = "";
		if (ps.y < pt.y) {// Դ��Ŀ���Ϸ���Ŀ������Ӧ���ڸ�����һЩ��Դ����Ӧ�ø�����һЩ
			if (ps.x > pt.x) // Ŀ����Դ����
				g.drawString(getTargetField(),
						pt.x - fm.stringWidth(getTargetField()), pt.y - ascent
								- descent);
			else
				g.drawString(getTargetField(), pt.x, pt.y - ascent - descent);
			g.drawString(getSourceField(), ps.x, ps.y + ascent);
		} else {
			if (ps.x > pt.x) // Ŀ����Դ����
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
	 *            Դ�ؼ�
	 * 
	 * @param target
	 *            Ŀ�Ŀؼ�
	 */
	@SuppressWarnings("unused")
	private Point getClosestPoint(MyComponent source, MyComponent target) {
		Point ps = getCenter(source);

		Point pt = getCenter(target);
		if (ps.x == pt.x) { // ��ֱ��
			if (ps.y < pt.y) {// Դ��Ŀ���Ϸ�
				this.startAngle = 90 - ARROW_ANGLE / 2;
				return new Point(ps.x, target.getBorderY());
			}
			startAngle = 270 - ARROW_ANGLE / 2;
			return new Point(ps.x, target.getBorderY()
					+ target.getBorderHeight());
		}
		if (Math.abs(ps.y - pt.y) < 15) { // ˮƽ��
			if (ps.x < pt.x) {// Դ��Ŀ�����
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
		} // ȡ��ˮƽ���ཻ�ĵ�

		DPoint[] pts = new DPoint[4];

		// ȡ�ϱߵĵ�

		DPoint pq = new DPoint((int) ((yt - b) / k0), yt, DPoint.D_H);

		pts[0] = pq; // ȡ�±ߵĵ�

		pq = new DPoint((int) ((yt + h - b) / k0), yt + h, DPoint.D_H);

		pts[1] = pq;

		// ȡ��ߵĵ�

		pq = new DPoint(xt, (int) (k0 * xt + b), DPoint.D_V);

		pts[2] = pq;

		// ȡ�ұߵĵ�

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
		if (p1.getD() == DPoint.D_H) { // ˮƽ��

			if (Math.abs(p1.y - ps.y) < Math.abs(p2.y - ps.y))

				return p1;

			return p2;

		} else { // ��ֱ��

			if (Math.abs(p1.x - ps.x) < Math.abs(p2.x - ps.x))

				return p1;

			return p2;

		}
	}

	/**
	 * 
	 * ��ȡ�߶ε�б��
	 * 
	 * 
	 * 
	 * <i>ע�⣬�÷��������׳��쳣����p1.x = p2.xʱ���׳���0�����쳣</i>
	 * 
	 * @param p1
	 *            �߶εĶ˵�
	 * 
	 * @param p2
	 *            �߶ε���һ���˵�
	 * 
	 * @return �߶ε�б��
	 */
	private double getK(Point p1, Point p2) {
		return ((double) p2.y - p1.y) / (p2.x - p1.x);

	}

	/**
	 * 
	 * ��ȥֱ�߷����е�bֵ
	 * 
	 * @param ps
	 *            �߶εĶ˵�
	 * 
	 * @param pt
	 *            �߶ε���һ���˵�
	 * 
	 * @return ֱ�߷����е�bֵ
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
