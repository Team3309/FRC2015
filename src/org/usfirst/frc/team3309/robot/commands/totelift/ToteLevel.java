package org.usfirst.frc.team3309.robot.commands.totelift;


public enum ToteLevel {
	
	ONE_TOTE(.007, 000, 1000), TWO_TOTE(.008, 000, 1000), THREE_TOTE(.009, 000, 1000), FOUR_TOTE(.01, 000, 1000), FIVE_TOTE(.012, 000, 1000), SIX_TOTE(.015, 000, 1000), BOTTOM(.014, 00, 0);

	private final double kP; // K TERM
	private final double kD; // D TERM
	private final int setPoint; //aim encoder counts

	ToteLevel(double kP, double kD, int setPoint) {
		this.kP = kP;
		this.kD = kD;
		this.setPoint = setPoint;
	}

	public double getkP() {
		return kP;
	}

	public double getkD() {
		return kD;
	}
	
	public int getSetPoint() {
		return setPoint;
	}
}
