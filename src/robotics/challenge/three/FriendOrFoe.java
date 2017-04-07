package robotics.challenge.three;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class FriendOrFoe {

	public static void main(String[] args) {
	Behavior roamIsland = new Behavior() {
			
			@Override
			public boolean takeControl() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void suppress() {
				//suppressed = true
				
			}
			
			@Override
			public void action() {
				//randomly roam the island
				
			}
		};
		
		Behavior beach = new Behavior() {
			
			@Override
			public boolean takeControl() {
				//one the beach
				return false;
			}
			
			@Override
			public void suppress() {
				//suppressed = true
				
			}
			
			@Override
			public void action() {
				//back away from the beach
				
			}
		};
		
		Behavior redPillar = new Behavior() {
			
			@Override
			public boolean takeControl() {
				//see redPillar
				return false;
			}
			
			@Override
			public void suppress() {
				//suppressed = true
				
			}
			
			@Override
			public void action() {
				//kill behavior (trow in the sea)
				
			}
		};
		
		Behavior bluePillar = new Behavior() {
			
			@Override
			public boolean takeControl() {
				//see bluePillar
				return false;
			}
			
			@Override
			public void suppress() {
				//suppressed = true
				
			}
			
			@Override
			public void action() {
				// romantic behavior
				
			}
		};
		
		Behavior [] bArray = {roamIsland,beach, redPillar, bluePillar};
		
		Arbitrator arby = new Arbitrator(bArray);
		
		arby.start();

	}

}
