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
	private LimbNode movingLeg;
	private LimbNode movingLowerLeg;
	
	private Animation animation;
	
	
	@Override
	public void initResources() {
		
		
		
		BufferedImage imgH = GraphicsTest.loadImage("src/resources/bodyParts/circle.png");
		BufferedImage imgT = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgLA = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgRA = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgLL = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		BufferedImage imgRL = GraphicsTest.loadImage("src/resources/bodyParts/limb.png");
		

		BufferedImage imgLRL = GraphicsTest.loadImage("src/resources/bodyParts/nLimb.png");
		BufferedImage imgLLL = GraphicsTest.loadImage("src/resources/bodyParts/nLimb.png");

		
//		LimbNode torso = new LimbNode("torso",imgT, this.getWidth()/2, this.getHeight()/2);
		LimbNode torso = new LimbNode("torso",imgT, 50, 50);

		LimbNode head = new LimbNode("head",torso,imgH, torso.getWidth()/3,-5,0);
		LimbNode LeftArm = new LimbNode("LeftArm",torso,imgLA, -15,0,45);		
		LimbNode RightArm = new LimbNode("RightArm",torso,imgRA, 15,0,-45);
		LimbNode LeftLeg = new LimbNode("LeftLeg",torso,imgLL, -15,torso.getHeight()/2,0);
		LimbNode RightLeg= new LimbNode("RightLeg",torso,imgRL, 15,torso.getHeight()/2,0);
		
		LimbNode LRightLeg = new LimbNode("LRightLeg",RightLeg, imgLRL, 0, RightLeg.getHeight()/2, 0);
		RightLeg.addChild(LRightLeg);
		
		LimbNode LLeftLeg = new LimbNode("LLeftLeg",LeftLeg, imgLLL, 0, LeftLeg.getHeight()/2, 0);
		LeftLeg.addChild(LLeftLeg);
		
		
		
		torso.addChild(LeftLeg);
		torso.addChild(RightLeg);
		torso.addChild(RightArm);
		torso.addChild(LeftArm);
		torso.addChild(head);
		
		myTree = new BodyTree(torso);
		
		

		
		Motion m1 = new Motion("RightLeg", -800, myTree, 400);
		Motion m2 = new Motion("LRightLeg", 900, myTree, 400);
		Motion m3 = new Motion("LRightLeg", myTree.getMap().get("LRightLeg").getDefaultTheta(), myTree, 400);
		Motion m4 = new Motion("RightLeg", myTree.getMap().get("RightLeg").getDefaultTheta(), myTree, 400);
		Motion m5 = new Motion("LRightLeg", 0, myTree, 400);
		
		Motion m6 = new Motion("LeftLeg", -800, myTree, 400);
		Motion m7 = new Motion("LLeftLeg", 900, myTree, 400);
		Motion m8 = new Motion("LLeftLeg", myTree.getMap().get("LRightLeg").getDefaultTheta(), myTree, 400);
		Motion m9 = new Motion("LeftLeg", myTree.getMap().get("LeftLeg").getDefaultTheta(), myTree, 400);
		Motion m10 = new Motion("LLeftLeg", 0, myTree, 400);
		
		
		HashMap<Long, Motion> sequence = new HashMap<Long, Motion>();
		

		sequence.put((long) 1003, m1);
		sequence.put((long) 1004, m2);
		sequence.put((long) 1500, m3);
		sequence.put((long) 2000, m4);
		sequence.put((long) 2001, m5);
		
		sequence.put((long) 1, m6);
		sequence.put((long) 2, m7);
		sequence.put((long) 504, m8);
		sequence.put((long) 1001, m9);
		sequence.put((long) 1002, m10);


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
