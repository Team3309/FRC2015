package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftEncoder extends Command {

	int setPoint = 0;
	private ToteLift mToteLift = ToteLift.getInstance();
	double lastError = 0;
	private Timer doneTimer = new Timer();
	private boolean startedTimer = false;

	public MoveToteLiftEncoder(int value) {
		setPoint = value;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double error = setPoint - mToteLift.getLiftEncoder();
		double pid = PID.runPIDWithError(error, lastError, .007, .000);
		lastError = error;
		mToteLift.setToteLiftPower(pid);

		if (Math.abs(mToteLift.getLiftEncoder() - setPoint) < 170 && !startedTimer) {
			System.out.println("IT WORKED");
			doneTimer.start();
			startedTimer = true;
		} else if (!(Math.abs(mToteLift.getLiftEncoder() - setPoint) < 170)) {
			System.out.println();
			doneTimer.stop();
			doneTimer.reset();
			startedTimer = false;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return doneTimer.get() > .5;
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
