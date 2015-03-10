package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveForwardAuto extends CommandGroup{

	public MoveForwardAuto(int counts) { 
		this.addSequential(new DriveForwardEncoderCounts(counts));
		Drive.getInstance().stopDrive();
		System.out.println("DFAKGODAG");
	}
}
