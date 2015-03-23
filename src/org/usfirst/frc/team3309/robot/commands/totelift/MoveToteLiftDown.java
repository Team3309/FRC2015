package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftDown extends Command {

	private int startCount = 0;
	private boolean started = false;
	private Timer doneTimer = new Timer();

	public MoveToteLiftDown(int counts) {
		this.startCount = counts;
		doneTimer.reset();
		doneTimer.stop();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		if (Drive.getInstance().getAverageCount() > startCount) {
			if(!started) {
				started = true;
				doneTimer.start();
			}
			
			if(doneTimer.get() > .7) {
				ToteLift.getInstance().runLiftAt(-.5);
			}else {
				ToteLift.getInstance().runLiftAt(-1);
			}
			System.out.println("RUNNING DOWN");
		}
		System.out.println("NOT RUNNING DOWN");
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return ToteLift.getInstance().getBot() && Drive.getInstance().getAverageCount() > startCount;
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
