package org.usfirst.frc.team3309.robot.commands.drive;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command implements PIDSource, PIDOutput{

	private double pidRequested;
	private Drive mDrive;
	private double currentValue;
	private boolean isFinished;
	private double lastError = 0;
	private Timer doneTimer = new Timer();
	private boolean startedTimer = false;
	
	public TurnToAngle(double angle) {
		pidRequested = angle;
	}
	@Override
	protected void initialize() {
		mDrive = Drive.getInstance();
	}

	@Override
	protected void execute() {
		currentValue = mDrive.getAngle();
		double pid = runGyroPID();
		mDrive.setLeft(pid);
		mDrive.setRight(-pid);
		
		if (Math.abs(mDrive.getAngle() - pidRequested) < 30 && !startedTimer) {
			System.out.println("IT WORKED");
			doneTimer.start();
			startedTimer = true;
		} else if(!(Math.abs(mDrive.getAngle() - pidRequested) < 30)){
			System.out.println();
			doneTimer.stop();
			doneTimer.reset();
			startedTimer = false;
		}
	}
	
	private double runGyroPID() {
		double currentValue = mDrive.getAngle();
		System.out.println("GYRO: " + currentValue);
		double currentError = pidRequested - currentValue;
		double pid = PID.runPIDWithError(currentError, lastError, .04, .000);
		lastError = currentError;
		return pid;
	}

	@Override
	protected boolean isFinished() {
		
		return doneTimer.get() > .25;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pidWrite(double output) {
		mDrive.setLeft(output);
		mDrive.setRight(-output);
	}

	@Override
	public double pidGet() {
		// use the left encoder(or the slower sides encoder)
		return currentValue;
	}


	
}
