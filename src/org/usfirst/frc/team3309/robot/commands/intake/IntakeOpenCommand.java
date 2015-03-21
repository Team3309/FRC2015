package org.usfirst.frc.team3309.robot.commands.intake;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeOpenCommand extends Command {

	private int startCount = 0;

	public IntakeOpenCommand(int startCount) {
		this.startCount = startCount;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		System.out.println("FSDG " + Drive.getInstance().getAverageCount());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (Drive.getInstance().getAverageCount() > startCount) {
			Intake.getInstance().setExtended();
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	public void run() {
		Intake.getInstance().setExtended();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}
}
