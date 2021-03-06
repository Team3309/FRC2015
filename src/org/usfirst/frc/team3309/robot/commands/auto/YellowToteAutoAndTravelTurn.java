package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeCloseCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeOpenCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunCom;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunContinuous;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftDown;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftUp;
import org.usfirst.frc.team3309.robot.commands.totelift.UnlatchToteLiftCommand;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteAutoAndTravelTurn extends CommandGroup {
	public YellowToteAutoAndTravelTurn() {

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

		this.addSequential(new DriveForwardEncoderCountsSlow(AutoConstants.THREE_TOTE_DISTANCE, Drive.getInstance().getAngle(), .3));

		this.addSequential(new TurnToAngle(65));

				this.addParallel( new MoveToteLiftDown(700));
				
				this.addParallel(new IntakeRunContinuous(0,1,600));
				this.addParallel(new IntakeOpenCommand(600));
				this.addSequential(new DriveForwardEncoderCountsSlow(1800, 65, .7));

				this.addSequential(new IntakeRunTime(.5, 1, 1));
				
				this.addSequential(new TurnToAngle(0));
				
				
				this.addParallel(new IntakeOpenCommand(-100000));
				this.addSequential(new DriveForwardEncoderCountsSlow(-1000, 0, -.7));
				//this.addParallel(new IntakeRunContinuous(1, 1, -10000));
				//this.addSequential(new DriveForwardEncoderCountsSlow(-1000, 65, -.5));
				

				//this.addParallel(new IntakeCloseCommand(2400));
				this.addSequential(new WaitCommand());


	}
}
