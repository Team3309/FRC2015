package org.usfirst.frc.team3309.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WaitAuto extends CommandGroup{

	public WaitAuto() {
		this.addSequential(new WaitCommand());
	}
}
