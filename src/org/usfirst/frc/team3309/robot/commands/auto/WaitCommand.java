package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command {

	Timer timer = new Timer();
	double time;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		time = 15 - DriverStation.getInstance().getMatchTime();
		System.out.println("TIME: " + time);
		timer.start();
	}

	@Override
	protected void execute() {
		Drive.getInstance().stopDrive();
		Intake.getInstance().stopClaw();

	}

	@Override
	protected boolean isFinished() {
		return !DriverStation.getInstance().isAutonomous();
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
