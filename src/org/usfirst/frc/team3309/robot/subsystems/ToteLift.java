package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;

public class ToteLift {

	private static ToteLift instance;

	private Victor toteLift;

	public static ToteLift getInstance() {
		if (instance == null) {
			instance = new ToteLift();
		}
		return instance;
	}

	private ToteLift() {
		toteLift = new Victor(RobotMap.TOTE_LIFT);
	}

}