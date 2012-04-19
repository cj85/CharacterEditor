package charactorEditor.drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

class MainPane extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 199L;
	private Graphics2D g = null;
	private FighterBuilder outer;
	private final Color UNFOCUSED_COMPONENT_COLOR = new Color(100, 200, 100);
	private final Color FOCUSED_COMPONENT_COLOR = Color.orange;
	private final Color STRING_ON_COMPONENT_COLOR = Color.white;
	private final Color SELECTED_COMPONENT_COLOR=Color.pink;
	private final Color LINE_COLOR=new Color(200, 200, 210);
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
	private Rectangle2D mySelectingRectangle;
	private ArrayList<MyComponent> mySelectedComponent = new ArrayList<MyComponent>();

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
		drawLine(g);
		try {
			drawViewComponent(g);
			drawSelecting(g);
			drawSelected(g);
			drawConnected(g);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void drawConnected(Graphics2D g) {// ////////先没用上
		g.setColor(SELECTED_COMPONENT_COLOR);
		for (MyComponent m : outer.myConnectedComponent)
			g.draw(m.border);
	}

	private void drawLine(Graphics2D g) {
		g.setColor(LINE_COLOR);
		for (int i = 0; i < COL; i++) {
			g.draw(cols[i]);
		}
		for (int i = 0; i < ROW; i++) {
			g.draw(rows[i]);
		}
	}

	private void drawSelecting(Graphics2D g) {
		g.setColor(Color.black);
		if (mySelectingRectangle != null)
			g.draw(mySelectingRectangle);
	}

	private void drawSelected(Graphics2D g) {
		g.setColor(Color.pink);
		for (MyComponent m : mySelectedComponent)
			g.fill(m.border);
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

		JMenuItem mConnect = new JMenuItem("connect");
		JMenuItem mDisconnect = new JMenuItem("disconnect");
		JPopupMenu menu = new JPopupMenu();
		if (e.getButton() == MouseEvent.BUTTON3) {

			menu.add(mConnect);
			menu.add(mDisconnect);
			menu.show(this, e.getX(), e.getY());
		}
		mConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("connect");
			}
		});
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
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
					} else// 两下，取消
					{
						removeCMP(outer.focusCMP);
					}
					update();
				}

				else// 没选上component 就啥也不干/////////////////////////////
				{
					Point2D p = e.getPoint();
					put.setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
					mySelectingRectangle = new Rectangle2D.Double();
					mySelectingRectangle.setFrame(put);
					outer.repaint();
				}
			} else// 第一次放置进来才走这里
			{
				Point2D p = e.getPoint();
				put.setFrame(p.getX(), p.getY(), 10, 10);
				getNearestPoint();
				outer.componentList.add((outer.focusCMP = new MyComponent(
						nearest, outer.willPut)));
				outer.focusCMP.setText(outer.getName(outer.focusCMP));
				outer.willPut = -1;
				update();
				repaint();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

		if (draging != null)// 点中间了 ,移动位置
		{

			if (dragingCMP == true) {
				mySelectedComponent.add(draging);
				for (MyComponent m : mySelectedComponent) {
					Point2D p = new Point2D.Double(m.border.getX(),
							m.border.getY());
					put.setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
					getNearestPoint();
					m.setLocation(nearest, 0);
				}
				mySelectedComponent.remove(draging);
			}
		} else if (dragingSize != null)// 点左下角了，拉大小
		{
			Point2D p = e.getPoint();
			((Rectangle2D) put).setFrame(p.getX() - 5, p.getY() - 5, 10, 10);
			getNearestPoint();
			dragingSize.setSize(nearest);
		} else if (selectComponent() != 0) {

		}
		draging = null;
		dragingSize = null;
		dragingCMP = false;
		mySelectingRectangle = null;
		repaint();
	}

	public void mouseDragged(MouseEvent e) {
		if (draging != null) {

			double x = draging.border.getX();
			double y = draging.border.getY();
			Point2D currentpoint = e.getPoint();
			draging.setLocation(currentpoint, 1);

			double dx;
			double dy;
			dx = draging.border.getX() - x;
			dy = draging.border.getY() - y;

			if (mySelectedComponent.contains(draging))
				for (MyComponent m : mySelectedComponent) {
					if (m != draging) {
						Rectangle2D p = new Rectangle2D.Double(m.border.getX()
								+ dx, m.border.getY() + dy,
								m.border.getWidth(), m.border.getHeight());
						m.border.setFrame(p);
					}
				}
			dragingCMP = true;
		} else if (dragingSize != null) {
			dragingSize.setSize(e.getPoint());
			dragingCMP = true;
		} else if (mySelectingRectangle != null) {// ///////////////正反选择 ， 没实现
			Point2D p = e.getPoint();
			double x = mySelectingRectangle.getX();
			double y = mySelectingRectangle.getY();
			if (p.getX() - x > 10 && p.getY() - y > 10) {
				mySelectingRectangle.setFrame(x, y, Math.abs(p.getX() - x),
						Math.abs(p.getY() - y));
			} else {
				mySelectingRectangle.setFrame(x, y, 10, 10);
			}
		}
		repaint();
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

	private int selectComponent() {// ///到底return啥？
		mySelectedComponent = new ArrayList<MyComponent>();
		int toReturn = 0;
		if (outer.componentList.size() != 0 && mySelectingRectangle != null) {
			for (MyComponent m : outer.componentList)
				if (mySelectingRectangle.contains(m.border)) {
					mySelectedComponent.add(m);
					toReturn++;
				}
		}
		return toReturn;
	}

	void drawViewComponent(Graphics2D g) throws IOException {
		for (MyComponent m : outer.componentList) {
			if (m == outer.focusCMP) {
				g.setColor(FOCUSED_COMPONENT_COLOR);
				g.fill(m.border);

			} else {
				g.setColor(UNFOCUSED_COMPONENT_COLOR);
				g.fill(m.border);
			}
			if (m.img != null) {
				BufferedImage img = ImageIO.read(m.img);

				g.drawImage(img, (int) m.border.getX(), (int) m.border.getY(),
						(int) m.border.getWidth(), (int) m.border.getHeight(),
						null);

			}

			g.setColor(STRING_ON_COMPONENT_COLOR);

			Rectangle2D tem = m.border.getBounds2D();
			g.drawString(m.text, (int) tem.getX(),
					(int) (tem.getY() + tem.getHeight() / 2 + 5));
		}
	}

	void removeCMP(MyComponent e) {
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