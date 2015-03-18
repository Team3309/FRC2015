package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.command.Command;

public class AutomaticToteLiftCommand extends Command {

	private ToteLift mToteLift = ToteLift.getInstance();
	private static AutomaticToteLiftCommand instance;
	private int topEncoderCount;
	private int setPoint = 0;
	private int toteToggleCount = 0;

	private int currentRunningState = 0;
	private final int NOT_RUNNING = 0;
	private final int RUNNING_UP = 1;
	private final int RUNNING_DOWN = 2;

	private MoveToteLiftEncoder upCommand;

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
		if (mToteLift.isToteSensorToggle()) {
			toteToggleCount++;
		}
		
		//if the button is pressed and it is the first time the button is being read as pressed
		if (mToteLift.isToteSensorPressed() && mToteLift.isToteSensorToggle()) {
			startGoingUp();
		}
		
		if(currentRunningState == RUNNING_UP) {
			
		}else if(currentRunningState == RUNNING_DOWN) {
			
		}else if(currentRunningState == NOT_RUNNING){
			
		}
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

	private ToteLevel getToteLevel(int toteCount) {
		//divide by two because, one toggle from off to on, and one toggle from on to off = 1 tote
		int count = toteCount / 2;
		ToteLevel level;
		switch (count) {
		case 1:
			level = ToteLevel.ONE_TOTE;
			break;
		case 2:
			level = ToteLevel.TWO_TOTE;
			break;
		case 3:
			level = ToteLevel.THREE_TOTE;
			break;
		case 4:
			level = ToteLevel.FOUR_TOTE;
			break;
		case 5:
			level = ToteLevel.FIVE_TOTE;
			break;
		case 6:
			level = ToteLevel.SIX_TOTE;
			break;
		default:
			level = ToteLevel.ONE_TOTE;
			break;
		}
		return level;
	}
	
	private void startGoingUp() {
		upCommand = new MoveToteLiftEncoder(getToteLevel(toteToggleCount));
		currentRunningState = RUNNING_UP;
	}
}
