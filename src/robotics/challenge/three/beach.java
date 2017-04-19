package robotics.challenge.three;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class beach implements Behavior {
	boolean suppressed = false;
	EV3ColorSensor color;
	double threshold = 0;
	
	public beach(Port port) {
		color = new EV3ColorSensor(port);
	}
	
	@Override
	public boolean takeControl() {
		SampleProvider sampleprovider = color.getColorIDMode();
		float[] sample = new float[1];
		sampleprovider.fetchSample(sample, 0);
		return sample[0] <= threshold;
	}
	
	@Override
	public void suppress() {
		//suppressed = true
		
	}
	
	@Override
	public void action() {
		//back away from the beach
		
	}
}
