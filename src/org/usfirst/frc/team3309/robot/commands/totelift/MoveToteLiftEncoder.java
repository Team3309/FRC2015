package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftEncoder extends Command {

	int setPoint = 0;
	private ToteLift mToteLift = ToteLift.getInstance();
	double lastError = 0;
	private Timer doneTimer = new Timer();
	private boolean startedTimer = false;
	private double kP;
	private double kD;

	private int startCount = 0;

	public MoveToteLiftEncoder(ToteLevel level) {
		kP = level.getkP();
		kD = level.getkD();

		setPoint = level.getSetPoint();
	}

	public MoveToteLiftEncoder(int value, int startCount) {
		setPoint = value;

		this.startCount = startCount;
		// DEFAULT VALUES
		kP = .007;
		kD = 000;
	}

	public MoveToteLiftEncoder(int value, double kP, double kD) {
		setPoint = value;
		this.kP = kP;
		this.kD = kD;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if (Drive.getInstance().getAverageCount() > startCount) {
			double error = setPoint - mToteLift.getLiftEncoder();
			double pid = PID.runPIDWithError(error, lastError, kP, kD);
			lastError = error;
			mToteLift.setToteLiftPower(pid);

			if (Math.abs(mToteLift.getLiftEncoder() - setPoint) < 170 && !startedTimer) {
				doneTimer.start();
				startedTimer = true;
			} else if (!(Math.abs(mToteLift.getLiftEncoder() - setPoint) < 170)) {
				doneTimer.stop();
				doneTimer.reset();
				startedTimer = false;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return doneTimer.get() > .5;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

	}

}
