package robotics.challenge.three;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

//ColorId 3.00 RED
//ColorId 1.00 BLUE

public class Challenge3 {

	/**
	 * Blue pillar romantic task
	 * Red pillar kill task
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting..");
		
		//Sensors
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S1);
		System.out.println("Color loaded..");
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S3);
		System.out.println("Gyro loaded..");
		EV3UltrasonicSensor sonic = new EV3UltrasonicSensor(SensorPort.S2);
		System.out.println("Sonic loaded..");
		System.out.println("Sensors loaded..");
		
		//Behaviors
		Behavior FindPillar = new FindPillar(gyro, color, sonic);
		System.out.println("FindPillar loaded..");
		Behavior Hostile = new Hostile(color, gyro, sonic);
		System.out.println("Hostile loaded..");
		Behavior Friendly = new Friendly(color, gyro, sonic);
		System.out.println("Friendly loaded..");
//		Behavior AvoidBeach = new AvoidBeach(color, gyro);
//		System.out.println("AvoidBeach loaded..");
		System.out.println("Behaviors loaded..");
		
		//Arbitrator
		Behavior [] bArray = {FindPillar, Hostile, Friendly};
//		Behavior [] bArray = {Friendly};
		Arbitrator arbitrator = new Arbitrator(bArray);
		arbitrator.start();

	}

}
