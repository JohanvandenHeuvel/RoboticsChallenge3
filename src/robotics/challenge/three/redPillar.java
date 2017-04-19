package robotics.challenge.three;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class redPillar implements Behavior {
	boolean suppressed = false;
	EV3UltrasonicSensor sonic;
	double threshold = 0;
	
	public redPillar(Port port) {
		sonic = new EV3UltrasonicSensor(port);
	}
	
	@Override
	public boolean takeControl() {
		return true;
		SampleProvider sampleprovider = sonic.getDistanceMode();
		float[] sample = new float[1];
		sampleprovider.fetchSample(sample, 0);
		return sample[0] <= threshold;
	}
	
	@Override
	public void suppress() {
		suppressed = true
		
	}
	
	@Override
	public void action() {
		suppressed = false;
		
		Motor.A.setSpeed(360);
		Motor.C.setSpeed(360);
		
		Motor.A.forward();
		Motor.C.forward();
		
		while(!suppressed)
			Thread.yield();
		
		Motor.A.stop(true);
		Motor.C.stop(true);
	}
}
