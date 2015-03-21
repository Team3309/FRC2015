package org.usfirst.frc.team3309.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.Intake;
import org.usfirst.frc.team3309.robot.subsystems.IntakeLift;

import edu.wpi.first.wpilibj.Timer;

public class IntakeRunContinuous extends Command {

	Timer doneTimer = new Timer();
	double time;
	int code;
	double speed;
	Intake mIntake = Intake.getInstance();

	/*
	 * CODES FOR INTAKE: 0: Inward 1: Outward 2: Reverse (right outward) 3:
	 * Reverse (left outward)
	 */
	public IntakeRunContinuous(int code, double speed) {
		super();
		this.speed = speed;
		this.code = code;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		IntakeLift.getInstance().stop();
		doneTimer.start();
		Drive.getInstance().stopDrive();

		// ensure it runs once
		execute();
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
		return true;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
