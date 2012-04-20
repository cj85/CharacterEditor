package SpriteTree;

public class Motion {
	//be able to decrement the time, updat() method, with elapsedtime, subtract from time
	//boolean update(long elapsedTime) time-=elaspedtime; return if time ==0;
	//pass in the currentangle of the limb
	//store dtheta: how much to move per second, constructor needs the source angle
	
	private String name;
	private double dTheta;
	private long time;
	private boolean active;
	//test
	private double myExpTheta;
	
	
	public Motion(String name, double expAngle, BodyTree tree, long time){
		
		this.name = name;
		double currAngle = tree.getMap().get(name).getTheta();

		this.dTheta = (expAngle-currAngle)/(double)time;
		this.time = time;
		
		this.myExpTheta = expAngle;
		
//		System.out.println();
//		System.out.println("my name is " + name);
//		System.out.println("my currAngle is "+ currAngle);
//		System.out.println("my expectedAngle is " + expAngle);
//		System.out.println("my dtheta is " + dTheta);
//		System.out.println();
		
		active = true;
	}
	
	public void update(long elapsedTime){
		if(this.time >=0){
		this.time -=elapsedTime;
		
		}
		else{
			active = false;
		}
	}
	
	
	//test
	public Double getExpAngle(){
		return this.myExpTheta;
	}
	//test
	                            
	public Boolean isActive(){
		return active;
	}
	
	public String getName(){
		return this.name;
	}
	
	public double getDTheta(){
		return this.dTheta;
	}
	
	public long getTime(){
		return this.time;
	}
}
