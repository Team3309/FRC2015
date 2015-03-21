package org.usfirst.frc.team3309.robot.commands.intake;

import org.usfirst.frc.team3309.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeOpenCommand extends Command{

	@Override
	protected void initialize() {
		Intake.getInstance().setRetracted();
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
