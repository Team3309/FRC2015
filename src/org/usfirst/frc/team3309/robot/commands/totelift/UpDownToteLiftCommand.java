package org.usfirst.frc.team3309.robot.commands.totelift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class UpDownToteLiftCommand extends CommandGroup {

	public UpDownToteLiftCommand() {
		this.addSequential(new MoveToteLiftEncoder(1080));
		this.addSequential(new MoveToteLiftDownEncoder(20));
		this.addSequential(new MoveDownUntillLimit());
		this.addSequential(new toteLiftIsDone());
	}
}
