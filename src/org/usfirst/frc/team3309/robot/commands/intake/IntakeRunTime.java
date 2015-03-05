package org.usfirst.frc.team3309.robot.commands.intake;

import org.usfirst.frc.team3309.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeRunTime extends Command{

	Timer doneTimer = new Timer();
	double time;
	int code;
	double speed;
	Intake mIntake = Intake.getInstance();
	/*
	 * CODES FOR INTAKE:
	 * 0: Inward
	 * 1: Outward
	 * 2: Reverse (right outward)
	 * 3: Reverse (left outward)
	 * 
	 * 
	 */
	public IntakeRunTime(double time, int code, double speed) {
		super();
		this.time = time * 1000;
		this.speed = speed;
		this.code = code;
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		doneTimer.start();
	}

	@Override
	protected void execute() {
		switch (code) {
		case 0:
			mIntake.runClawInward(speed);
			break;
		case 1:
			mIntake.runClawOutward(speed);
			break;
		case 2:
			mIntake.runReverseRight(speed);
			break;
		case 3:
			mIntake.runReverseLeft(speed);
			break;
		default:
			break;
		}
	}

	@Override
	protected boolean isFinished() {
		return (doneTimer.get() > time);
	}

	@Override
	protected void end() {
		mIntake.stopClaw();
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
