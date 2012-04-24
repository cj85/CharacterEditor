package charactorEditor.drag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import charactorEditor.Controller;

public class MainPane extends JPanel implements MouseListener,
		MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 199L;
	private Graphics2D g = null;
	private final Color UNFOCUSED_COMPONENT_COLOR = new Color(100, 200, 100);
	private final Color FOCUSED_COMPONENT_COLOR = Color.orange;
	private final Color STRING_ON_COMPONENT_COLOR = Color.white;
	private final Color SELECTED_COMPONENT_COLOR = Color.pink;
	private final Color NEXT_FOCUSED_COMPONENT_COLOR = Color.cyan;
	private final Color LINE_COLOR = new Color(200, 200, 210);

	
	private Controller myController;
	public JMenuItem mConnect;
	JMenuItem mDisconnect = new JMenuItem("disconnect");
	JPopupMenu menu = new JPopupMenu();
	private MainPaneModel myMainPaneModel = MainPaneModel.Instance();

	MainPane() {
		myController = Controller.Instance();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
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

	private void drawConnected(Graphics2D g) {
		g.setColor(Color.BLACK);
		if (!myController.isComponentListEmpty()) {
			for (MyComponent m : myController.getComponentList()) {
				if (m.isRoot()) {
					m.drawTree(g);
				}
			}
		}
	}

	private void drawLine(Graphics2D g) {
		g.setColor(LINE_COLOR);
		for (int i = 0; i < MainPaneModel.COL; i++) {
			g.draw(MainPaneModel.cols[i]);
		}
		for (int i = 0; i <MainPaneModel. ROW; i++) {
			g.draw(MainPaneModel.rows[i]);
		}
	}

	private void drawSelecting(Graphics2D g) {
		g.setColor(Color.black);
		if (!myMainPaneModel.noSelectingRectangle())
			g.draw(myMainPaneModel.getSelectingRectangle());
	}

	private void drawSelected(Graphics2D g) {
		g.setColor(SELECTED_COMPONENT_COLOR);
		for (MyComponent m : myMainPaneModel.getSelectedComponnet())
			g.fill(m.getBorder());
	}

	public void mouseClicked(MouseEvent e) {
		mConnect = new JMenuItem("connect");
		myController.register(mConnect);
		myController.mouseClicked(e);
		mConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myController.getMessage(null, e);
				repaint();
			}
		});
	}

	public void addMenu(MouseEvent e) {
		menu.removeAll();
		menu.add(mConnect);
		menu.show(this, e.getX(), e.getY());
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		myController.mousePressed(e);

	}

	public void mouseReleased(MouseEvent e) {
		myController.mouseReleased(e);
		repaint();
		this.requestFocus();
	}

	public void mouseDragged(MouseEvent e) {
		myController.mouseDragged(e);
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		myController.mouseMoved(e);

	}


	void drawViewComponent(Graphics2D g) throws IOException {
		for (MyComponent m : myController.getComponentList()) {
			if (m == myController.getFoucsedComponent()) {
				g.setColor(FOCUSED_COMPONENT_COLOR);
			} else if (m == myMainPaneModel.getNextFocusComponent()) {
				g.setColor(NEXT_FOCUSED_COMPONENT_COLOR);
			} else {
				g.setColor(UNFOCUSED_COMPONENT_COLOR);
			}
			g.fill(m.getBorder());
			if (!m.hasNoImg()) {
				BufferedImage img = ImageIO.read(m.getImg());
				g.drawImage(img, m.getBorderX(), m.getBorderY(),
						 m.getBorderWidth(),  m.getBorderHeight(),
						null);
			}
			g.setColor(STRING_ON_COMPONENT_COLOR);
			Rectangle2D tem = m.getBorderBounds2D();
			g.drawString(m.getText(), (int) tem.getX(),
					(int) (tem.getY() + tem.getHeight() / 2 + 5));
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		myController.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		myController.keyReleased(e);
	}

}