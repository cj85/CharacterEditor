package SpriteTree;

public class Motion {
	//be able to decrement the time, updat() method, with elapsedtime, subtract from time
	//boolean update(long elapsedTime) time-=elaspedtime; return if time ==0;
	//pass in the currentangle of the limb
	//store dtheta: how much to move per second, constructor needs the source angle
	
	private String name;
	private double dTheta;
	private long time;
	private long defaultTime;
	private boolean active;
	//test only
//	private double currAngle;
//	private double expAngle;
//	private int timesRevived = 0;
	//
	
	
	public Motion(String name, double expAngle, BodyTree tree, long time){
		
		this.name = name;
		
		double currAngle = tree.getMap().get(name).getTheta();

		this.dTheta = (expAngle-currAngle)/(double)time;
		this.time = time;
		this.defaultTime = time;
		
		//add rotated angle to current theta, assume already rotated
		//so the next motion performed on the same limb would calculate the rotated dtheta
		tree.getMap().get(name).setTheta(expAngle);

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
	
	

	                            
	public Boolean isActive(){
		return active;
	}
	
	public void reActivate(){
//		timesRevived +=1;
//		
//		System.out.println();
//		System.out.println("this is my " + timesRevived + "th life" );
//		System.out.println("my name is " + name);
//		System.out.println("my currAngle is "+ currAngle);
//		System.out.println("my expectedAngle is " + expAngle);
//		System.out.println("my dtheta is " + dTheta);
//		System.out.println();
//		
		this.time = this.defaultTime;
		active = true;
		

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
