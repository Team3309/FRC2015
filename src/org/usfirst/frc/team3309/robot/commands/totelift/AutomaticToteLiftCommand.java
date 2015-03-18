package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.driverstation.Controllers;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutomaticToteLiftCommand extends Command {

	private ToteLift mToteLift = ToteLift.getInstance();
	private static AutomaticToteLiftCommand instance;
	private int toteToggleCount = 0;

	private boolean manualControl = false;

	private int currentRunningState = 0;
	private final int NOT_RUNNING = 0;
	private final int RUNNING_UP = 1;
	private final int RUNNING_DOWN = 2;

	private MoveToteLiftEncoder upCommand;
	private MoveToteLiftEncoder downCommand;

	private double testKP = 000;
	private double testKD = 000;

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
		SmartDashboard.putNumber("tote lift kp", testKP);
		SmartDashboard.putNumber("tote lift kd", testKD);
	}

	private boolean buttonLastState = false;
	
	@Override
	protected void execute() {
		// set manual control to whether or not the home button is pressed
		manualControl = Controllers.getInstance().OperatorController.getHome();

		// if manual control is enabled, set the toteLift to joystick, then stop
		if (manualControl) {
			// mToteLift.runLiftAt(Controllers.getInstance().OperatorController.getRightY());
			
			

			if (buttonLastState == false) {
					startGoingUp();
					buttonLastState = true;
			}
			buttonLastState = false;
			
			return;
		}

		if (mToteLift.isToteSensorToggle()) {
			toteToggleCount++;
		}

		// if the button is pressed and it is the first time the button is being
		// read as pressed
		if (mToteLift.isToteSensorPressed() && mToteLift.isToteSensorToggle()) {
			startGoingUp();
		}

		if (currentRunningState == RUNNING_UP) {

			// if totelift is done going up
			if (!upCommand.isRunning()) {
				startGoingDown();
			}
		} else if (currentRunningState == RUNNING_DOWN) {

			// if totelift is back at bottom
			if (!downCommand.isRunning()) {
				currentRunningState = NOT_RUNNING;
			}
		} else if (currentRunningState == NOT_RUNNING) {

		}

		updateConstants();

	}

	private void updateConstants() {
		testKP = SmartDashboard.getNumber("tote lift kp", testKP);
		testKD = SmartDashboard.getNumber("tote lift kd", testKD);
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
		// divide by two because, one toggle from off to on, and one toggle from
		// on to off = 1 tote
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

	// the comments are for testing constants
	private void startGoingUp() {
		upCommand = new MoveToteLiftEncoder(0, 0, 0);
		// upCommand = new MoveToteLiftEncoder(getToteLevel(toteToggleCount));
		currentRunningState = RUNNING_UP;
		upCommand.start();
	}

	private void startGoingDown() {
		downCommand = new MoveToteLiftEncoder(0, 0, 0);
		// downCommand = new MoveToteLiftEncoder(ToteLevel.BOTTOM);
		currentRunningState = RUNNING_DOWN;
		downCommand.start();
	}
}
