package SpriteTree;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JFrame;

public class BodyTree {
	private LimbNode root;
	private HashMap<String,LimbNode> map ;
	
	
	
	public BodyTree (LimbNode root){
		this.root=root;
		map= new HashMap<String,LimbNode>();
		createMap(this.root);
		
	}
	

	public void createMap(LimbNode currNode){
		if(!map.containsKey(currNode.getName())){
			map.put(currNode.getName(), currNode);
		}
		for(LimbNode limb:currNode.getChildren()){
			createMap(limb);
		}
	}
	
	public HashMap<String, LimbNode> getMap(){
		return map;
	}
	
	public LimbNode getNode(String name){
		return map.get(name);
	}
	
	public void render(Graphics2D pen){
		root.render(pen, root.getX(),root.getY(),0);
	}
	
	public void move(Graphics2D pen, double moveX, double moveY){
		root.render(pen, root.getX()+moveX,root.getY()+moveY,0);
	}
	
	public LimbNode getRoot(){
		return root;
	}
}