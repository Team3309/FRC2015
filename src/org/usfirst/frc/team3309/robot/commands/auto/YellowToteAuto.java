package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeCloseCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeOpenCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunCom;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunContinuous;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftEncoder;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftTime;
import org.usfirst.frc.team3309.robot.commands.totelift.UnlatchToteLiftCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteAuto extends CommandGroup {

	public YellowToteAuto() {

		// this.addSequential(new WaitCommand());

		this.addSequential(new UnlatchToteLiftCommand());
		this.addSequential(new IntakeCloseCommand(-500));

		this.addSequential(new IntakeRunCom(1, 1));
		this.addSequential(new IntakeRunTime(1, 1, 1));
		this.addSequential(new IntakeRunContinuous(1, 1, -50));

		this.addParallel(new IntakeRunContinuous(3, 1, 30));
		this.addParallel(new IntakeOpenCommand(600));

		this.addParallel(new IntakeRunContinuous(0, 1, 750));

		this.addParallel(new MoveToteLiftTime(1.3, 1, 800));

		this.addParallel(new IntakeCloseCommand(990));

		this.addParallel(new IntakeRunContinuous(3, 1, 1000));

		this.addParallel(new MoveToteLiftTime(.9, -1, 1450));

		this.addParallel(new IntakeRunContinuous(3, 1, 1700));

		// open to get third tote
		this.addParallel(new IntakeOpenCommand(1800));
		this.addParallel(new MoveToteLiftTime(.8, 1, 2000));

		this.addParallel(new IntakeRunContinuous(0, 1, 2000));
		this.addParallel(new IntakeCloseCommand(2550));
		this.addSequential(new DriveForwardEncoderCountsSlow(2900));
		
		this.addSequential(new TurnToAngle(-10));
		
		this.addParallel(new IntakeCloseCommand(2400));
		
		

		// close, intake, set intake to reverse

		// this.addSequential(new IntakeRunContinuous(3, .7));

	}
}
