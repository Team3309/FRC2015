package org.usfirst.frc.team3309.robot.commands.pid;

import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

public abstract class PIDLoopCommand extends Command implements PIDSource, PIDOutput {

	private boolean isFinished = false;
	private double kP;
	private double kI;
	private double kD;
	private double pidRequested;
	private Drive mDrive;

	protected PIDController controller;
	protected double KPCONSTANT = .01;

	public PIDLoopCommand(double kP, double kI, double kD, double pidRequested) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.pidRequested = pidRequested;

		controller = new PIDController(kP, kI, kD, this, this);
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		mDrive = Drive.getInstance();
	}

	@Override
	protected void execute() {
		// PID Loop for Straight Strafing

		// Read the sensor value
	}

	@Override
	protected boolean isFinished() {
		return false;
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
