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

class MyComponentPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	private static final long serialVersionUID = 100L;
	Graphics2D g = null;
	private Rectangle2D[] components = new Rectangle2D.Double[2];
	private FighterBuilder outer = null;
	private AttributePane attributePane = null;

	MyComponentPanel(FighterBuilder e) {
		outer = e;
		attributePane = (AttributePane) outer.attributePane;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	int find(Point2D p) {
		for (int i = 0; i < outer.NUM; i++) {
			if (components[i].contains(p)) {
				return i;
			}
		}
		return -1;
	}

	void initComponents() {
		for (int i = 0; i < 2; i++) {
			components[i] = new Rectangle2D.Double(25, 30 + 30 * i, 60, 25);
		}
	}

	void drawComponents(Graphics2D g) {
		g.setColor(Color.blue);
		for (int i = 0; i < 2; i++) {
			if (i == outer.willPut) {
				g.setColor(Color.red);
				g.fill(components[i]);
				g.setColor(Color.blue);
			} else {
				g.fill(components[i]);
			}
		}
		g.setColor(Color.white);

		for (int i = 0; i < 2; i++) {
			g.drawString(outer.SORTSTRING.STRINGS[i], 27, 47 + 30 * i);
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
		outer.willPut = find(e.getPoint());
		outer.focusCMP = null;

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
		attributePane.label_8update();
		attributePane.label_1.setText("");
		attributePane.text_1.setText("");
		attributePane.text_2.setText("");
		attributePane.cbo_1.removeAllItems();
	}
}