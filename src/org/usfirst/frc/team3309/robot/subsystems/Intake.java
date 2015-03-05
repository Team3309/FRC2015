package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.IntakeSolenoid;
import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.SuperSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

@SuppressWarnings("unused")
public class Intake {
	private Victor rightClaw;
	private Victor leftClaw;
	private IntakeSolenoid intakeSolenoid;

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
		intakeSolenoid = new IntakeSolenoid(RobotMap.INTAKE_SOLENOID_FRONT, RobotMap.INTAKE_SOLENOID_BACK);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	/*public void runClawInward() {
		rightClaw.set(1);
		leftClaw.set(-1);
	}*/
	
	public void runClawInward(double power) {
		if(Math.abs(power) < .1) {
			return;
		}
		System.out.println("INWARD: " + power);
		double s = power;
		System.out.println("INWARD ASDFH AD : " + s);
		rightClaw.set(s);
		leftClaw.set(-s);
	}

	/*public void runClawOutward() {
		rightClaw.set(-1);
		leftClaw.set(1);
	}*/
	
	public void runClawOutward(double power) {
		if(Math.abs(power) < .1) {
			return;
		}
		rightClaw.set(-power);
		leftClaw.set(power);
	}

	public void stopClaw() {
		rightClaw.set(0);
		leftClaw.set(0);
	}
	
	public void runReverseRight(double power) {
		rightClaw.set(-power);
		leftClaw.set(-power);
	}
	
	public void runReverseLeft(double power) {
		rightClaw.set(power);
		leftClaw.set(power);
	}

	public void setExtended() {
		intakeSolenoid.setExtended();
		;
	}

	public void setRetracted() {
		intakeSolenoid.setRetracted();
	}

	public void setNeutral() {
		intakeSolenoid.setNeutral();
	}

	private boolean buttonLastState = false;

	public void toggleSolenoid() {
		if (buttonLastState == false) {
			intakeSolenoid.toggleSolenoid();
			buttonLastState = true;
		}
	}

	public void notActivated() {
		buttonLastState = false;
	}

}
