package org.usfirst.frc.team3309.robot.commands.totelift;

import edu.wpi.first.wpilibj.command.Command;

public class toteLiftIsDone extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		AutomaticToteLiftCommand.getInstance().commandRunning = false;

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
