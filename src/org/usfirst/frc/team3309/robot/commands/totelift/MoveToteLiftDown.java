package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftDown extends Command {

	private int startCount = 0;
	private boolean started = false;
	private Timer startTimer = new Timer();
	private ToteLift mToteLift = ToteLift.getInstance();
	double setPoint = 0;
	private double kP = .01;
	private double kD = .01;
	private boolean startedTimer = false;
	double lastError = 0;

	public MoveToteLiftDown(int counts) {
		setPoint = 20;
		this.startCount = counts;
		startTimer.reset();
		startTimer.stop();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		if (Math.abs(Drive.getInstance().getAverageCount()) > Math.abs(startCount)) {
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
				startTimer.start();
				startedTimer = true;
			} else if (!(Math.abs(mToteLift.getLiftEncoder() - setPoint) < 50)) {
				startTimer.stop();
				startTimer.reset();
				startedTimer = false;
			}

		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return startTimer.get() > .5 && Drive.getInstance().getAverageCount() > startCount;
	}

	@Override
	protected void end() {

		ToteLift.getInstance().runLiftAt(0);
		MoveDownUntillLimit com = new MoveDownUntillLimit();
		com.start();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}

/*
 * ARIZONA MOVE TOTE if (Math.abs(Drive.getInstance().getAverageCount()) >
 * Math.abs(startCount)) { if (!started) { started = true; doneTimer.start(); }
 * 
 * if (doneTimer.get() > .7) { ToteLift.getInstance().runLiftAt(-.7); } else {
 * ToteLift.getInstance().runLiftAt(-1); } System.out.println("RUNNING DOWN"); }
 * System.out.println("NOT RUNNING DOWN");
 */

/*
 * package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftDown extends Command {

	private int startCount = 0;
	private boolean started = false;
	private Timer startTimer = new Timer();
	private ToteLift mToteLift = ToteLift.getInstance();
	double setPoint = 0;
	private double kP = .01;
	private double kD = .01;
	private boolean startedTimer = false;
	double lastError = 0;

	public MoveToteLiftDown(int counts) {
		setPoint = 10;
		this.startCount = counts;
		startTimer.reset();
		startTimer.stop();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		if (Math.abs(Drive.getInstance().getAverageCount()) > Math.abs(startCount)) {
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
				startTimer.start();
				startedTimer = true;
			} else if (!(Math.abs(mToteLift.getLiftEncoder() - setPoint) < 50)) {
				startTimer.stop();
				startTimer.reset();
				startedTimer = false;
			}

		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return startTimer.get() > .5 && Drive.getInstance().getAverageCount() > startCount;
	}

	@Override
	protected void end() {

		ToteLift.getInstance().runLiftAt(0);
		MoveDownUntillLimit com = new MoveDownUntillLimit();
		com.start();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}

/*
 * ARIZONA MOVE TOTE if (Math.abs(Drive.getInstance().getAverageCount()) >
 * Math.abs(startCount)) { if (!started) { started = true; doneTimer.start(); }
 * 
 * if (doneTimer.get() > .7) { ToteLift.getInstance().runLiftAt(-.7); } else {
 * ToteLift.getInstance().runLiftAt(-1); } System.out.println("RUNNING DOWN"); }
 * System.out.println("NOT RUNNING DOWN");
 */

