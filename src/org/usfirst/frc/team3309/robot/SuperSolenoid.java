package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class SuperSolenoid extends Solenoid{

	private boolean buttonLastPressed = false;
	public SuperSolenoid(int channel) {
		super(channel);
	}
	
	public void turnOnSolenoid() {
		set(true);
	}

	public void turnOffSolenoid() {
		set(false);
	}

	public void toggleSolenoid() {
		if(!buttonLastPressed) {
			set(!get());
			buttonLastPressed = true;
		}
	}
	
	public void notPressed() {
		buttonLastPressed = false;
	}
	

}
