package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.driverstation.Controllers;
import org.usfirst.frc.team3309.driverstation.XboxController;
import org.usfirst.frc.team3309.robot.commands.auto.MoveForwardAuto;
import org.usfirst.frc.team3309.robot.commands.auto.YellowToteAuto;
import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.intakelift.IntakeLiftCommand;
import org.usfirst.frc.team3309.robot.commands.pid.PIDLoopCommand;
import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.Intake;
import org.usfirst.frc.team3309.robot.subsystems.IntakeLift;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	//private DigitalInput io = new DigitalInput(10);

	private SendableChooser autoChooser;

	// The command that will begin running at the start of autonomous
	private Command autoCommand;

	private boolean constantChanger = true;

	// Runs when Robot is turned on
	public void robotInit() {
		if (constantChanger) {
			// constantChanger frame = new constantChanger();
		}

		//Compressor comp = new Compressor();
	
		
		scheduler = Scheduler.getInstance();
		// setSubsystems to the Instance of each
		mDrive = Drive.getInstance();
		mIntake = Intake.getInstance();
		mToteLift = ToteLift.getInstance();
		mIntakeLift = IntakeLift.getInstance();
		// sets it so all information about the drive will be printed repeatedly
		// during driving
		// mDrive.setPrintingDriveInfo(false);

		autoChooser = new SendableChooser();
		autoChooser.addDefault("DEFAULT", new DriveForwardEncoderCounts(600));
		autoChooser.addObject("EXPERIMENTAL", new MoveForwardAuto(2000));
		autoChooser.addObject("INTAKE TIME THING", new IntakeRunTime(2000, 0, .4));
		autoChooser.addObject("Yellow Tote", new YellowToteAuto());
		SmartDashboard.putData("AUTO CHOOSER", autoChooser);

	}

	// When first put into disabled mode
	public void disabledInit() {
		
	}

	// Called repeatedly in disabled mode
	public void disabledPeriodic() {

	}

	// Init to Auto
	public void autonomousInit() {
		mDrive.resetEncoders();
		autoCommand =  (CommandGroup) autoChooser.getSelected();
		autoCommand.start();
	}

	// This function is called periodically during autonomous
	public void autonomousPeriodic() {
		scheduler.run();

	}

	// Init to Tele
	public void teleopInit() {
		if (autoCommand != null)
			autoCommand.cancel();
		mDrive.resetGyro();
		mIntakeLift.resetEncoders();
		
		// autoCommand.cancel();

	}

	// This function is called periodically during operator control
	public void teleopPeriodic() {
		scheduler.run();

		// gets all 4 axis from driver remote and depending on what drive the
		// robot is in, the values will be used accordingly
		mDrive.drive(driverController.getLeftX(), driverController.getLeftY(), driverController.getRightX(), driverController.getRightY());

		// mIntakeLift.setMasterVictor(operatorController.getLeftY());
		mIntakeLift.runLiftWithJoystick(operatorController.getLeftY());

		mToteLift.runLiftAt((operatorController.getRightY()));

		//System.out.println(operatorController.getRightTrigger());
		//System.out.println(operatorController.getLeftTrigger());
		
		if(operatorController.getLeftTrigger() > 0) {
			mIntake.runClawInward(operatorController.getLeftTrigger());
		}else if(operatorController.getRightTrigger() > 0) {
			mIntake.runClawOutward(operatorController.getRightTrigger());
		}else if(operatorController.getB()){
			mIntake.runReverseRight(1);
		}else if(operatorController.getXBut()){
			mIntake.runReverseLeft(1);
		}else {
			mIntake.stopClaw();
		}
		
		if(driverController.getA()) {
			mIntakeLift.resetEncoders();
		}
		
		
		if (operatorController.getLB()) {
			mIntake.setExtended();
		} else if(operatorController.getRB()){
			mIntake.setRetracted();
		}

		if (operatorController.getYBut()) {
			mIntake.setNeutral();
		} else {

		}

		if (operatorController.getA()) {
			mToteLift.turnOffSolenoid();
		} else {
			mToteLift.turnOnSolenoid();
		}
		
		//System.out.println(mToteLift.getLiftEncoder());
		
		Compressor c = new Compressor(0);
		//System.out.println(c.getCompressorCurrent());

		
		//System.out.println("DIGITAL: " + io.get());
		System.out.println("RIGHT: " +mIntakeLift.getRightEncoder());
		System.out.println("LEFT: " + mIntakeLift.getLeftEncoder());
		
		//System.out.println("Left Encodre:" + mDrive.getLeftEncoder());
		//System.out.println("Right Encodre:" + mDrive.getRightEncoder());
		//System.out.println("GYRO: " + mDrive.getAngle());
	}
}
