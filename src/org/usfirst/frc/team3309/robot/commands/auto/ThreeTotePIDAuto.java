package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCountsSlow;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunContinuous;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftDown;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftUp;
import org.usfirst.frc.team3309.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeTotePIDAuto extends CommandGroup{

	public ThreeTotePIDAuto() {
		
		//this.addParallel(new IntakeLiftRunEncoder(100));
		this.addParallel(new IntakeRunContinuous(0, 1, -1));
		this.addSequential(new DriveForwardEncoderCountsSlow(300, 0, .25), 2);
		
		//this.addSequential(new IntakeRunTime(.5, 0, .7));
		
		this.addParallel(new IntakeRunContinuous(0, 1, -4000));
		this.addSequential(new TurnToAngle(30));
		
		this.addSequential(new IntakeRunTime(1, 0, 1));
		
		this.addParallel(new IntakeRunContinuous(0, 1, -4000));
		//this.addSequential(new DriveForwardEncoderCountsSlow(-100, -.4));
		
		this.addSequential(new TurnToAngle(18));
		//ram into second tote while lifting elevator
		this.addParallel(new MoveToteLiftUp(50));
		this.addSequential(new DriveForwardEncoderCountsSlow(300, 18, .4));
		
		this.addSequential(new IntakeRunTime(1, 0, 1));
		
		this.addParallel(new MoveToteLiftDown(40));
		this.addSequential(new DriveForwardEncoderCountsSlow(-100, 18, -.4));
		
		this.addSequential(new TurnToAngle(16));
		/*
		this.addSequential(new TurnToAngle(-25));
		
		this.addSequential(new DriveForwardEncoderCountsSlow(-400, Drive.getInstance().getAngle(), -.4));
		
		this.addSequential(new DriveForwardEncoderCountsSlow(600, Drive.getInstance().getAngle(), .4));
	*/
		this.addSequential(new WaitCommand());
	}
}
