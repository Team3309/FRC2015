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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeLiftCommand extends Command {

	private static double KP_RIGHT_UP = .008;
	private static double KP_LEFT_UP = .01;

	private static double KD_RIGHT_UP = .006;
	private static double KD_LEFT_UP = .000;

	private static double KP_RIGHT_DOWN = .005;
	private static double KP_LEFT_DOWN = .009;

	private static double KD_RIGHT_DOWN = .009;
	private static double KD_LEFT_DOWN = .009;

	private double bothCounter = 0;
	private static IntakeLift mIntakeLift = IntakeLift.getInstance();
	private PIDController controller;

	private double lastErrorR;
	private double lastErrorL;
	private static IntakeLiftCommand instance;

	public static IntakeLiftCommand getInstance() {
		if (instance == null) {
			instance = new IntakeLiftCommand();

		}
		return instance;
	}

	private IntakeLiftCommand() {
		super();
		requires(mIntakeLift);
	}

	@Override
	protected void initialize() {
		lastErrorR = 0;
		lastErrorL = 0;
		SmartDashboard.putNumber("RIGHT KP INTAKE LIFT UP", KP_RIGHT_UP);
		SmartDashboard.putNumber("RIGHT KD INTAKE LIFT UP", KD_RIGHT_UP);

		SmartDashboard.putNumber("RIGHT KP INTAKE LIFT DOWN", KP_RIGHT_DOWN);
		SmartDashboard.putNumber("RIGHT KD INTAKE LIFT DOWN", KD_RIGHT_DOWN);

		SmartDashboard.putNumber("LEFT KP INTAKE LIFT UP", KP_LEFT_UP);
		SmartDashboard.putNumber("LEFT KD INTAKE LIFT UP", KD_LEFT_UP);

		SmartDashboard.putNumber("LEFT KP INTAKE LIFT DOWN", KP_LEFT_DOWN);
		SmartDashboard.putNumber("LEFT KD INTAKE LIFT DOWN", KD_LEFT_DOWN);
	}

	@Override
	protected void execute() {

		// System.out.println("RIGHT CLAW LIFT " +
		// mIntakeLift.getSlaveEncoder());
		// System.out.println("LefT CLAW LIFT " +
		// mIntakeLift.getMasterEncoder());

		double errorL = mIntakeLift.getSetPoint() - mIntakeLift.getLeftEncoder();
		double errorR = mIntakeLift.getSetPoint() - mIntakeLift.getRightEncoder();
		
		double derR = errorR - lastErrorR;
		double derL = errorL - lastErrorL;
		lastErrorR = errorR;
		lastErrorL = errorL;

		double pidRight = 0;
		double pidLeft = 0;
		if (errorR + derR > 0) {
			pidRight = errorR * KP_RIGHT_UP + derR * KD_RIGHT_UP;
			pidLeft = errorL * KP_LEFT_UP + derL * KD_LEFT_UP;
		} else if (errorR + derR < 0) {
			pidRight = errorR * KP_RIGHT_DOWN + derR * KD_RIGHT_DOWN;
			pidLeft = errorL * KP_LEFT_UP + derL * KD_LEFT_UP;
		}

		//System.out.println("KP RIGHT UP: " + KP_RIGHT_UP);
		//System.out.println("HERE IS PID: " + pidRight);

		mIntakeLift.runRightLiftAt(pidRight);
		mIntakeLift.runLeftLiftAt(-pidLeft);

		updateConstants();

	}

	private void updateConstants() {
		KP_RIGHT_UP = SmartDashboard.getNumber("RIGHT KP INTAKE LIFT UP", KP_RIGHT_UP);
		KD_RIGHT_UP = SmartDashboard.getNumber("RIGHT KD INTAKE LIFT UP", KD_RIGHT_UP);

		KP_RIGHT_DOWN = SmartDashboard.getNumber("RIGHT KP INTAKE LIFT DOWN", KP_RIGHT_DOWN);
		KD_RIGHT_DOWN = SmartDashboard.getNumber("RIGHT KD INTAKE LIFT DOWN", KD_RIGHT_DOWN);

		KP_LEFT_UP = SmartDashboard.getNumber("LEFT KP INTAKE LIFT UP", KP_LEFT_UP);
		KD_LEFT_UP = SmartDashboard.getNumber("LEFT KD INTAKE LIFT UP", KD_LEFT_UP);

		KP_LEFT_DOWN = SmartDashboard.getNumber("LEFT KP INTAKE LIFT DOWN", KP_LEFT_DOWN);
		KD_LEFT_DOWN = SmartDashboard.getNumber("LEFT KD INTAKE LIFT DOWN", KD_LEFT_DOWN);
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

	/*
	 * @Override public void pidWrite(double output) {
	 * System.out.println("\nSETTING SLAVE TO: " + output);
	 * mIntakeLift.setSlaveVictor(-output); }
	 * 
	 * @Override public double pidGet() { return mIntakeLift.getMasterEncoder();
	 * } public void disable() { controller.disable(); } public void enable() {
	 * controller.enable(); }
	 */
}
