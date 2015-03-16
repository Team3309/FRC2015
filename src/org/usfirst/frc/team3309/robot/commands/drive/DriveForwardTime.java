package org.usfirst.frc.team3309.robot.commands.drive;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardTime extends Command {

	private Drive mDrive;
	private boolean isFinished = false;
	private double pidRequestedEncoder;
	private double pidRequestedGyro;

	private boolean startedTimer = false;
	private double time = 0;
	private double power = 0;

	private Timer doneTimer = new Timer();

	public DriveForwardTime(double time, double power) {
		mDrive = Drive.getInstance();
		this.time = time;
		this.power = power;
		setTimeout(5);
	}

	@Override
	protected void initialize() {
		mDrive.resetEncoders();
		doneTimer.start();
	}

	@Override
	protected void execute() {
		mDrive.drive(0, power, 0, 0);
	}

	@Override
	protected boolean isFinished() {
		return (doneTimer.get() > time);
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
