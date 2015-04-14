package org.usfirst.frc.team3309.robot.commands.totelift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DownUpDownToteLiftCommand extends CommandGroup{
	public DownUpDownToteLiftCommand() {
		//this.addSequential(new MoveToteLiftDownEncoder(20));
		//this.addSequential(new MoveDownUntillLimit());
		this.addSequential(new MoveToteLiftEncoder(20));
		
		
		this.addSequential(new MoveToteLiftEncoder(1080));
		
		this.addSequential(new MoveToteLiftEncoder(500));
		this.addSequential(new toteLiftIsDone());
	}
}
