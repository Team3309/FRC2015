package org.usfirst.frc.team3309.robot.commands.drive;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardEncoderCounts extends Command {

	private Drive mDrive;
	private boolean isFinished = false;
	private double pidRequestedEncoder;
	private double pidRequestedGyro;

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

		double leftSpeed = throttle - turn;
		double rightSpeed = throttle + turn;

		mDrive.setLeft(leftSpeed);
		mDrive.setRight(rightSpeed);

		if (Math.abs(mDrive.getAverageCount() - pidRequestedEncoder) < 10) {
			doneTimer.start();
		} else {
			doneTimer.stop();
			doneTimer.reset();
		}
	}

	private double runEncoderPID() {
		double currentValue = mDrive.getAverageCount();
		double currentError = pidRequestedEncoder - currentValue;
		double pid = PID.runPIDWithError(currentError, lastEncoderError, .002, .000);
		lastEncoderError = currentError;
		return pid;
	}

	private double runGyroPID() {
		double currentValue = mDrive.getAngle();
		double currentError = pidRequestedGyro - currentValue;
		double pid = PID.runPIDWithError(currentError, lastGyroError, .002, .000);
		lastGyroError = currentError;
		return pid;
	}

	

	@Override
	protected boolean isFinished() {
		return isTimedOut() || (doneTimer.get() > 500000);
	}

	@Override
	protected void end() {
		mDrive.stopDrive();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}

}
