package org.usfirst.frc.team3309.robot.commands.totelift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DownUpDownToteLiftCommand extends CommandGroup{
	public boolean b = false;
	public DownUpDownToteLiftCommand() {
		this.setInterruptible(true);
		//this.addSequential(new MoveToteLiftDownEncoder(20));
		//this.addSequential(new MoveDownUntillLimit());
		this.addSequential(new MoveToteLiftEncoder(60));
		
		this.addSequential(new MoveToteLiftEncoder(1080));
		
		this.addSequential(new MoveToteLiftEncoder(500));
		this.addSequential(new toteLiftIsDone());
	}
	public boolean isFinished() {
		return b;
		
	}
}
