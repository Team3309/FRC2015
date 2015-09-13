package org.usfirst.frc.team3309.robot.commands.intakelift;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.IntakeLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeLiftRunEncoder extends Command {

	private Timer doneTimer = new Timer();
	private IntakeLift mIntakeLift = IntakeLift.getInstance();
	private boolean startedTimer = false;

	public IntakeLiftRunEncoder(int encodercounts) {
		IntakeLift.getInstance().setLeftSetPoint(encodercounts);
		IntakeLift.getInstance().setRightSetPoint(encodercounts);
	}

	@Override
	protected void initialize() {
		Drive.getInstance().stopDrive();
	}

	@Override
	protected void execute() {
		// IntakeLift.getInstance().start();
		
		System.out.println("RUNNING ");
		if (IntakeLiftCommand.getInstance().isDone() && !startedTimer) {
			System.out.println("IT WORKED");
			doneTimer.start();
			startedTimer = true;
		} else if (!IntakeLiftCommand.getInstance().isDone()) {
			System.out.println();
			doneTimer.stop();
			doneTimer.reset();
			startedTimer = false;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return (doneTimer.get() > .5);
	}

	@Override
	protected void end() {
		// IntakeLift.getInstance().stop();
		mIntakeLift.runLiftWithJoystick(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
