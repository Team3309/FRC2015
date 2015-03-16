package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveForewardTimeAuto extends CommandGroup {

	public MoveForewardTimeAuto(double time, double power) {
		this.addSequential(new DriveForwardTime(time, power));
		this.addSequential(new WaitCommand());
	}
}
