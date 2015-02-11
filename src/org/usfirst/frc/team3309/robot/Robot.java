package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.subsystems.Drive;
import org.usfirst.frc.team3309.subsystems.Intake;
import org.usfirst.frc.team3309.subsystems.IntakeLift;
import org.usfirst.frc.team3309.subsystems.ToteLift;
import org.usfirst.team3309.commands.pid.PIDLoopCommand;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
@SuppressWarnings("unused")
public class Robot extends IterativeRobot {
	XboxController driverController = Controllers.getInstance().driverController;
	XboxController operatorController = Controllers.getInstance().OperatorController;

	private boolean buttonPressedLastTime = false;
	private Scheduler scheduler;
	private Drive mDrive;
	private Intake mIntake;
	private ToteLift mToteLift;
	private IntakeLift mIntakeLift;

	// The command that will begin running at the start of autonomous
	private Command autoCommand;

	private boolean constantChanger = true;

	// Runs when Robot is turned on
	public void robotInit() {
		if (constantChanger) {
			// constantChanger frame = new constantChanger();
		}
		scheduler = Scheduler.getInstance();
		// setSubsystems to the Instance of each
		mDrive = Drive.getInstance();
		mIntake = Intake.getInstance();
		mToteLift = ToteLift.getInstance();
		mIntakeLift = IntakeLift.getInstance();
		// sets it so all information about the drive will be printed repeatedly
		// during driving
		// mDrive.setPrintingDriveInfo(false);
	}

	// When first put into disabled mode
	public void disabledInit() {
		//scheduler.disable();
	}

	// Called repeatedly in disabled mode
	public void disabledPeriodic() {

	}

	// Init to Auto
	public void autonomousInit() {
		mDrive.resetEncoders();
		autoCommand = new PIDLoopCommand(.001, 0, 0, 3000);
		autoCommand.start();
	}

	// This function is called periodically during autonomous
	public void autonomousPeriodic() {
		scheduler.run();
		
	}

	// Init to Tele
	public void teleopInit() {
		if(autoCommand != null)
			autoCommand.cancel();
		mDrive.resetGyro();
		// autoCommand.cancel();
	}

	// This function is called periodically during operator control
	public void teleopPeriodic() {
		scheduler.run();

		// gets all 4 axis from driver remote and depending on what drive the
		// robot is in, the values will be used accordingly
		mDrive.drive(driverController.getLeftX(), driverController.getLeftY(), driverController.getRightX(), driverController.getRightY());

		// checks if triggers are pressed in any way shape or form
		if (driverController.getRB()) {
			mIntake.runClawInward();
		} else if (driverController.getLB()) {
			mIntake.runClawOutward();
		} else {
			mIntake.stopClaw();
		}

	}
}
