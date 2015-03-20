package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteAuto extends CommandGroup{

	public YellowToteAuto() {
		this.addSequential(new DriveForwardEncoderCounts(1000));
	}
}
