package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftUp extends Command{

	private int startCount = 0;

	public MoveToteLiftUp(int counts) {
		this.startCount  = counts;
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(Drive.getInstance().getAverageCount() > startCount)
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
