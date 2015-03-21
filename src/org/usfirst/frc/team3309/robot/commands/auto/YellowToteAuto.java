package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.DriveForwardEncoderCounts;
import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeCloseCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeOpenCommand;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunContinuous;
import org.usfirst.frc.team3309.robot.commands.intake.IntakeRunTime;
import org.usfirst.frc.team3309.robot.commands.totelift.MoveToteLiftEncoder;
import org.usfirst.frc.team3309.robot.commands.totelift.UnlatchToteLiftCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class YellowToteAuto extends CommandGroup{

	public YellowToteAuto() {
		
		this.addSequential(new DriveForwardEncoderCounts(1000));
		
		
		//close, intake, set intake to reverse
		this.addSequential(new IntakeCloseCommand());
		this.addSequential(new IntakeRunTime(.7, 0, .7));
		this.addSequential(new IntakeRunContinuous(3, .7));
		
		//drive forward while intaking reverse to knock can
		this.addSequential(new DriveForwardEncoderCounts(400));
		
		
		//open and start running in
		this.addSequential(new IntakeOpenCommand());
		this.addSequential(new IntakeRunContinuous(0, .7));
		
		//go forward until around second tote, move the lift up while doing this
		this.addParallel(new MoveToteLiftEncoder(1000));
		this.addSequential(new DriveForwardEncoderCounts(500));
		
		//close around tote
		this.addParallel(new MoveToteLiftEncoder(0));
		this.addSequential(new IntakeCloseCommand());
		
		//move back then set to reverse go forward to knock can
		this.addSequential(new DriveForwardEncoderCounts(-500));
		this.addSequential(new IntakeRunContinuous(3, .7));
		
		//hit the can with reverse
		this.addParallel(new MoveToteLiftEncoder(1000));
		this.addSequential(new DriveForwardEncoderCounts(700));
		
		//open and start intaking
		this.addSequential(new IntakeOpenCommand());
		this.addSequential(new IntakeRunContinuous(0, .7));
		
		//go to third tote
		this.addParallel(new MoveToteLiftEncoder(0));
		this.addSequential(new DriveForwardEncoderCounts(500));
		
		//close around third tote
		this.addSequential(new IntakeCloseCommand());
		
		//turn to 90
		this.addSequential(new TurnToAngle(90));
		
		//bring up while going to auto zone
		this.addParallel(new MoveToteLiftEncoder(1050));
		this.addSequential(new DriveForwardEncoderCounts(1000));
		
		//unlatch and bring down
		this.addSequential(new UnlatchToteLiftCommand());
		this.addSequential(new MoveToteLiftEncoder(0));
		
		//open and backup
		this.addSequential(new IntakeOpenCommand());
		this.addSequential(new DriveForwardEncoderCounts(-500));
	}
}
