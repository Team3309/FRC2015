package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.SuperSolenoid;
import org.usfirst.frc.team3309.robot.VexLimitSwitch;
import org.usfirst.frc.team3309.robot.commands.totelift.AutomaticToteLiftCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToteLift extends Subsystem {

	private static ToteLift instance;

	private SuperSolenoid latchSolenoid;
	private Victor toteLift;

	private DigitalInput toteSensor = new DigitalInput(RobotMap.TOTE_SENSOR);

	// private VexLimitSwitch topLimitSwitch;
	// private VexLimitSwitch botLimitSwitch;

	private Encoder liftEncoder;

	boolean useLimitSwitch = false;

	public static ToteLift getInstance() {
		if (instance == null) {
			instance = new ToteLift();
			instance.setDefaultCommand(AutomaticToteLiftCommand.getInstance());
		}
		return instance;
	}

	private ToteLift() {
		toteLift = new Victor(RobotMap.TOTE_LIFT);

		// topLimitSwitch = new
		// VexLimitSwitch(RobotMap.TOTE_LIFT_TOP_LIMIT_SWITCH);
		// botLimitSwitch = new
		// VexLimitSwitch(RobotMap.TOTE_LIFT_BOT_LIMIT_SWITCH);

		latchSolenoid = new SuperSolenoid(RobotMap.LATCH_SOLENOID);
		liftEncoder = new Encoder(RobotMap.TOTE_LIFT_ENCODER_A, RobotMap.TOTE_LIFT_ENCODER_B, true);
	}

	public void runLiftAt(double power) {
		setToteLiftPower(power);
		
		
	}

	public void setToteLiftPower(double power) {
		SmartDashboard.putNumber("TOTELIFT" , power);
		System.out.println("SETTING TOTE LIFT TO : " + power);
		toteLift.set(power);
	}

	public double getLiftEncoder() {
		return liftEncoder.get();
	}

	public void turnOnSolenoid() {
		latchSolenoid.turnOnSolenoid();
	}

	public void turnOffSolenoid() {
		latchSolenoid.turnOffSolenoid();
	}

	private boolean buttonLastState = false;

	public void toggle() {
		System.out.println("TOGGLE");
		if (buttonLastState == false) {
			latchSolenoid.toggleSolenoid();
			buttonLastState = true;
		}
	}

	public void notActivated() {
		buttonLastState = false;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	private boolean lastToteSensorVal = false;
	private boolean notTempLastToteSensorVal = false;
	public boolean isToteSensorPressed() {
		notTempLastToteSensorVal = toteSensor.get();
		return toteSensor.get();
	}
	
	//returns true if totelift value is different from last time
	public boolean isToteSensorToggle() {
		lastToteSensorVal = notTempLastToteSensorVal;
		if (lastToteSensorVal == isToteSensorPressed()) {
			return true;
		}
		return false;
	}

	public void resetEncoder() {
		liftEncoder.reset();
		
	}
}
