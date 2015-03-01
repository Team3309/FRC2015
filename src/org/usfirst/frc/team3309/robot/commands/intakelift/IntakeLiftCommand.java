/*
 * This Command runs continously and its only function is to keep the slave encoder value the same as the master encoder's value
 */
package org.usfirst.frc.team3309.robot.commands.intakelift;

import org.usfirst.frc.team3309.robot.commands.pid.PIDLoopCommand;
import org.usfirst.frc.team3309.robot.subsystems.IntakeLift;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeLiftCommand extends PIDLoopCommand {
 
	private static double KPCONSTANT = .01;
	private static IntakeLift mIntakeLift = IntakeLift.getInstance();
	
	private static IntakeLiftCommand instance;
	public static IntakeLiftCommand getInstance() {
		if(instance == null) {
			instance = new IntakeLiftCommand();
		
		}
		return instance;
	}
	private IntakeLiftCommand() {
		super(KPCONSTANT, 0, 0, mIntakeLift.getMasterEncoder());
		requires(mIntakeLift);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
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

	@Override
	public void pidWrite(double output) {
		mIntakeLift.setSlaveVictor(output);
	}

	@Override
	public double pidGet() {
		return mIntakeLift.getMasterEncoder();
	}
}
