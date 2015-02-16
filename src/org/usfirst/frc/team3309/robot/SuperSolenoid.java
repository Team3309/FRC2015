package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class SuperSolenoid extends Solenoid{

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
		set(!get());
	}
	

}
