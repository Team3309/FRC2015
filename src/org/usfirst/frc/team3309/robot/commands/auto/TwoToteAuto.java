package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardTime;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoToteAuto extends CommandGroup {

	public TwoToteAuto() {
		this.addSequential(new IntakeRunTime(.5, 0, 1));
		this.addSequential(new DriveForwardTime(2, .35));
		this.addSequential(new TurnToAngle(-30), 3);
		this.addSequential(new DriveForwardTime(.7, .3));
		// wait .5
		this.addSequential(new DriveForwardTime(.5, .1));

		this.addSequential(new MoveToteLiftEncoder(920), 4);

		this.addSequential(new DriveForwardTime(.7, -.3));
		this.addSequential(new TurnToAngle(00), 3);
		this.addSequential(new DriveForwardTime(1, -.3));

		this.addSequential(new TurnToAngle(45), 5);
		this.addSequential(new DriveForwardTime(3, -.3));
		this.addSequential(new MoveToteLiftEncoder(0), 4);
	}
}
