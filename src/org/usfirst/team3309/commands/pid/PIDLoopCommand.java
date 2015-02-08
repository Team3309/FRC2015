package org.usfirst.team3309.commands.pid;

import org.usfirst.frc.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class PIDLoopCommand extends Command {

	private boolean isFinished = false;
	private double kP;
	private double kI;
	private double kD;
	private double pidRequested;

	public PIDLoopCommand(double kP, double kI, double kD, double pidRequested) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.pidRequested = pidRequested;
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		mDrive = Drive.getInstance();
	}

	Drive mDrive;

	@Override
	protected void execute() {
		// PID Loop for Straight Strafing

		// Read the sensor value
		double pidSensorCurrentValue = mDrive.getLeftEncoder();

		// calculate error
		double pidError = pidSensorCurrentValue - pidRequested;
		// System.out.println(pidSensorCurrentValue + " - " +
		// pidRequestedValue + " = Error: " + pidError);

		// calculate drive
		double pidDrive = ((kP * pidError));
		mDrive.setLeft(-pidDrive);
		mDrive.setRight(pidDrive);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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

}