package SpriteTree;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Animation {
	private ArrayList<Motion> myActiveMotions;
	private HashMap<Long, Motion> mySequence; // long => start time of animation
	private HashMap<Long, Motion> myDefaultSequence;
	private long myCurrTime;
	private HashMap<String, LimbNode> myMap;
	private boolean isActive;
	private boolean isRepeating;

	public Animation(HashMap<Long, Motion> allMotions, BodyTree tree) {
		this.myCurrTime = 0;
		this.myMap = tree.getMap();
		this.mySequence = allMotions;
		this.myDefaultSequence = new HashMap<Long, Motion>();
		this.myDefaultSequence.putAll(allMotions);
		
		this.myActiveMotions = new ArrayList<Motion>();

		isRepeating = true; // default, action only performed once, but for testing purposes, set to true

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
				mySequence.putAll(myDefaultSequence);
				for(Motion m: mySequence.values()){
					m.reActivate();
				}

				
				} else {
				isActive = false;
				myCurrTime = 0;
			}
		} else {

			myCurrTime += elapsedTime;

			
			Iterator i = ((HashMap<Long, Motion>)mySequence.clone()).entrySet().iterator();
			while(i.hasNext()){
				Map.Entry<Long, Motion> entry = (Entry<Long, Motion>) i.next();
				if (entry.getKey() <= myCurrTime) {// key is the start time of the motion

					myActiveMotions.add(entry.getValue()); // make the motion active
					mySequence.remove(entry.getKey());
				}
				
			}
			
			Iterator j = ((ArrayList<Motion>) myActiveMotions.clone()).iterator();
			
			while(j.hasNext()){
				Motion motion = (Motion)j.next();
				if (motion.isActive() == false) {
					myActiveMotions.remove(motion);
					continue;
					
				}
				else{
				motion.update(elapsedTime);
			
				myMap.get(motion.getName()).rotate(motion.getDTheta());

				}
				
			}

		}

	}

	public long getCurrentTime() {
		return this.myCurrTime;
	}
}
