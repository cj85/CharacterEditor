package charactorEditor.drag;

import javax.swing.*;
import javax.swing.event.*;
import charactorEditor.Controller;
import java.awt.*;
import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class FighterBuilder extends JFrame implements ChangeListener {

	private Controller myController;
	private MyComponentPanel myComponentPanel;
	private JPanel centerPane;
	private AttributePane attributePane;
	private JTabbedPane tab;
	private MainPane drawPane;

	public FighterBuilder() throws FileNotFoundException {
		setTitle("FighterBuilder");
		myController = Controller.Instance();
		myComponentPanel = new MyComponentPanel();
		this.setDefaultCloseOperation(3);
		setBounds(10, 50, 1000, 645);
		JPanel jp = new JPanel();
		jp.setLayout(null);
		attributePane = new AttributePane();
		drawPane = new MainPane();
		tab = new JTabbedPane();
		init();
		myController.register(this);
		jp.add(myComponentPanel);
		jp.add(centerPane);
		jp.add(attributePane);
		setContentPane(jp);
		setVisible(true);
		setResizable(false);
	}

	private void init() {
		centerPane = new JPanel();
		centerPane.setBounds(115, 0, 700, 615);
		centerPane.setLayout(new BorderLayout());
		tab.add("View", drawPane);
		tab.addChangeListener(this);
		centerPane.add(tab);
	}

	public void stateChanged(ChangeEvent e) {
	}

	public void changesize() {
		setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
	}
	
	public void cross() {
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void deletecross() {
		setCursor(Cursor.getDefaultCursor());
	}
	
	public  MainPane getDrawPane(){
		return drawPane;
	}
	public MyComponentPanel getComponentPanel(){
		return myComponentPanel;
	}
	public AttributePane getAttributePane(){
		return attributePane;
	}

}
