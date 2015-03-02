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

	private static double KPCONSTANT_UP = .009;
	private static double KDDERIVATIVE_UP = .003;
	private static double KPCONSTANT_DOWN = .0055;
	private static double KDDERIVATIVE_DOWN = .005;

	private double bothCounter = 0;
	private static IntakeLift mIntakeLift = IntakeLift.getInstance();
	private PIDController controller;

	private double lastError;
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
		lastError = 0;
		SmartDashboard.putNumber("KP INTAKE LIFT UP", KPCONSTANT_UP);
		SmartDashboard.putNumber("KD INTAKE LIFT UP", KDDERIVATIVE_UP);

		SmartDashboard.putNumber("KP INTAKE LIFT DOWN", KPCONSTANT_DOWN);
		SmartDashboard.putNumber("KD INTAKE LIFT DOWN", KDDERIVATIVE_DOWN);
	}

	@Override
	protected void execute() {

		// System.out.println("RIGHT CLAW LIFT " +
		// mIntakeLift.getSlaveEncoder());
		// System.out.println("LefT CLAW LIFT " +
		// mIntakeLift.getMasterEncoder());

		double error = mIntakeLift.getSetPoint() - mIntakeLift.getLeftEncoder();

		double der = error - lastError;
		lastError = error;

		double pid = 0;
		if (error + der > 0) {
			pid = error * KPCONSTANT_UP + der * KDDERIVATIVE_UP;
		} else if (error + der < 0) {
			pid = error * KPCONSTANT_DOWN + der * KDDERIVATIVE_DOWN;
		}

		System.out.println("HERE IS PID: " + pid);

		mIntakeLift.runRightLiftAt(pid);
		mIntakeLift.runLeftLiftAt(-pid);

		updateConstants();

	}

	private void updateConstants() {
		KPCONSTANT_UP = SmartDashboard.getNumber("KP INTAKE LIFT UP");
		KDDERIVATIVE_UP = SmartDashboard.getNumber("KD INTAKE LIFT UP");

		KPCONSTANT_DOWN = SmartDashboard.getNumber("KP INTAKE LIFT DOWN");
		KDDERIVATIVE_DOWN = SmartDashboard.getNumber("KD INTAKE LIFT DOWN");
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
