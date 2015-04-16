package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeCloseCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeOpenCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunContinuous;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftDown;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftDownEncoder;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftUp;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeTotePIDAuto extends CommandGroup {

	private boolean done = false;
	private static ThreeTotePIDAuto instance;

	public static ThreeTotePIDAuto getInstance() {
		if (instance == null) {
			instance = new ThreeTotePIDAuto();
		}
		return instance;
	}

	private ThreeTotePIDAuto() {
		this.setInterruptible(true);
		this.addSequential(new TurnToAngle(47));

		// intake, power, ??
		this.addParallel(new IntakeRunContinuous(0, 1, 0));

		// encoder, angle, power
		this.addSequential(new DriveForwardEncoderCountsSlow(630, 47, .3), 2);
		// this.addSequential(new IntakeCloseCommand(570));
		// starting value
		this.addSequential(new IntakeCloseCommand(-2000));
		this.addSequential(new IntakeRunTime(.5, 0, 1));
		this.addSequential(new IntakeRunContinuous(0, 1, -2000));
		this.addSequential(new DriveForwardEncoderCountsSlow(-375, 47, -.3));

		// this.addSequential(new IntakeOpenCommand(-2000));
		this.addSequential(new IntakeRunTime(.5, 0, 1));
		this.addSequential(new IntakeRunContinuous(0, 1, -2000));
		this.addSequential(new TurnToAngle(33));
		// this.addSequentiaaAaal(new IntakeCloseCommand(-2000));

		this.addSequential(new IntakeCloseCommand(-2000));

		this.addSequential(new IntakeOpenCommand(-2000));
		// start encoder, waitForSENSOR?
		this.addParallel(new MoveToteLiftUp(10, true));
		this.addSequential(new DriveForwardEncoderCountsSlow(760, 33, .3));
		this.addSequential(new IntakeCloseCommand(-2000));
		this.addSequential(new IntakeRunTime(.5, 0, 1));
		this.addSequential(new MoveToteLiftDownEncoder(37));
		this.addSequential(new IntakeRunContinuous(0, 1, -2000));

		this.addSequential(new DriveForwardEncoderCountsSlow(-375, 33, -.3), 2);

		this.addSequential(new TurnToAngle(43));

		this.addSequential(new IntakeOpenCommand(-4000));
		this.addParallel(new MoveToteLiftUp(10, true));
		this.addSequential(new DriveForwardEncoderCountsSlow(590, 40, .3), 2.5);
		this.addSequential(new IntakeCloseCommand(-4000));
		this.addSequential(new DriveForwardEncoderCountsSlow(10, 40, .3), 2);

		this.addSequential(new IntakeRunTime(.3, 0, 1));
		this.addSequential(new IntakeRunContinuous(0, 1, -2000));

		this.addSequential(new DriveForwardEncoderCountsSlow(-200, 40, -.3));
		// this.addSequential(new MoveToteLiftDownEncoder(3));

		// this.addParallel(new MoveToteLiftUp(100, true));
		// this.addSequential(new DriveForwardEncoderCountsSlow(760, 40, .3));
		this.addSequential(new WaitCommand());
	}

	public boolean isFinished() {
		return done;
	}

	public void setDone(boolean b) {
		done = b;
	}
}

/*
 * this.addSequential(new TurnToAngle(35));
 * 
 * // intake, power, ?? this.addParallel(new IntakeRunContinuous(0, 1, 0));
 * 
 * // encoder, angle, power this.addSequential(new
 * DriveForwardEncoderCountsSlow(525, 35, .3), 2);
 * 
 * // starting value this.addSequential(new IntakeCloseCommand(-2000));
 * 
 * this.addSequential(new DriveForwardEncoderCountsSlow(-500, 35, -.3));
 * 
 * this.addSequential(new IntakeOpenCommand(-2000)); this.addSequential(new
 * IntakeRunTime(.5, 0, 1)); this.addSequential(new IntakeRunContinuous(0, 1,
 * -2000)); this.addSequential(new TurnToAngle(12)); this.addSequential(new
 * IntakeCloseCommand(-2000));
 * 
 * this.addSequential(new IntakeCloseCommand(-2000)); // start encoder,
 * waitForSENSOR? this.addParallel(new MoveToteLiftUp(400, true));
 * this.addSequential(new DriveForwardEncoderCountsSlow(750, 12, .3));
 * this.addSequential(new IntakeRunTime(1.5, 0, 1)); this.addSequential(new
 * WaitCommand());
 */
