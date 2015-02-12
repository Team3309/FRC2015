package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class IntakeLift {

	private static IntakeLift instance;
	private Victor leftLift;
	private Victor rightLift;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	public static IntakeLift getInstance() {
		if (instance == null) {
			instance = new IntakeLift();
		}
		return instance;
	}

	private IntakeLift() {
		leftLift = new Victor(RobotMap.INTAKE_LIFT_LEFT);
		rightLift = new Victor(RobotMap.INTAKE_LIFT_RIGHT);
		leftEncoder = new Encoder(RobotMap.INTAKE_LIFT_LEFT_ENCODER_A, RobotMap.INTAKE_LIFT_LEFT_ENCODER_B, false, CounterBase.EncodingType.k1X);
		rightEncoder = new Encoder(RobotMap.INTAKE_LIFT_RIGHT_ENCODER_A, RobotMap.INTAKE_LIFT_RIGHT_ENCODER_B, false, CounterBase.EncodingType.k1X);
	}

	public void runLiftAt(double power) {
		runLeftLiftAt(power);
		runRightLiftAt(power);
	}

	public void runLeftLiftAt(double power) {
		leftLift.set(power);
	}

	public void runRightLiftAt(double power) {
		rightLift.set(power);
	}
}
