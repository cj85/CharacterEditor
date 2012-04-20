package SpriteTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;


public class Animation {
	private ArrayList<Motion> myActiveMotions;
	private HashMap<Long, Motion> mySequence; // long => start time of animation
	private long myCurrTime;
	private HashMap<String, LimbNode> myMap;
	private boolean isActive;
	private boolean isRepeating;

	public Animation(HashMap<Long, Motion> allMotions, BodyTree tree) {
		this.myCurrTime = 0;
		this.myMap = tree.getMap();
		this.mySequence = allMotions;

		this.myActiveMotions = new ArrayList<Motion>();

		isRepeating = false; // default, action only performed once

	}

	public void setRepeat(Boolean loop) {
		if (loop == true) {
			isRepeating = true;
		}
	}

	public void activateAnimation() {
		isActive = true;
	}

	public boolean getStatus() {
		return isActive;
	}

	public void update(long elapsedTime) {

		if (myActiveMotions.size() == 0 && mySequence.size() == 0) {

			if (isRepeating == true) {
				myCurrTime = 0;
			} else {
				isActive = false;
				myCurrTime = 0;
			}
		} else {

			myCurrTime += elapsedTime;

			ArrayList<Long> activeKeys = new ArrayList<Long>();
			for (Map.Entry<Long, Motion> entry : mySequence.entrySet()) {
				if (entry.getKey() <= myCurrTime) {// key is the start time of
													// the motion
					myActiveMotions.add(entry.getValue()); // make the motion
															// active
					activeKeys.add(entry.getKey());

				}
			}

			for (Long key : activeKeys) {
				mySequence.remove(key);
			}

			ArrayList<Motion> finishedMotions = new ArrayList<Motion>();

			for (Motion motion : myActiveMotions) {

				if (motion.isActive() == false) {
					finishedMotions.add(motion);

					continue;
				}

				motion.update(elapsedTime);
				
				//try to calculate dtheta here
				double currAngle = myMap.get(motion.getName()).getTheta();

				double dTheta= (motion.getExpAngle()-currAngle)/(double)motion.getTime();
				
				System.out.println();
				System.out.println("my name is " + motion.getName());
				System.out.println("my dTheta is " + dTheta);
				System.out.println();
				//
				
				
//				myMap.get(motion.getName()).rotate(motion.getDTheta());
				myMap.get(motion.getName()).rotate(dTheta);


			}
			for (Motion motion : finishedMotions) {
				myActiveMotions.remove(motion);
			}
		}

	}

	public long getCurrentTime() {
		return this.myCurrTime;
	}
}
