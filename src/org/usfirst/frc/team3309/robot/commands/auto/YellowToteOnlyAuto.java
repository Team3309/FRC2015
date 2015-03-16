package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteOnlyAuto extends CommandGroup{

	public YellowToteOnlyAuto() {
		
		this.addSequential(new IntakeRunTime(2, 0, 5));
		//this.addSequential(new DriveForewardTime());
		
		//this.addSequential(new MoveToteLiftEncoder(200));
		
		//this.addSequential(new IntakeRunTime(5, 4, 1));
		//this.addParallel(new DriveForwardEncoderCounts(1000));
		//this.addSequential(new MoveToteLift(500));
	}
}
