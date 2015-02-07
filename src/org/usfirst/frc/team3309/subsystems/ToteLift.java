package org.usfirst.frc.team3309.subsystems;

public class ToteLift {

	private static ToteLift instance;
	
	public static ToteLift getInstance() {
		if(instance == null) {
			instance = new ToteLift();
		}
		return instance;
	}
	
	private ToteLift() {
		
	}
			
}
