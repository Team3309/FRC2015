package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowRobonautsAuto extends CommandGroup {

	public YellowRobonautsAuto() {
		this.addSequential(new DriveForwardEncoderCounts(300), 1);
		this.addParallel(new IntakeRunTime(1, 0, 1), 1);

		this.addSequential(new DriveForwardEncoderCounts(-300));
		this.addParallel(new IntakeRunTime(1, 0, 1), 1);

	}

}
