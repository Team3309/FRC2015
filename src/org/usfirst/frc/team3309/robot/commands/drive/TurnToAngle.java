package org.usfirst.frc.team3309.robot.commands.drive;

import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command implements PIDSource, PIDOutput{

	private double pidRequested;
	private Drive mDrive;
	private PIDController controller;
	private double currentValue;
	private boolean isFinished;
	
	public TurnToAngle(double angle) {
		pidRequested = angle;
	}
	@Override
	protected void initialize() {
		mDrive = Drive.getInstance();
		controller = new PIDController(.002, 0, 0, this, this);
	}

	@Override
	protected void execute() {
		currentValue = mDrive.getAngle();
		
	}

	@Override
	protected boolean isFinished() {
		if (currentValue > pidRequested - 50 && currentValue < pidRequested + 50) {
			System.out.println("PID IS DONE");
			isFinished = true;
			for (int i = 0; i < 2000; i++) {
				execute();
				if (!(currentValue > pidRequested - 50) && !(currentValue < pidRequested + 50))
					isFinished = false;
			}
		}
		return isFinished;
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
