package charactorEditor.drag;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class MainPane extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 199L;
	private Graphics2D g = null;
	private FighterBuilder outer;
	private Color b = new Color(100, 200, 100);
	private final int ROW = 59;// y coordinate
	private final int COL = 70;// x coordinate
	private Point2D.Double[][] point = new Point2D.Double[COL][ROW];
	private Line2D[] rows = new Line2D.Double[59];
	private Line2D[] cols = new Line2D.Double[70];
	private Point2D.Double nearest = new Point2D.Double(40, 40);// nearest point
	private Rectangle2D put = new Rectangle2D.Double();// /放置矩形
	private MyComponent draging = null;
	private MyComponent dragingSize = null;
	private boolean dragingCMP = false;
	MainPane(FighterBuilder e) {
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
		outer = e;
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.white);
	}

	public void paintComponent(Graphics e) {
		super.paintComponent(e);
		g = (Graphics2D) e;
		drawLine(g);// 画格子
		g.setColor(b);
		try {
			drawViewComponent(g);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void drawLine(Graphics2D g) {
		g.setColor(new Color(200, 200, 210));
		for (int i = 0; i < COL; i++) {
			g.draw(cols[i]);
		}
		for (int i = 0; i < ROW; i++) {
			g.draw(rows[i]);
		}
	}

	private boolean getNearestPoint() {
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

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
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
					update();
				} else// 两下，取消
				{
					removeCMP(outer.focusCMP);
					update();
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
			outer.componentList.add((outer.focusCMP = new MyComponent(nearest,
					outer.willPut, outer.maxID, outer)));
			outer.focusCMP.setText(outer.getName(outer.focusCMP));
			outer.maxID++;
			outer.willPut = -1;
			update();
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
		draging = null;
		dragingSize = null;
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

	void drawViewComponent(Graphics2D g) throws IOException {
		for (int i = 0; i < outer.componentList.size(); i++) {
			if (outer.componentList.get(i) == outer.focusCMP) {
				g.setColor(Color.orange);
				g.fill(outer.componentList.get(i).border);
				g.setColor(new Color(100, 200, 100));
			} else {
				g.fill(outer.componentList.get(i).border);
			}
			if (outer.componentList.get(i).img != null) {
				BufferedImage img = ImageIO.read(outer.componentList.get(i).img);

				g.drawImage(img, (int) outer.componentList.get(i).border.getX(),
						(int) outer.componentList.get(i).border.getY(),
						(int) outer.componentList.get(i).border.getWidth(),
						(int)outer. componentList.get(i).border.getHeight(), null);

			}
		}
		g.setColor(Color.white);
		for (int i = 0; i < outer.componentList.size(); i++) {
			Rectangle2D tem = outer.componentList.get(i).border.getBounds2D();
			g.drawString(outer.componentList.get(i).text, (int) tem.getX(),
					(int) (tem.getY() + tem.getHeight() / 2 + 5));
		}

	}
	void removeCMP(MyComponent e) {
		System.out.println(outer.componentList==outer.componentList);////////////////////////////////
		for (int i = 0; i < outer.componentList.size(); i++) {
			if (outer.componentList.get(i) == outer.focusCMP) {
				outer.focusCMP = null;
				outer.componentList.remove(i);
				break;
			}
		}
	}
	public void update() {
		outer.attributePane.update();
	}
}