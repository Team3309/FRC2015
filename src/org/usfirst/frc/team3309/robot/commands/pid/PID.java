package org.usfirst.frc.team3309.robot.commands.pid;

public class PID {

	public static double runPIDWithError(double error, double lastError, double kP, double kD) {

		double der = error - lastError;

		double pid = (error * kP) + (der * kD);

		return pid;
	}
}
