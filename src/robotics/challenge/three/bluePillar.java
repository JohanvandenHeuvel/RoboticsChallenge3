package robotics.challenge.three;

import lejos.hardware.Audio;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.robotics.subsumption.Behavior;

public class bluePillar implements Behavior {
	boolean suppressed = false;
	
	@Override
	public boolean takeControl() {
		//see bluePillar
		return false;
	}
	
	@Override
	public void suppress() {
		suppressed = true;
		
	}
	
	@Override
	public void action() {
		suppressed = false;
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		Audio audio = ev3.getAudio();
		
		// Make EV3 beep
		
		//while(!suppressed)
		audio.systemSound(0);
		
	}
}
