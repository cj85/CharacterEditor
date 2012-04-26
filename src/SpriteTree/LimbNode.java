package SpriteTree;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import com.golden.gamedev.util.ImageUtil;

import com.golden.gamedev.object.Sprite;

public class LimbNode extends Sprite{
	
	private String myName;
	
	private BufferedImage myCurrImage;
	private BufferedImage myOrigImage;
	
	
	private double dx;
	private double dy;
	private double defaultTheta;
	private double mutableTheta;
	private LimbNode Parent;
	
	private ArrayList<LimbNode> children = new ArrayList<LimbNode>();
	
	//to be implemented
	double damageMultiplier;
	double damageDealt;
	
	//constructor for root node who doesn't have a parent 
	public LimbNode(String name, BufferedImage image, double x, double y){
		super(image, x, y);
		this.myName = name;
		this.myOrigImage = image;
	}
	//constructor for limbs
	
	public LimbNode(String name, LimbNode parent, BufferedImage image,double dx, double dy, int baseTheta){
		super(image,parent.getX()+dx, parent.getY()+dy);
		this.myName = name;
		this.myOrigImage= image;
		this.Parent = parent;
		this.mutableTheta = baseTheta;
		this.defaultTheta  = baseTheta;
		this.dx = dx;
		this.dy = dy;
	
	}
	
	
	public String getName(){
		return this.myName;
	}
	
	public double getTheta(){
		return this.mutableTheta;
	}
	public double getDefaultTheta(){
		return this.defaultTheta;
	}

	public void setTheta(double expTheta){
		this.mutableTheta = expTheta;
	}

	
	public ArrayList<LimbNode> getChildren(){
		return this.children;
	}
	
	public void rotate(double dTheta)
	{	
		this.mutableTheta += dTheta;
		
	}
	
	
	public void addChild(LimbNode child){
		children.add(child);
		
	}
	public void draw(double x, double y, double theta){
		this.setX(x);
		this.setY(y);
		this.myCurrImage =GraphicsTest.rotate(this.myOrigImage,theta);
		this.setImage(this.myCurrImage);
	}

	public void render(Graphics2D pen,double baseX, double baseY, double baseTheta){
		
		
		super.render(pen);
		
		double dx =Math.cos(Math.toRadians(baseTheta)) * this.dx - Math.sin(Math.toRadians(baseTheta)) * this.dy;
		double dy =Math.sin(Math.toRadians(baseTheta)) * this.dx + Math.cos(Math.toRadians(baseTheta)) * this.dy;

		
		draw((baseX + dx), (baseY + dy),this.mutableTheta+baseTheta);
		
		
		for(LimbNode limb: this.children){
			limb.render(pen, (baseX + dx), (baseY + dy), this.mutableTheta+baseTheta);
		}
		

	}
	
	public void update(long elapsedTime){

		super.update(elapsedTime);
	}
	
	public void print(){
		System.out.println(this.Parent);
		System.out.println(this.dx);
		System.out.println(this.dy);
		System.out.println(this.mutableTheta);
	}
	
}
