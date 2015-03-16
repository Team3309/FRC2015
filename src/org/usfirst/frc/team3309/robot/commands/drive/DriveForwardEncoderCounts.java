package org.usfirst.frc.team3309.robot.commands.drive;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.IntakeLift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardEncoderCounts extends Command {

	private Drive mDrive;
	private boolean isFinished = false;
	private double pidRequestedEncoder;
	private double pidRequestedGyro;

	private boolean startedTimer = false;
	private double lastGyroError = 0;
	private double lastEncoderError = 0;

	private Timer doneTimer = new Timer();

	public DriveForwardEncoderCounts(double request) {
		mDrive = Drive.getInstance();
		pidRequestedEncoder = request;
		pidRequestedGyro = mDrive.getAngle();
		setTimeout(5);
	}

	@Override
	protected void initialize() {
		mDrive.resetEncoders();
	}

	@Override
	protected void execute() {
		double throttle = runEncoderPID();
		double turn = runGyroPID();

		double leftSpeed = throttle + turn;
		double rightSpeed = throttle - turn;

		mDrive.setLeft(leftSpeed);
		mDrive.setRight(rightSpeed);
		
		System.out.println("LEFTSPEED: " + leftSpeed);
		System.out.println("RIGHTSPEED: " + rightSpeed);
		
		System.out.println("LEFT EN: " + mDrive.getLeftEncoder());
		System.out.println("RIGHT EN: " + mDrive.getRightEncoder());

		System.out.println("ERROR: " + Math.abs(mDrive.getAverageCount() - pidRequestedEncoder));
		if (Math.abs(mDrive.getAverageCount() - pidRequestedEncoder) < 50 && !startedTimer) {
			System.out.println("IT WORKED");
			doneTimer.start();
			startedTimer = true;
		} else if(!(Math.abs(mDrive.getAverageCount() - pidRequestedEncoder) < 170)){
			System.out.println();
			doneTimer.stop();
			doneTimer.reset();
			startedTimer = false;
		}
	}

	//.0032 with 2
	private double runEncoderPID() {
		double currentValue = mDrive.getAverageCount();
		double currentError = pidRequestedEncoder - currentValue;
		double pid = PID.runPIDWithError(currentError, lastEncoderError, .002, .016);
		lastEncoderError = currentError;
		return pid;
	}

	private double runGyroPID() {
		double currentValue = mDrive.getAngle();
		System.out.println("GYRO: " + currentValue);
		double currentError = pidRequestedGyro - currentValue;
		double pid = PID.runPIDWithError(currentError, lastGyroError, .05, .000);
		lastGyroError = currentError;
		return pid;
	}

	

	@Override
	protected boolean isFinished() {
		//return !DriverStation.getInstance().isAutonomous();
		System.out.println("TIMER: " + doneTimer.get());
		
		return (doneTimer.get() > .5);
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
