package org.usfirst.frc.team3309.robot.commands.intakelift;

import org.usfirst.frc.team3309.robot.subsystems.IntakeLift;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeLiftCommand extends Command implements PIDSource, PIDOutput{

	private PIDController controller; 
	private double KPCONSTANT = .01;
	private IntakeLift mIntakeLift = IntakeLift.getInstance();
	public IntakeLiftCommand() {
		super();
	}
	
	@Override
	protected void initialize() {
		controller = new PIDController(KPCONSTANT, 0 , 0, this, this);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return mIntakeLift.;
	}
	
	
}
