package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import java.lang.Thread;


public class TrainControllerImpl implements TrainController, Runable {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	private Thread thread = new Thread();

	public TrainSpeed(){
		thread.start();
	} 

	public void run() {
		followSpeed();
		Thread.sleep(1000);
	}

	public void TrainControllerImpl()
	{
		th = new Thread(this);
		th.start(); 	
	}	

	public void run()
	{	followSpeed(); 
		th.sleep(1000);

	}
	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
