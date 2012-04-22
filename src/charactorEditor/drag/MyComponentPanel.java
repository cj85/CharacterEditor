package charactorEditor.drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import charactorEditor.Model;

class MyComponentPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	private static final long serialVersionUID = 100L;
	private final Color UNCLICKED_COMPONENT_COLOR = Color.blue;
	private final Color CLICKED_COMPONENT_COLOR = Color.red;
	private final Color STRING_COLOR = Color.white;
	public final int  COMPONENTNUMBER=2;
	private Graphics2D g = null;
	private Rectangle2D[] components = new Rectangle2D.Double[COMPONENTNUMBER];
	private FighterBuilder outer = null;
	private Model myModel;

	MyComponentPanel(FighterBuilder e) {
		outer = e;
		initComponents();
		setBounds(0, 0, 113, 615);
		myModel=outer.myModel;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	int find(Point2D p) {
		for (int i = 0; i < COMPONENTNUMBER; i++) {
			if (components[i].contains(p)) {
				return i;
			}
		}
		return -1;
	}

	void initComponents() {
		for (int i = 0; i < COMPONENTNUMBER; i++) {
			components[i] = new Rectangle2D.Double(25, 30 + 30 * i, 60, 25);
		}
	}

	void drawComponents(Graphics2D g) {
		for (int i = 0; i < COMPONENTNUMBER; i++) {
			if (i == myModel.willPut) {
				g.setColor(CLICKED_COMPONENT_COLOR);
				g.fill(components[i]);
			} else {
				g.setColor(UNCLICKED_COMPONENT_COLOR);
				g.fill(components[i]);
			}
		}
		g.setColor(STRING_COLOR);
		for (int i = 0; i < COMPONENTNUMBER; i++) {
			g.drawString(myModel.SORT.CMP[i], 27, 47 + 30 * i);
		}
	}

	public void paintComponent(Graphics e) {
		super.paintComponent(e);
		g = (Graphics2D) e;
		drawComponents(g);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		myModel.willPut = find(e.getPoint());
		myModel.focusCMP = null;
		update();
		outer.repaint();
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		if (find(e.getPoint()) != -1) {
			outer.cross();
		} else {
			outer.deletecross();
		}
	}

	private void update() {
		outer.attributePane.update();
	}
}