package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeCloseCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeOpenCommand;
import org.usfirst.frc.team3309.robot.commands.intakelift.IntakeLiftRunEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabCanAndMoveAuto extends CommandGroup {
	public GrabCanAndMoveAuto() {
		this.addSequential(new IntakeCloseCommand(-10000));
		this.addSequential(new IntakeLiftRunEncoder(150));
		this.addSequential(new DriveForwardEncoderCountsSlow(-700, 0, -.3));
		this.addSequential(new TurnToAngle(180));
		this.addSequential(new WaitCommand());
	}
}
