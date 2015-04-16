package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.commands.auto.ThreeTotePIDAuto;
import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.Intake;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftUp extends Command {

	private int startCount = 0;
	private boolean isWaitingForTote = false;
	Timer doneTimer = new Timer();

	public MoveToteLiftUp(int counts) {
		this.startCount = counts;
		isWaitingForTote = false;
	}

	public MoveToteLiftUp(int counts, boolean b) {
		this.startCount = counts;
		isWaitingForTote = b;
	}

	@Override
	protected void initialize() {
		doneTimer.start();

	}

	@Override
	protected void execute() {
		if (isWaitingForTote) {

			if (doneTimer.get() > 2) {
				ThreeTotePIDAuto.getInstance().setDone(true);
				this.getGroup().cancel();
				Drive.getInstance().stopDrive();
				Intake.getInstance().stopClaw();
				ToteLift.getInstance().runLiftAt(0);
				System.out.println("C\nA\nN\nC\nE\nL\nE\nD\n\n\n\n");
			}
			if (!ToteLift.getInstance().isToteSensorPressed()) {
				return;
			}
		}
		if (Drive.getInstance().getAverageCount() > startCount)
			ToteLift.getInstance().runLiftAt(1);

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return ToteLift.getInstance().getTop() && Drive.getInstance().getAverageCount() > startCount;
	}

	@Override
	protected void end() {
		ToteLift.getInstance().runLiftAt(0);

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
