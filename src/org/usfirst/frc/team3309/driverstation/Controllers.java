package org.usfirst.frc.team3309.driverstation;

public class Controllers {

	private static Controllers instance;

	// All Controllers being used
	public XboxController driverController = new XboxController(0);
	public XboxController OperatorController = new XboxController(1);

	public static Controllers getInstance() {
		if (instance == null) {
			instance = new Controllers();
		}
		return instance;
	}

	private Controllers() {

	}
}
