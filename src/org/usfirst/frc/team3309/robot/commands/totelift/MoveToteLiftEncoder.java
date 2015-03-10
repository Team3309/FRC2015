package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.pid.PID;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftEncoder extends Command{

	int setPoint = 0;
	private ToteLift mToteLift = ToteLift.getInstance();
	double lastError = 0;
	public MoveToteLiftEncoder(int encoder) {
		setPoint = encoder;
	}
	@Override
	protected void initialize() {
		double error = setPoint - mToteLift.getLiftEncoder();
		double pid = PID.runPIDWithError(setPoint - mToteLift.getLiftEncoder(), lastError, .001, .000);
		lastError = error;
	}

	@Override
	protected void execute() {
		//double
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
