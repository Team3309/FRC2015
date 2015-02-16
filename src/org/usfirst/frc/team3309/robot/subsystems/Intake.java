package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.SuperSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

@SuppressWarnings("unused")
public class Intake {
	private Victor rightClaw;
	private Victor leftClaw;
	private SuperSolenoid intakeSolenoid;

	private static Intake instance;

	public static Intake getInstance() {
		if (instance == null) {
			instance = new Intake();
		}
		return instance;
	}

	private Intake() {
		rightClaw = new Victor(RobotMap.CLAW_RIGHTSIDE);
		leftClaw = new Victor(RobotMap.CLAW_LEFTSIDE);
		intakeSolenoid = new SuperSolenoid(RobotMap.INTAKE_SOLENOID);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void runClawInward() {
		rightClaw.set(1);
		leftClaw.set(1);
	}

	public void runClawOutward() {
		rightClaw.set(-1);
		leftClaw.set(-1);
	}

	public void stopClaw() {
		rightClaw.set(0);
		leftClaw.set(0);
	}

	public void turnOnSolenoid() {
		intakeSolenoid.turnOnSolenoid();;
	}

	public void turnOffSolenoid() {
		intakeSolenoid.turnOffSolenoid();
	}

	public void toggleSolenoid() {
		intakeSolenoid.toggleSolenoid();
	}
	public void notPressedSolenpid() {
		intakeSolenoid.notPressed();
	}

}
