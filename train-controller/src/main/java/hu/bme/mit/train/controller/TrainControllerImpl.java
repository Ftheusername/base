package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController, Runnable {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Thread th; 

	public TrainControllerImpl()
	{
		th = new Thread();
		th.start(); 	
	}	

	public void run()
	{	
		followSpeed(); 

		try
		{
			th.sleep(1000);
		} catch (Exception e) {
			System.out.println("nem aludt el a szál");
		} 
		
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
