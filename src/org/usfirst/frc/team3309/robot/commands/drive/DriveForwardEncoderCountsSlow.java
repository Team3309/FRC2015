package org.usfirst.frc.team3309.robot.commands.drive;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.IntakeLift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardEncoderCountsSlow extends Command {

	private Drive mDrive;
	private boolean isFinished = false;
	private double pidRequestedEncoder;
	private double pidRequestedGyro;

	private boolean startedTimer = false;
	private double lastGyroError = 0;
	private double lastEncoderError = 0;

	private double speed = .3;
	private Timer doneTimer = new Timer();
boolean current = false;
	
	public DriveForwardEncoderCountsSlow(int requested, double requestAngle, double speed) {
		mDrive = Drive.getInstance();
		pidRequestedGyro = requestAngle;
		pidRequestedEncoder = requested;
		this.speed = speed;
	}
	
	public DriveForwardEncoderCountsSlow(int requested, double speed) {
		mDrive = Drive.getInstance();
		current = true;
		pidRequestedEncoder = requested;
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		mDrive.resetEncoders();
		if(current) {
			pidRequestedGyro = Drive.getInstance().getAngle();
		}
	}

	@Override
	protected void execute() {
		// double throttle = runEncoderPID();
		double turn = runGyroPID();

		double leftSpeed = speed + turn;
		double rightSpeed = speed - turn;

		mDrive.setLeft(leftSpeed);
		mDrive.setRight(rightSpeed);

		//System.out.println("LEFTSPEED: " + leftSpeed);
		//System.out.println("RIGHTSPEED: " + rightSpeed);

	//	System.out.println("LEFT EN: " + mDrive.getLeftEncoder());
//		System.out.println("RIGHT EN: " + mDrive.getRightEncoder());

	}

	// .0032 with 2
	private double runEncoderPID() {
		double currentValue = mDrive.getAverageCount();
		double currentError = pidRequestedEncoder - currentValue;
		double pid = PID.runPIDWithError(currentError, lastEncoderError, .002, .016);
		lastEncoderError = currentError;
		return pid;
	}

	private double runGyroPID() {
		double currentValue = mDrive.getAngle();
		//NORMAL: .1
		//mini: .17
		double currentError = pidRequestedGyro - currentValue;
		double pid = PID.runPIDWithError(currentError, lastGyroError, .17, .000);
		lastGyroError = currentError;
		return pid;
	}

	@Override
	protected boolean isFinished() {
		// return !DriverStation.getInstance().isAutonomous();

		return (Math.abs(mDrive.getAverageCount()) > Math.abs(pidRequestedEncoder));
	}

	@Override
	protected void end() {
		mDrive.stopDrive();
		System.out.println("STOP");
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}

}
