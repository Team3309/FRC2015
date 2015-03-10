package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteAuto extends CommandGroup{

	public YellowToteAuto() {
		this.addSequential(new DriveForwardEncoderCounts(1000));
		this.addSequential(new IntakeRunTime(2000, 0, 5));
		//this.addSequential(new MoveToteLift(500));
	}
}
