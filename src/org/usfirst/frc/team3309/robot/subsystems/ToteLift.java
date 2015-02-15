package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class ToteLift {

	private static ToteLift instance;

	private Victor toteLift;

	private DigitalInput topLimitSwitch;
	private DigitalInput botLimitSwitch;

	public static ToteLift getInstance() {
		if (instance == null) {
			instance = new ToteLift();
		}
		return instance;
	}

	private ToteLift() {
		toteLift = new Victor(RobotMap.TOTE_LIFT);
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
		if (topLimitSwitch.get())
			return true;
		else
			return false;
	}

	public boolean botBumperSwitchPressed() {
		if (botLimitSwitch.get())
			return true;
		else
			return false;
	}

}
