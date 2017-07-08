package robotics.challenge.three;

import lejos.hardware.Audio;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

/**
 * Friendly Behavior.
 * @author johan
 *
 */
public class Hostile implements Behavior{
	boolean suppressed;
	boolean inRange = false;
	
	EV3GyroSensor gyro;
	EV3ColorSensor color;
	EV3UltrasonicSensor sonic;
	
	final int RED = 0;
	final int BLUE = 2;
	final double THRESHOLD = 0.20;
	final int SPEED = 150;
	
	public Hostile(EV3ColorSensor color, EV3GyroSensor gyro, EV3UltrasonicSensor sonic)
	{
		suppressed = false;
		this.color = color;
		this.gyro = gyro;
		this.sonic = sonic;
	}
	
	@Override
	public boolean takeControl() 
	{
//		return true;
		inRange = readUltraSonic() < THRESHOLD;
		return inRange && readColorIDMode() == RED;
	}
	
	@Override
	public void suppress() 
	{
		suppressed = true;
	}
	
	public void unsuppress()
	{
		suppressed = false;
	}
	
	public float readColorIDMode()
	{
		float[] sample = new float[1];
		SampleProvider sampleProvider = color.getColorIDMode();
		sampleProvider.fetchSample(sample, 0);
		return sample[0];
	}
	
	public float readUltraSonic()
	{
		float[] sample = new float[1];
		SampleProvider sampleProvider = sonic.getDistanceMode();
		sampleProvider.fetchSample(sample, 0);
		return sample[0];
	}
	
	public float readGyroAngle()
	{
		float[] sample = new float[1];
		SampleProvider sampleprovider = gyro.getAngleMode();
		sampleprovider.fetchSample(sample, 0);
		return sample[0];
	} 
	
	public double avgThreshold(double white, double black)
	{
		return ((white - black) / 2) + black;
	}
	
	public void motorsStop()
	{
		Motor.A.stop(true);
		Motor.C.stop(true);
	}
	
	public void motorsForward()
	{
		Motor.A.forward();
		Motor.C.forward();
	}
	
	public void motorsSpeed(int speedA, int speedC)
	{
		Motor.A.setSpeed(speedA);
		Motor.C.setSpeed(speedC);
	}
	
	public void turnLeft()
	{
		motorsSpeed(SPEED,SPEED);
		Motor.A.backward();
		Motor.C.forward();
		try {
			Thread.sleep (500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		motorsStop();
	}
	
	public void turnRight()
	{
		motorsSpeed(SPEED,SPEED);
		Motor.C.backward();
		Motor.A.forward();
		try {
			Thread.sleep (500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		motorsStop();
	}
	
	@Override
	public void action() 
	{
		unsuppress();
		
		motorsSpeed(SPEED, SPEED);
		Motor.C.backward();
		Motor.A.backward();
		Delay.msDelay(1000);
		motorsStop();
		
		EV3 ev3 = (EV3) BrickFinder.getDefault();
		Audio audio = ev3.getAudio();
		
		audio.playTone(440, 125, 80);
		audio.playTone(392, 125, 80);
		audio.playTone(440, 500, 80);
		audio.playTone(294, 500, 80);
		Delay.msDelay(750);
		audio.playTone(466, 125, 80);
		audio.playTone(440, 125, 80);
		audio.playTone(466,	250, 80);
		audio.playTone(440, 250, 80);
		audio.playTone(392, 500, 80);
		
		motorsSpeed((int) 1.5 * SPEED,(int) 1.5 * SPEED);
		Motor.C.forward();
		Motor.A.forward();
		Delay.msDelay(2000);
		motorsStop();

	}
}
