package org.usfirst.team3309.commands.pid;

import edu.wpi.first.wpilibj.command.Command;

public class PIDLoopCommand  extends Command{

	private boolean isFinished = false;
	private double kP;
	private double kI;
	private double kD;
	
	public PIDLoopCommand(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isFinished;
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
