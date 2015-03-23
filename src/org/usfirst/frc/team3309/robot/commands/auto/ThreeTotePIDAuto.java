package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunContinuous;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftUp;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeTotePIDAuto extends CommandGroup{

	public ThreeTotePIDAuto() {
		
		this.addParallel(new IntakeRunContinuous(0, 1, -1));
		this.addSequential(new DriveForwardEncoderCountsSlow(200, Drive.getInstance().getAngle(), .4));
		
		this.addSequential(new TurnToAngle(-10));
		
		this.addSequential(new DriveForwardEncoderCountsSlow(-200, Drive.getInstance().getAngle(), -.4));
		
		//ram into second tote while lifting elevator
		this.addParallel(new MoveToteLiftUp(200));
		this.addSequential(new DriveForwardEncoderCountsSlow(400, Drive.getInstance().getAngle(), .4));
		
		this.addSequential(new TurnToAngle(-25));
		
		this.addSequential(new DriveForwardEncoderCountsSlow(-400, Drive.getInstance().getAngle(), -.4));
		
		this.addSequential(new DriveForwardEncoderCountsSlow(600, Drive.getInstance().getAngle(), .4));
	}
}
