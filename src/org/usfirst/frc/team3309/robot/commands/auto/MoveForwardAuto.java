package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveForwardAuto extends CommandGroup {

	public MoveForwardAuto(int counts) {
		this.addSequential(new DriveForwardEncoderCountsSlow(counts, .35));
		this.addSequential(new WaitCommand());
		System.out.println("DFAKGODAG");
	}
}
