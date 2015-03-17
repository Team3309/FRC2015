package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.command.Command;

public class AutomaticToteLiftCommand extends Command {

	private ToteLift mToteLift = ToteLift.getInstance();
	private static AutomaticToteLiftCommand instance;
	private int topEncoderCount;
	private int setPoint = 0;

	private AutomaticToteLiftCommand() {
		super();
		requires(mToteLift);
	}

	public static AutomaticToteLiftCommand getInstance() {
		if (instance == null) {
			instance = new AutomaticToteLiftCommand();
		}
		return instance;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

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
