package robotics.challenge.three;

import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

//ColorId 3.00 RED
//ColorId 1.00 BLUE

public class FriendOrFoe {

	public static void main(String[] args) {
		Behavior roamIsland = new roamIsland(SensorPort.S3);
		Behavior beach = new beach(SensorPort.S1);
		
		Behavior redPillar = new redPillar(SensorPort.S4);
		
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
		
		Behavior [] bArray = {redPillar};
		//Behavior [] bArray = {roamIsland,beach, redPillar, bluePillar};
		
		Arbitrator arby = new Arbitrator(bArray);
		
		arby.start();

	}

}
