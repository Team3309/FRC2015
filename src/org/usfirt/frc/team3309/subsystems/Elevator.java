package org.usfirt.frc.team3309.subsystems;

public class Elevator {

	private static Elevator instance;
	
	public static Elevator getInstance() {
		if(instance == null) {
			instance = new Elevator();
		}
		return instance;
	}
			
}
