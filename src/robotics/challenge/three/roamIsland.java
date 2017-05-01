package robotics.challenge.three;

import lejos.hardware.Audio;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class roamIsland implements Behavior{
	boolean suppressed = false;
	boolean touched = false;
	
	EV3TouchSensor touch;
	
	public roamIsland(Port port) {
		touch = new EV3TouchSensor(port);
	}
	
	@Override
	public boolean takeControl() {
		SampleProvider sampleprovider = touch.getTouchMode();
		float[] sample = new float[1];
		sampleprovider.fetchSample(sample, 0);
		if(sample[0] == 1)
			touched = true;
		return touched;
	}
	
	@Override
	public void suppress() {
		suppressed = true;
		
	}
	
	@Override
	public void action() {
		Motor.A.stop(true);
		Motor.C.stop(true);
		
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		Audio audio = ev3.getAudio();
		audio.systemSound(0);
		
		
	}
}
