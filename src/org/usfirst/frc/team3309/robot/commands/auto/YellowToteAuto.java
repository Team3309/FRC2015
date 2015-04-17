package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeCloseCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeOpenCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunCom;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunContinuous;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftDown;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftEncoder;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftUp;
import org.usfirst.frc.team3309.robot.commands.totelift.UnlatchToteLiftCommand;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteAuto extends CommandGroup {

	public YellowToteAuto() {

		// this.addSequential(new WaitCommand());

		this.addSequential(new UnlatchToteLiftCommand());
		this.addSequential(new IntakeRunCom(1, .5));
		this.addSequential(new IntakeRunContinuous(1, .5, -50));

		this.addSequential(new IntakeCloseCommand(-500));
		this.addSequential(new IntakeRunTime(1, 1, .7));

		this.addSequential(new IntakeRunContinuous(1, 1, -50));

		this.addParallel(new IntakeRunContinuous(3, 1, 70));
		this.addParallel(new IntakeOpenCommand(400));

		this.addParallel(new MoveToteLiftUp(600));
		this.addParallel(new IntakeRunContinuous(0, 1, 750));

		this.addParallel(new IntakeCloseCommand(990));

		this.addParallel(new IntakeRunContinuous(3, 1, 1400));

		this.addParallel(new MoveToteLiftDown(1600));

		this.addParallel(new IntakeRunContinuous(3, 1, 1700));

		// open to get third tote, just changed this KRAGER
		this.addParallel(new IntakeOpenCommand(1850));
		this.addParallel(new MoveToteLiftUp(2250));

		this.addParallel(new IntakeRunContinuous(0, 1, 2300));
		this.addParallel(new IntakeCloseCommand(2550));
		this.addParallel(new IntakeRunContinuous(1, 1, 2700));
		this.addParallel(new IntakeRunContinuous(0, 1, 2769));

		this.addSequential(new DriveForwardEncoderCountsSlow(2800, Drive.getInstance().getAngle(), .3));

		this.addSequential(new TurnToAngle(65));

		this.addParallel(new MoveToteLiftDown(700));
		this.addParallel(new IntakeOpenCommand(550));
		this.addSequential(new DriveForwardEncoderCountsSlow(1700, 65, .9));

		this.addSequential(new IntakeRunTime(.2, 1, .4));

		this.addParallel(new IntakeRunContinuous(1, 1, -10000));
		this.addSequential(new DriveForwardEncoderCountsSlow(-600, 65, -.7));

		// this.addParallel(new IntakeCloseCommand(2400));
		this.addSequential(new WaitCommand());

	}

}

/*
 * 
 * NORMAL this.addSequential(new UnlatchToteLiftCommand());
 * this.addSequential(new IntakeRunCom(1, .5)); this.addSequential(new
 * IntakeRunContinuous(1, .5, -50));
 * 
 * this.addSequential(new IntakeCloseCommand(-500)); this.addSequential(new
 * IntakeRunTime(1, 1, .7));
 * 
 * this.addSequential(new IntakeRunContinuous(1, 1, -50));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 50)); this.addParallel(new
 * IntakeOpenCommand(284));
 * 
 * this.addParallel(new IntakeRunContinuous(0, 1, 533));
 * 
 * this.addParallel(new MoveToteLiftUp(560));
 * 
 * this.addParallel(new IntakeCloseCommand(704));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 996));
 * 
 * this.addParallel(new MoveToteLiftDown(1138));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 1209));
 * 
 * // open to get third tote this.addParallel(new IntakeOpenCommand(1422));
 * this.addParallel(new MoveToteLiftUp(1600));
 * 
 * this.addParallel(new IntakeRunContinuous(0, 1, 1636)); this.addParallel(new
 * IntakeCloseCommand(1813));
 * 
 * 
 * this.addSequential(new DriveForwardEncoderCountsSlow(2062,
 * Drive.getInstance().getAngle(), .27));
 * 
 * this.addSequential(new TurnToAngle(70));
 * 
 * this.addParallel( new MoveToteLiftDown(356)); this.addParallel(new
 * IntakeOpenCommand(391)); this.addSequential(new
 * DriveForwardEncoderCountsSlow(1600, 65, .7));
 * 
 * this.addParallel(new IntakeRunContinuous(1, 1, -10000));
 * this.addSequential(new DriveForwardEncoderCountsSlow(-711, 65, -.8));
 * 
 * 
 * 
 * //this.addParallel(new IntakeCloseCommand(2400)); this.addSequential(new
 * WaitCommand());
 */
/*
 * 
 * this.addSequential(new UnlatchToteLiftCommand()); this.addSequential(new
 * IntakeRunCom(1, .5)); this.addSequential(new IntakeRunContinuous(1, .5,
 * -50));
 * 
 * this.addSequential(new IntakeCloseCommand(-500)); this.addSequential(new
 * IntakeRunTime(1, 1, .7));
 * 
 * this.addSequential(new IntakeRunContinuous(1, 1, -50));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 70)); this.addParallel(new
 * IntakeOpenCommand(400));
 * 
 * this.addParallel(new IntakeRunContinuous(0, 1, 750));
 * 
 * this.addParallel(new MoveToteLiftUp(850));
 * 
 * this.addParallel(new IntakeCloseCommand(990));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 1400));
 * 
 * this.addParallel(new MoveToteLiftDown(1600));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 1700));
 * 
 * // open to get third tote this.addParallel(new IntakeOpenCommand(2000));
 * this.addParallel(new MoveToteLiftUp(2250));
 * 
 * this.addParallel(new IntakeRunContinuous(0, 1, 2300)); this.addParallel(new
 * IntakeCloseCommand(2550));
 * 
 * 
 * this.addSequential(new DriveForwardEncoderCountsSlow(2900,
 * Drive.getInstance().getAngle(), .3));
 * 
 * this.addSequential(new TurnToAngle(65));
 * 
 * this.addParallel( new MoveToteLiftDown(500)); this.addParallel(new
 * IntakeOpenCommand(550)); this.addSequential(new
 * DriveForwardEncoderCountsSlow(2300, 65, .7));
 * 
 * this.addParallel(new IntakeRunContinuous(1, 1, -10000));
 * this.addSequential(new DriveForwardEncoderCountsSlow(-1000, 65, -.8));
 * 
 * 
 * 
 * //this.addParallel(new IntakeCloseCommand(2400)); this.addSequential(new
 * WaitCommand());
 */

/*
 * VEGAS/ARIZONA AUTO //this.addSequential(new WaitCommand());
 * 
 * this.addSequential(new UnlatchToteLiftCommand()); this.addSequential(new
 * IntakeRunCom(1, .5)); this.addSequential(new IntakeRunContinuous(1, .5,
 * -50));
 * 
 * this.addSequential(new IntakeCloseCommand(-500)); this.addSequential(new
 * IntakeRunTime(1, 1, .7));
 * 
 * this.addSequential(new IntakeRunContinuous(1, 1, -50));
 * 
 * this.addParallel(new IntakeRunContinuous(2, 1, 70)); this.addParallel(new
 * IntakeOpenCommand(400));
 * 
 * this.addParallel(new MoveToteLiftUp(600)); this.addParallel(new
 * IntakeRunContinuous(0, 1, 750));
 * 
 * 
 * 
 * this.addParallel(new IntakeCloseCommand(990));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 1400));
 * 
 * this.addParallel(new MoveToteLiftDown(1600));
 * 
 * this.addParallel(new IntakeRunContinuous(3, 1, 1700));
 * 
 * // open to get third tote, just changed this KRAGER this.addParallel(new
 * IntakeOpenCommand(1850)); this.addParallel(new MoveToteLiftUp(2250));
 * 
 * this.addParallel(new IntakeRunContinuous(0, 1, 2300)); this.addParallel(new
 * IntakeCloseCommand(2550));
 * 
 * 
 * this.addSequential(new DriveForwardEncoderCountsSlow(2900,
 * Drive.getInstance().getAngle(), .3));
 * 
 * this.addSequential(new TurnToAngle(65));
 * 
 * this.addParallel( new MoveToteLiftDown(500)); this.addParallel(new
 * IntakeOpenCommand(550)); this.addSequential(new
 * DriveForwardEncoderCountsSlow(2100, 65, .7));
 * 
 * this.addParallel(new IntakeRunContinuous(1, 1, -10000));
 * this.addSequential(new DriveForwardEncoderCountsSlow(-1000, 65, -.5));
 * 
 * 
 * 
 * //this.addParallel(new IntakeCloseCommand(2400)); this.addSequential(new
 * WaitCommand());
 */