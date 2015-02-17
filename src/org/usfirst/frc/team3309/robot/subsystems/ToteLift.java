package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.SuperSolenoid;
import org.usfirst.frc.team3309.robot.VexLimitSwitch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToteLift implements PIDSource, PIDOutput {

	private static ToteLift instance;

	private SuperSolenoid latchSolenoid;

	private Victor toteLift;
	
	private PIDController controller;
	private boolean pidRunning = false;

	private double KP = .02;
	private double KD = .02;
	// private VexLimitSwitch topLimitSwitch;
	// private VexLimitSwitch botLimitSwitch;

	private Encoder liftEncoder;

	private int MAX_HEIGHT = 2000;
	private int SLOW_DOWN_BOTTOM = 20;
	private int SLOW_DOWN_TOP = 1500;

	boolean useLimitSwitch = false;

	public static ToteLift getInstance() {
		if (instance == null) {
			instance = new ToteLift();
		}
		return instance;
	}

	private ToteLift() {
		toteLift = new Victor(RobotMap.TOTE_LIFT);

		SmartDashboard.putNumber("Max_Height_Tote", MAX_HEIGHT);
		SmartDashboard.putNumber("KP_TOTE_LIFT", KP);
		SmartDashboard.putNumber("KD_TOTE_LIFT", KD);
		SmartDashboard.putNumber("SLOW_DOWN_BOTTOM", SLOW_DOWN_BOTTOM);
		SmartDashboard.putNumber("SLOW_DOWN_TOP", SLOW_DOWN_TOP);
		
		controller = new PIDController(KP, 0, KD, this, this);

		// topLimitSwitch = new
		// VexLimitSwitch(RobotMap.TOTE_LIFT_TOP_LIMIT_SWITCH);
		// botLimitSwitch = new
		// VexLimitSwitch(RobotMap.TOTE_LIFT_BOT_LIMIT_SWITCH);
		latchSolenoid = new SuperSolenoid(RobotMap.LATCH_SOLENOID);

		liftEncoder = new Encoder(RobotMap.TOTE_LIFT_ENCODER_A, RobotMap.TOTE_LIFT_ENCODER_B);
	}

	public void runLiftAt(double power) {
		if(true) {
			toteLift.set(power);
			System.out.println("LIFT ENCODER: " + this.getLiftEncoder());
			return;
		}

		if (isBelowSlowDownBottom()) {
			if (power > 0) {
				toteLift.set(power);
				pidRunning = false;
			}else {
				controller.setSetpoint(0);
				pidRunning = true;
			}
		} else if (isAboveSlowDownTop()) {
			if (power < 0){
				toteLift.set(power);
				pidRunning = false;
			}else {
				controller.setSetpoint(MAX_HEIGHT);
				pidRunning = true;
			}
		} else {
			pidRunning = false;
			toteLift.set(power);
		}

		updateConstants();

	}

	public boolean isBelowSlowDownBottom() {
		if (getLiftEncoder() <= SLOW_DOWN_BOTTOM)
			return true;
		else
			return false;
	}

	public double getLiftEncoder() {
		return liftEncoder.get();
	}

	public boolean isAboveSlowDownTop() {
		if (getLiftEncoder() >= SLOW_DOWN_TOP)
			return true;
		else
			return false;
	}

	public void updateConstants() {
		MAX_HEIGHT = (int) SmartDashboard.getNumber("Max_Height_Tote");
		KP = SmartDashboard.getNumber("KP_TOTE_LIFT");
		KD = SmartDashboard.getNumber("KD_TOTE_LIFT");
		SLOW_DOWN_BOTTOM = (int) SmartDashboard.getNumber("SLOW_DOWN_BOTTOM");
		SLOW_DOWN_TOP = (int) SmartDashboard.getNumber("SLOW_DOWN_TOP");
	}

	public void turnOnSolenoid() {
		latchSolenoid.turnOnSolenoid();
	}

	public void turnOffSolenoid() {
		latchSolenoid.turnOffSolenoid();
	}

	@Override
	public void pidWrite(double output) {
		if(pidRunning)
			toteLift.set(output);
	}

	@Override
	public double pidGet() {
		return getLiftEncoder();
	}

}
