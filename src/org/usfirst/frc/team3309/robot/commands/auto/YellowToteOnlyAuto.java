package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftDown;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftEncoder;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftUp;
import org.usfirst.frc.team3309.robot.commands.totelift.UnlatchToteLiftCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteOnlyAuto extends CommandGroup {

	public YellowToteOnlyAuto() {

		//this.addSequential(new DriveForwardEncoderCountsSlow(2500));
		//this.addSequential(new  UnlatchToteLiftCommand());
		//this.addSequential( new MoveToteLiftUp());
		//this.addSequential( new MoveToteLiftDown());
		//this.addSequential(new MoveToteLiftTime(1000, .9, -50));

		// this.addSequential(new IntakeRunTime(2, 0, 5));
		// this.addSequential(new DriveForewardTime());

		// this.addSequential(new MoveToteLiftEncoder(200));

		// this.addSequential(new IntakeRunTime(5, 4, 1));
		// this.addParallel(new MoveToteLiftEncoder(800));
		// this.addSequential(new DriveForwardEncoderCounts(1000));

		// this.addSequential(new MoveToteLiftEncoder(500, -50));

	}
}
