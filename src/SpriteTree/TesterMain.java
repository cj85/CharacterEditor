package SpriteTree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class TesterMain extends Game {
	private BodyTree myTree;
	
	private Graphics2D myPen;
	private Animation animation;
	
	
	@Override
	public void initResources() {
		
		
		
		BufferedImage imgH = GraphicsTest.loadImage("./circle.png");
		BufferedImage imgT = GraphicsTest.loadImage("./limb.png");
		BufferedImage imgLA = GraphicsTest.loadImage("./limb.png");
		BufferedImage imgRA = GraphicsTest.loadImage("./limb.png");
		BufferedImage imgLL = GraphicsTest.loadImage("./limb.png");
		BufferedImage imgRL = GraphicsTest.loadImage("./limb.png");
		

		BufferedImage imgLRL = GraphicsTest.loadImage("./nLimb.png");

		
		LimbNode torso = new LimbNode("torso",imgT, this.getWidth()/2, this.getHeight()/2);
		LimbNode head = new LimbNode("head",torso,imgH, torso.getWidth()/3,-5,0);
		LimbNode LeftArm = new LimbNode("LeftArm",torso,imgLA, -15,0,45);		
		LimbNode RightArm = new LimbNode("RightArm",torso,imgRA, 15,0,-45);
		LimbNode LeftLeg = new LimbNode("LeftLeg",torso,imgLL, -15,torso.getHeight()/2,45);
		LimbNode RightLeg= new LimbNode("RightLeg",torso,imgRL, 15,torso.getHeight()/2,-45);
		
		LimbNode LRightLeg = new LimbNode("LRightLeg",RightLeg, imgLRL, 0, RightLeg.getHeight()/2, 45);
		RightLeg.addChild(LRightLeg);
		
		
//		movingLeg = RightLeg;
//		movingLowerLeg = LRightLeg;

		
		torso.addChild(LeftLeg);
		torso.addChild(RightLeg);
		torso.addChild(RightArm);
		torso.addChild(LeftArm);
		torso.addChild(head);
		
		myTree = new BodyTree(torso);
		
		
		
//		//create animation motions
		Motion m1 = new Motion("LeftArm", -800, myTree, 45);
		Motion m2 = new Motion("RightLeg", 750, myTree, 40);
		Motion m3 = new Motion("RightLeg", 0, myTree, 40);
		Motion m4 = new Motion("LeftArm", 0, myTree, 45);

		HashMap<Long, Motion> sequence = new HashMap<Long, Motion>();
		sequence.put((long) 0,m1);
		sequence.put((long) 150, m2);
		sequence.put((long) 500, m3);
		sequence.put((long) 1000, m4);
		this.animation = new Animation(sequence, myTree);
		

	}

	
	@Override
	public void render(Graphics2D pen) {
		pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        myPen = pen;
        myTree.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		
		
		
	if(this.animation.getStatus()==true){
		System.out.println("********************");
		System.out.println("animation update called");
		System.out.println("********************");

		this.animation.update(elapsedTime);
	}
	

		
		if(keyDown(KeyEvent.VK_LEFT)){
			myTree.move(myPen,-1, 0); 
		}
		if(keyDown(KeyEvent.VK_RIGHT)){
			myTree.move(myPen,1, 0);
		}
		if(keyDown(KeyEvent.VK_UP)){
			myTree.move(myPen,0, -1);
		}
		if(keyDown(KeyEvent.VK_DOWN)){
			myTree.move(myPen,0, 1);
		}
		
		if(keyDown(KeyEvent.VK_SPACE)){
			System.out.println("/////////////////////////////////");
			//note to self: leftarm default is 45
			//rightleg default is -45
			

			
			this.animation.activateAnimation();

		}
		

		
	
	}
	
	 public static void main (String[] args)
	    {
	        GameLoader loader = new GameLoader();
	        loader.setup(new TesterMain(), new Dimension(800, 600), false);
	        loader.start();
	    }
}
