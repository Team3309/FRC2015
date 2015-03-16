package org.usfirst.frc.team3309.robot;

public class IntakeSolenoid {
	SuperSolenoid backSolenoid;
	SuperSolenoid frontSolenoid;

	// right channel always first
	public IntakeSolenoid(int backCh, int frontCh) {
		backSolenoid = new SuperSolenoid(backCh);
		frontSolenoid = new SuperSolenoid(frontCh);
	}

	public void setNeutral() {
		backSolenoid.set(false);
		frontSolenoid.set(false);
	}

	public void setExtended() {
		backSolenoid.set(true);
		frontSolenoid.set(false);
	}

	public void setRetracted() {
		backSolenoid.set(false);
		frontSolenoid.set(true);
	}

	public boolean isExtended() {
		if (backSolenoid.get() && !frontSolenoid.get())
			return true;

		return false;
	}

	public void toggleSolenoid() {
		if (isExtended())
			setRetracted();
		else
			setExtended();
	}

}
