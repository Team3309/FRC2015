package org.usfirst.frc.team3309.robot.commands.totelift;

public enum ToteLevel {
	ONE_TOTE(.007, 000), TWO_TOTE(.008, 000), THREE_TOTE(.009, 000), FOUR_TOTE(.01, 000), FIVE_TOTE(.012, 000), SIX_TOTE(.015, 000);

	private final double kP; // K TERM
	private final double kD; // D TERM

	ToteLevel(double kP, double kD) {
		this.kP = kP;
		this.kD = kD;
	}

	public double getkP() {
		return kP;
	}

	public double getkD() {
		return kD;
	}
}
