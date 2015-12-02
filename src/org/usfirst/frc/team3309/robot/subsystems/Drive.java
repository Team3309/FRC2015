package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.driverstation.Controllers;
import org.usfirst.frc.team3309.robot.ModifiedGyro;
import org.usfirst.frc.team3309.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")
public class Drive {
	private boolean isPrintingDriveInfo = false;

	// test

	// all of the sensors and motor controllers

	// private Victor[] leftVictors = new Victor[2];
	// private Victor[] rightVictors = new Victor[2];
	private Victor strafeVictor1;

	private Talon[] leftVictors = new Talon[2];
	private Talon[] rightVictors = new Talon[2];
	// private Talon strafeVictor1;

	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private ModifiedGyro gyro;

	// enable this is you ever want to go just forward
	private boolean straightPidEnabled = false;
	private boolean straightAngleSet = false;

	// This method takes when the equation returns above the max or below the
	// min and fixes it so it does not. Uses some math and gets the job done
	private double skimGain = .25;

	// change this to change threshold
	private final double THRESHOLD = .3;
	// tells if gyro is a yay or nay
	private boolean gyroEnabled = true;
	// the max angular velocity (duh)
	private int MAX_ANGULAR_VELOCITY = 1500;

	// Now for all the possible kp constants
	private double KP_NORMAL = .008;
	private double pid_Kp_NoThrottle_Right = 0.049;
	private double pid_Kp_NoThrottle_Left = 0.04;
	private double pid_Kp_Throttle_Right = 0.02;
	private double pid_Kp_Throttle_Left = 0.02;

	private static Drive instance;

	private PIDController straightPID = null;

	private double throttle = 0;

	private int strafeCounter = 0;
	double pidRequestedValue;
	boolean aimAngleIsSet = false;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();

		}
		return instance;
	}

	// the constructor
	private Drive() {
		// initialize Victors in their arrays

		// leftVictors[0] = new Victor(RobotMap.DRIVE_LEFT_0);
		// leftVictors[1] = new Victor(RobotMap.DRIVE_LEFT_1);
		// rightVictors[0] = new Victor(RobotMap.DRIVE_RIGHT_0);
		// rightVictors[1] = new Victor(RobotMap.DRIVE_RIGHT_1);
		strafeVictor1 = new Victor(RobotMap.DRIVE_STRAFE_1);

		leftVictors[0] = new Talon(RobotMap.DRIVE_LEFT_0);
		leftVictors[1] = new Talon(RobotMap.DRIVE_LEFT_1);
		rightVictors[0] = new Talon(RobotMap.DRIVE_RIGHT_0);
		rightVictors[1] = new Talon(RobotMap.DRIVE_RIGHT_1);
		// strafeVictor1 = new Talon(RobotMap.DRIVE_STRAFE_1);

		// initialize Encoders
		leftEncoder = new Encoder(RobotMap.DRIVE_ENCODER_LEFT_A, RobotMap.DRIVE_ENCODER_LEFT_B, false, CounterBase.EncodingType.k1X);
		rightEncoder = new Encoder(RobotMap.DRIVE_ENCODER_RIGHT_A, RobotMap.DRIVE_ENCODER_RIGHT_B, true, CounterBase.EncodingType.k1X);

		// initialize gyro
		gyro = new ModifiedGyro(RobotMap.DRIVE_GYRO);

		// pid To be used Later
		/*
		 * StraightPID straight = new StraightPID(); straightPID = new
		 * PIDController(.001, 0, .02, straight, straight);
		 * straightPID.disable();
		 */

		SmartDashboard.putNumber("KP_DRIVE_CONSTANT", KP_NORMAL);
		SmartDashboard.putNumber("No_Throttle_Left", pid_Kp_NoThrottle_Left);
		SmartDashboard.putNumber("No_Throttle_Right", pid_Kp_NoThrottle_Right);
		SmartDashboard.putNumber("Throttle_Left", pid_Kp_Throttle_Right);
		SmartDashboard.putNumber("Throttle_Right", pid_Kp_Throttle_Left);
		SmartDashboard.putNumber("Max_Velcoity", this.MAX_ANGULAR_VELOCITY);

	}

	public void resetGyro() {
		gyro.reset();
	}

	public double getAngle() {
		return gyro.getAngle();
	}

	private void driveHalo(double throttle, double turn, double strafe) {

		updateConstants();
		double modifiedTurn;
		double gyroKP = KP_NORMAL;
		this.throttle = throttle;

		// Thresholds
		if (Math.abs(throttle) < THRESHOLD) {
			throttle = 0;
		}
		if (Math.abs(strafe) < THRESHOLD) {
			strafe = 0;
		}
		if (Math.abs(turn) < THRESHOLD) {
			turn = 0;
		}

		// If the user is strafing
		if (strafe != 0) {
			double pidSensorCurrentValue;
			double pidError;
			double pidDrive;

			// If the angle needs to be set, or the counter is not done
			// Strafe counter delays some time between choosing the angle and
			// beginning to correct
			if (!aimAngleIsSet || strafeCounter <= 5) {
				// set angle
				if (!aimAngleIsSet) {
					pidRequestedValue = gyro.getAngle();
				}
				strafeCounter++;
				aimAngleIsSet = true;
			} else {

				// PID Loop for Straight Strafing

				// Read the sensor value
				pidSensorCurrentValue = gyro.getAngle();

				// calculate error
				pidError = pidSensorCurrentValue - pidRequestedValue;

				if (turn != 0) { // if turning and strafing, disable the PID and
									// just set it through normal means
					setLeft(turn);
					setRight(-turn);
					aimAngleIsSet = false;
				} else if (throttle == 0) { // If the user is strafing and not
											// moving forward at the same time
					double pid_Kp_NoThrottle;
					if (strafe > 0) // if the strafe is moving right
						pid_Kp_NoThrottle = pid_Kp_NoThrottle_Right;
					else
						// if the strafe is moving left
						pid_Kp_NoThrottle = pid_Kp_NoThrottle_Left;
					// calculate drive
					pidDrive = ((pid_Kp_NoThrottle * pidError));

					setLeft(-pidDrive);
					setRight(pidDrive);
				} else { // else the user is driving with strafe and throttle
					double pid_Kp_Throttle;
					if (strafe > 0) // if the strafe is moving right
						pid_Kp_Throttle = pid_Kp_Throttle_Right;
					else
						// if the strafe is moving left
						pid_Kp_Throttle = pid_Kp_Throttle_Left;
					// calculate drive
					pidDrive = ((pid_Kp_Throttle * pidError));

					// Gets Power of each side
					double leftPower = (throttle - pidDrive);
					double rightPower = (throttle + pidDrive);
					setLeft(leftPower);
					setRight(rightPower);
				}
				setStrafe(strafe);

			}
		} else { // use default tank drive by default, no strafe, no throttle
			strafeCounter = 0;
			// The simple drive equation
			if (gyroEnabled) {
				double currentAngularRateOfChange = gyro.getAngularRateOfChange();
				double desiredAngularRateOfChange = turn * MAX_ANGULAR_VELOCITY;
				// Change back if it doesn't work
				modifiedTurn = (currentAngularRateOfChange - desiredAngularRateOfChange) * gyroKP;
				if (isPrintingDriveInfo) {
					System.out.println("turn: " + turn + " throttle: " + throttle);
					System.out.println("Current: " + currentAngularRateOfChange + " Desired: " + desiredAngularRateOfChange);
					System.out.println("Error: " + (currentAngularRateOfChange - desiredAngularRateOfChange) + "modified value: " + modifiedTurn);
				}
			} else {
				System.out.println("NO GYRO");
				modifiedTurn = turn;
			}

			double t_left = throttle - modifiedTurn;
			double t_right = throttle + modifiedTurn;

			if (isPrintingDriveInfo) {
				System.out.println(t_left + " t_Left");
				System.out.println(t_right + " t_Right");
			}

			double left = t_left + skim(t_right);
			double right = t_right + skim(t_left);

			if (isPrintingDriveInfo) {
				System.out.println(left + " Left");
				System.out.println(right + " Right");
			}
			aimAngleIsSet = false;
			setLeft(left);
			setRight(right);
			setStrafe(strafe);
		}

	}
	
	 public double handleDeadband(double val, double deadband) {
	        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	    }

    double oldWheel, quickStopAccumulator;
    private double throttleDeadband = 0.02;
    private double wheelDeadband = 0.02;
    
    public static double limit(double v, double limit) {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }
	public void cheesyDrive(double throttle, double wheel, boolean isQuickTurn, boolean isHighGear) {


		double wheelNonLinearity;

		wheel = handleDeadband(wheel, wheelDeadband);
		throttle = handleDeadband(throttle, throttleDeadband);

		double negInertia = wheel - oldWheel;
		oldWheel = wheel;

		if (isHighGear) {
			wheelNonLinearity = 0.6;
			// Apply a sin function that's scaled to make it feel better.
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		} else {
			wheelNonLinearity = 0.5;
			// Apply a sin function that's scaled to make it feel better.
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
			wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		}

		double leftPwm, rightPwm, overPower;
		double sensitivity;

		double angularPower;
		double linearPower;

		// Negative inertia!
		double negInertiaAccumulator = 0.0;
		double negInertiaScalar;
		if (isHighGear) {
			negInertiaScalar = 4.0;
			sensitivity = .75;
		} else {
			if (wheel * negInertia > 0) {
				negInertiaScalar = 2.5;
			} else {
				if (Math.abs(wheel) > 0.65) {
					negInertiaScalar = 5.0;
				} else {
					negInertiaScalar = 3.0;
				}
			}
			sensitivity = .85; // Constants.sensitivityLow.getDouble();
		}
		double negInertiaPower = negInertia * negInertiaScalar;
		negInertiaAccumulator += negInertiaPower;

		wheel = wheel + negInertiaAccumulator;
		if (negInertiaAccumulator > 1) {
			negInertiaAccumulator -= 1;
		} else if (negInertiaAccumulator < -1) {
			negInertiaAccumulator += 1;
		} else {
			negInertiaAccumulator = 0;
		}
		linearPower = throttle;

		// Quickturn!
		if (isQuickTurn) {
			if (Math.abs(linearPower) < 0.2) {
				double alpha = 0.1;
				quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * limit(wheel, 1.0) * 5;
			}
			overPower = 1.0;
			if (isHighGear) {
				sensitivity = .8;
			} else {
				sensitivity = .8;
			}
			angularPower = wheel * sensitivity;
		} else {
			overPower = 0.0;
			angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
			if (quickStopAccumulator > 1) {
				quickStopAccumulator -= 1;
			} else if (quickStopAccumulator < -1) {
				quickStopAccumulator += 1;
			} else {
				quickStopAccumulator = 0.0;
			}
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}
		this.setLeft(leftPwm);
		this.setRight(rightPwm);

	}

	private void updateConstants() {
		KP_NORMAL = SmartDashboard.getNumber("KP_DRIVE_CONSTANT", .003);
		MAX_ANGULAR_VELOCITY = (int) SmartDashboard.getNumber("Max_Velcoity", 600);
		pid_Kp_NoThrottle_Left = SmartDashboard.getNumber("No_Throttle_Left", pid_Kp_NoThrottle_Left);
		pid_Kp_NoThrottle_Right = SmartDashboard.getNumber("No_Throttle_Right", pid_Kp_NoThrottle_Right);
		pid_Kp_Throttle_Right = SmartDashboard.getNumber("Throttle_Left", pid_Kp_Throttle_Right);
		pid_Kp_Throttle_Left = SmartDashboard.getNumber("Throttle_Right", pid_Kp_Throttle_Left);
	}

	private double skim(double v) {
		// gain determines how much to skim off the top
		if (v > 1.0) {
			return -((v - 1.0) * skimGain);
		} else if (v < -1.0) {
			return -((v + 1.0) * skimGain);
		}
		return 0;
	}

	public void drive(double leftX, double leftY, double rightX, double rightY) {
		// reverse these because pushing the Y joysticks forward returns a
		// negative value, this is fixed here
		this.cheesyDrive(leftY, rightX, Controllers.getInstance().driverController.getRB(), false);
		//driveHalo(leftY, rightX, leftX);
	}

	public int getLeftEncoder() {
		return leftEncoder.get();
	}

	public int getRightEncoder() {
		return rightEncoder.get();
	}

	public void stopDrive() {
		drive(0, 0, 0, 0);
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void setLeft(double val) {
		leftVictors[0].set(val);
		leftVictors[1].set(val);
	}

	public void setRight(double val) {
		rightVictors[0].set(-val);
		rightVictors[1].set(-val);

	}

	private void setStrafe(double value) {
		strafeVictor1.set(value);
	}

	public double getAverageCount() {
		return (rightEncoder.get());
	}

}
