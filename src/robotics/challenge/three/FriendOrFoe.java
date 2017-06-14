package robotics.challenge.three;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

//ColorId 3.00 RED
//ColorId 1.00 BLUE

public class FriendOrFoe {

	public static void main(String[] args) {
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S1);
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
		
		Behavior roamIsland = new roamIsland();
		Behavior beach = new beach(SensorPort.S1);
		
		Behavior redPillar = new redPillar(SensorPort.S4);
		
		Behavior bluePillar = new Behavior()
		
		Behavior [] bArray = {redPillar};
		//Behavior [] bArray = {roamIsland,beach, redPillar, bluePillar};
		
		Arbitrator arby = new Arbitrator(bArray);
		
		arby.start();

	}

}
