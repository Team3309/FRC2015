package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftDownEncoder extends Command {

	private int setPoint = 0;
	private double lastError = 0;
	private ToteLift mToteLift = ToteLift.getInstance();
	private Timer doneTimer = new Timer();
	private boolean startedTimer = false;
	private double kP = .01;
	private double kD = .01;

	public MoveToteLiftDownEncoder(int value) {
		this.setPoint = value;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {

		double error = setPoint - mToteLift.getLiftEncoder();
		double pid = PID.runPIDWithError(error, lastError, kP, kD);
		lastError = error;
		if (pid > 1)
			pid = 1;
		else if (pid < -1)
			pid = -1;
		mToteLift.runLiftAt(pid);
		System.out.println("TOTE LIFT down PID: " + pid);
		System.out.println("TOTE LIFT en: " + mToteLift.getLiftEncoder());
		if (Math.abs(mToteLift.getLiftEncoder() - setPoint) < 50 && !startedTimer) {
			doneTimer.start();
			startedTimer = true;
		} else if (!(Math.abs(mToteLift.getLiftEncoder() - setPoint) < 50)) {
			doneTimer.stop();
			doneTimer.reset();
			startedTimer = false;
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return doneTimer.get() > .5 || mToteLift.getBot();
	}

	@Override
	protected void end() {
		System.out.println("DONE");
		mToteLift.setToteLiftPower(0);
		mToteLift.resetEncoder();

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
