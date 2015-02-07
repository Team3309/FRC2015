package org.usfirst.frc.team3309.subsystems;

public class IntakeLift {
	private static IntakeLift instance;

	public static IntakeLift getInstance() {
		if (instance == null) {
			instance = new IntakeLift();
		}
		return instance;
	}

	private IntakeLift() {

	}
}
