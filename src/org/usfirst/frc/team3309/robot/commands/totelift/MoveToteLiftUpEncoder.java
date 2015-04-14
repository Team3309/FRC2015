package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftUpEncoder extends Command {
	int setPoint = 0;
	private ToteLift mToteLift = ToteLift.getInstance();
	double lastError = 0;
	private Timer doneTimer = new Timer();
	private boolean startedTimer = false;
	private double kP;
	private double kD;

	private int startCount = 0;

	public MoveToteLiftUpEncoder(int value) {
		setPoint = value;
		kP = .04;
		kD = 000;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if (mToteLift.getLiftEncoder() <= 1000) {
			mToteLift.runLiftAt(1);
		}
	}

	@Override
	protected boolean isFinished() {
		return mToteLift.getLiftEncoder() >= 1000;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}
}
