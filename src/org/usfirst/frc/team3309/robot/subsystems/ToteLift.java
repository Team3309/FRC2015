package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.VexLimitSwitch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class ToteLift {

	private static ToteLift instance;

	private Solenoid latchSolenoid;

	private Victor toteLift;

	private VexLimitSwitch topLimitSwitch;
	private VexLimitSwitch botLimitSwitch;

	public static ToteLift getInstance() {
		if (instance == null) {
			instance = new ToteLift();
		}
		return instance;
	}

	private ToteLift() {
		toteLift = new Victor(RobotMap.TOTE_LIFT);
		
		latchSolenoid = new Solenoid(RobotMap.LATCH_SOLENOID);
	}

	public void runLiftAt(double power) {

		if (botBumperSwitchPressed()) {
			if (power > 0)
				toteLift.set(power);
		} else if (topBumperSwitchPressed()) {
			if (power < 0)
				toteLift.set(power);
		} else {
			toteLift.set(power);
		}

	}

	public boolean topBumperSwitchPressed() {
		if (topLimitSwitch.isPressed())
			return true;
		else
			return false;
	}

	public boolean botBumperSwitchPressed() {
		if (botLimitSwitch.isPressed())
			return true;
		else
			return false;
	}
	
	public void turnOnSolenoid() {
		latchSolenoid.set(true);
	}

	public void turnOffSolenoid() {
		latchSolenoid.set(false);
	}

	public void toggleSolenoid() {
		latchSolenoid.set(!latchSolenoid.get());
	}


}
