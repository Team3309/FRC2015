package org.usfirst.frc.team3309.robot.commands.intake;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCloseCommand extends Command{

	private int startCount = 0;
	public IntakeCloseCommand(int startCount) {
		this.startCount = startCount;
	}
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
		System.out.println("FSDG " + Drive.getInstance().getAverageCount());
		if (Drive.getInstance().getAverageCount() > startCount) {
			Intake.getInstance().setRetracted();
			return true;
		}
		return false;
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
