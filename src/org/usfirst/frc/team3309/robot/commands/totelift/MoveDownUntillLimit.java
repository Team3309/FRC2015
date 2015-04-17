package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.command.Command;

public class MoveDownUntillLimit extends Command {

	private ToteLift mToteLift = ToteLift.getInstance();

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		mToteLift.runLiftAt(-.4);

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return mToteLift.getBot();
	}

	@Override
	protected void end() {
		mToteLift.runLiftAt(0);
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
