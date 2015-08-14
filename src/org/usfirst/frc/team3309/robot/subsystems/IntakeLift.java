package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.driverstation.Controllers;
import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.commands.intakelift.IntakeLiftCommand;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeLift extends Subsystem {

	private static IntakeLift instance;
	private Victor leftLift;
	private Victor rightLift;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	private double rightSetPoint = 0;
	private double leftSetPoint = 0;

	public double getRightSetPoint() {
		return rightSetPoint;
	}

	public void setRightSetPoint(double rightSetPoint) {
		this.rightSetPoint = rightSetPoint;
	}

	public double getLeftSetPoint() {
		return leftSetPoint;
	}

	public void setLeftSetPoint(double leftSetPoint) {
		this.leftSetPoint = leftSetPoint;
	}

	private double MAXSPEED = 125;

	public static IntakeLift getInstance() {
		if (instance == null) {
			instance = new IntakeLift();
			instance.setDefaultCommand(IntakeLiftCommand.getInstance());
		}
		return instance;
	}

	private IntakeLift() {
		leftLift = new Victor(RobotMap.INTAKE_LIFT_LEFT);
		rightLift = new Victor(RobotMap.INTAKE_LIFT_RIGHT);
		leftEncoder = new Encoder(RobotMap.INTAKE_LIFT_LEFT_ENCODER_A, RobotMap.INTAKE_LIFT_LEFT_ENCODER_B, false, CounterBase.EncodingType.k1X);
		rightEncoder = new Encoder(RobotMap.INTAKE_LIFT_RIGHT_ENCODER_A, RobotMap.INTAKE_LIFT_RIGHT_ENCODER_B, true, CounterBase.EncodingType.k1X);
	}

	public double getRightEncoder() {
		return rightEncoder.get();
	}

	public double getLeftEncoder() {
		return leftEncoder.get();
	}

	public void runLiftAt(double power) {
		runLeftLiftAt(-power);
		runRightLiftAt(power);
	}

	public void runLiftWithJoystick(double power) {
		double rightSetPoint = leftEncoder.get() + power * MAXSPEED;
		double leftSetPoint = leftEncoder.get() + power * MAXSPEED;
		if(Controllers.getInstance().driverController.getA()) {
			rightSetPoint = leftEncoder.get() + .95 * MAXSPEED;
			leftSetPoint = leftEncoder.get() + .95 * MAXSPEED;
		}
		
		// System.out.println("SETPOINT: " + setPoint);
		setRightSetPoint(rightSetPoint);
		setLeftSetPoint(leftSetPoint);
		
		System.out.println("Left " + getLeftEncoder());
		System.out.println("Right " + getRightEncoder());
	}

	public void runLeftLiftAt(double power) {
		leftLift.set(power);
	}

	public void runRightLiftAt(double power) {
		rightLift.set(power);
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
		System.out.println("RESET");
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void start() {
		// TODO Auto-generated method stub
	}

	public void stop() {
		this.setLeftSetPoint(leftEncoder.get());
		this.setRightSetPoint(leftEncoder.get());
	}
}
