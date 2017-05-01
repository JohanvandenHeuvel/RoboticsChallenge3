package robotics.challenge.three;

import lejos.hardware.Audio;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class redPillar implements Behavior {
	boolean suppressed = false;
	boolean inRange = false;
	EV3UltrasonicSensor sonic;
	double threshold = 0;
	
	public redPillar(Port port) {
		sonic = new EV3UltrasonicSensor(port);
	}
	
	@Override
	public boolean takeControl() {
		return !inRange;
		/*
		SampleProvider sampleprovider = sonic.getDistanceMode();
		float[] sample = new float[1];
		sampleprovider.fetchSample(sample, 0);
		return sample[0] < 100;
		*/
	}
	
	@Override
	public void suppress() {
		suppressed = true;
	}
	
	@Override
	public void action() {
		suppressed = false;
		
		SampleProvider sampleprovider = sonic.getDistanceMode();
		float[] sample = new float[1];

		int CONSTANT = 240;
		
		while (!suppressed) {
			sampleprovider.fetchSample(sample, 0);
			
			if(sample[0] < 0.08)
			{
				inRange = true;
				suppress();
				EV3 ev3 = (EV3) BrickFinder.getDefault();
				Audio audio = ev3.getAudio();
				audio.systemSound(0);
			}
			else if (sample[0] > 100 && !suppressed) {
				Motor.A.setSpeed(0);
				Motor.C.setSpeed(CONSTANT);

				Motor.A.forward();
				Motor.C.forward();
			}

			else if(!suppressed){
				Motor.A.setSpeed(240);
				Motor.C.setSpeed(CONSTANT);

				Motor.A.forward();
				Motor.C.forward();
			} 
			//	suppress();
			Thread.yield();
		}
		
		Motor.A.stop(true);
		Motor.C.stop(true);

		
		//EV3 ev3 = (EV3) BrickFinder.getDefault();
		//Audio audio = ev3.getAudio();
		//audio.systemSound(0);
		
	}
}
