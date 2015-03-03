package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveForwardAuto extends CommandGroup{

	private int counts; 
	public MoveForwardAuto(int counts) { 
		this.addSequential(new DriveForwardEncoderCounts(counts));
	}
}
