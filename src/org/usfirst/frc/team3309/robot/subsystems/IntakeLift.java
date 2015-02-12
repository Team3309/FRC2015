package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;

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
		leftLift = new Victor(RobotMap.INTAKE_LIFT_LEFT);
		rightLift = new Victor(RobotMap.INTAKE_LIFT_RIGHT);
	}
	
	public void runLiftAt(double power) {
		leftLift.set(power);
		rightLift.set(power);
	}
}
