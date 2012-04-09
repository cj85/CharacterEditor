package charactorEditor.drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

class MainPane extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 199L;
	private Graphics2D g = null;
	private FighterBuilder outer = null;
	private AttributePane attributePane = null;
	private Color b = new Color(100, 200, 100);
	private final int ROW = 59;// y coordinate
	private final int COL = 70;// x coordinate
	private MyPoint[][] point = new MyPoint[COL][ROW];
	private Line2D[] rows = new Line2D.Double[59];
	private Line2D[] cols = new Line2D.Double[70];
	private Point2D nearest = new Point2D.Double(40, 40);// nearest point
	private Rectangle2D put = new Rectangle2D.Double();// /放置矩形
	private MyComponent draging = null;
	private MyComponent dragingSize = null;
	private boolean dragingCMP = false;

	MainPane(FighterBuilder e) {
		for (int x = 0; x < COL; x++) {

			for (int y = 0; y < ROW; y++) {
				point[x][y] = new MyPoint(new Point2D.Double(x * 10, y * 10));
			}
		}
		for (int i = 0; i < COL; i++) {
			cols[i] = new Line2D.Double(i * 10, 0, i * 10, 580);
		}

		for (int i = 0; i < ROW; i++) {
			rows[i] = new Line2D.Double(0, i * 10, 690, i * 10);
		}
		outer = e;
		attributePane = e.attributePane;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.white);
	}

	public void paintComponent(Graphics e) {
		super.paintComponent(e);
		g = (Graphics2D) e;
		drawLine(g);// 画格子
		g.setColor(b);
		try {
			outer.drawViewComponent(g);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void drawLine(Graphics2D g) {
		g.setColor(new Color(200, 200, 210));
		for (int i = 0; i < COL; i++) {
			g.draw(cols[i]);
		}
		for (int i = 0; i < ROW; i++) {
			g.draw(rows[i]);
		}
	}

	boolean getNearestPoint() {
		for (int x = 0; x < COL; x++) {
			for (int y = 0; y < ROW; y++) {

				if (put.contains(point[x][y].point)) {
					// System.out.println(y);
					nearest = point[x][y].point;
					return true;
				}
			}
		}
		return false;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		attributePane.label_1.requestFocus();
		int count = e.getClickCount();
		if (outer.willPut == -1) {
			if ((dragingSize = outer.findComponent(e.getPoint())) != null) {
				if (count < 2) {
					if (outer.setSizeFlag == true)// 边缘拖拽
					{
						outer.focusCMP = dragingSize;
					} else// 点在中间了
					{
						outer.focusCMP = dragingSize;
						draging = dragingSize;
						dragingSize = null;

					}
					attributePane.label_1.setText("   "
							+ outer.SORT.CMP[outer.focusCMP.sort]);
					attributePane.text_1.setText(outer.focusCMP.name);
					attributePane.text_2.setText(outer.focusCMP.text);
					int st = outer.focusCMP.sort;
					update();
					setPropertyCombo();
				} else// 两下，取消
				{
					outer.removeCMP(outer.focusCMP);
					attributePane.label_1.setText("");
					attributePane.text_1.setText("");
					attributePane.text_2.setText("");
					attributePane.cbo_1.removeAllItems();
					attributePane.repaint();
				}
			}

			else// 没选上component 就啥也不干
			{
			}
		} else// 第一次放置进来才走这里
		{
			Point2D p = e.getPoint();
			put.setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
			getNearestPoint();
			outer.cmp.add((outer.focusCMP = new MyComponent(nearest,
					outer.willPut, outer.maxID, outer)));
			outer.maxID++;
			outer.willPut = -1;
			attributePane.label_1.setText("   "
					+ outer.SORT.CMP[outer.focusCMP.sort]);
			attributePane.text_1.setText(outer.focusCMP.name);
			attributePane.text_2.setText(outer.focusCMP.text);
			int st = outer.focusCMP.sort;
			setPropertyCombo();
			outer.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {

		if (draging != null)// 点中间了 ,移动位置
		{
			Point2D p = e.getPoint();
			((Rectangle2D) put).setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
			getNearestPoint();
			if (dragingCMP == true) {
				draging.setLocation(nearest);

			}
		} else if (dragingSize != null)// 点左下角了，拉大小
		{
			Point2D p = e.getPoint();
			((Rectangle2D) put).setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
			getNearestPoint();
			dragingSize.setSize(nearest);
		}
		// }
		draging = null;
		dragingSize = null;
		// outer.dragRange = false;
		dragingCMP = false;
		repaint();
	}

	public void mouseDragged(MouseEvent e) {
		if (draging != null) {
			draging.setLocation(e.getPoint());
			dragingCMP = true;
			outer.repaint();
		} else if (dragingSize != null) {
			dragingSize.setSize(e.getPoint());
			dragingCMP = true;
			outer.repaint();
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (outer.findComponent(e.getPoint()) != null) {
			if (outer.setSizeFlag == true) {
				outer.changesize();
			} else {
				outer.cross();
			}
		} else {
			outer.deletecross();
		}
	}

	private void setPropertyCombo() {
		attributePane.cbo_1.removeAllItems();
		for (String s : outer.properties) {
			attributePane.cbo_1.addItem(s);
		}
	}
	private void update(){
		attributePane.label_8update();
	}
}