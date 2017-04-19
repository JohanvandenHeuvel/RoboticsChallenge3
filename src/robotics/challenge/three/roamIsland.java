package robotics.challenge.three;

import lejos.robotics.subsumption.Behavior;

public class roamIsland implements Behavior{
	boolean suppressed = false;
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void suppress() {
		suppressed = true;
		
	}
	
	@Override
	public void action() {
		//randomly roam the island
		
	}
}
