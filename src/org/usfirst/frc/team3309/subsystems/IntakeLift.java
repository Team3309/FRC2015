package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.Victor;

public class IntakeLift {
	private static IntakeLift instance;

	private Victor leftLift;
	private Victor rightLift;
	
	public static IntakeLift getInstance() {
		if (instance == null) {
			instance = new IntakeLift();
		}
		return instance;
	}

	private IntakeLift() {

	}
}
