package org.usfirst.frc.team3309.robot.commands.totelift;

import org.usfirst.frc.team3309.robot.subsystems.Drive;
import org.usfirst.frc.team3309.robot.subsystems.ToteLift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveToteLiftTime extends Command{

	double time = 1.5;
	int startCount = 760;
	double power = 0;
	Timer doneTimer = new Timer();
	boolean started = false;
	
	ToteLift mToteLift;
	
	public MoveToteLiftTime(double time, double power, int encoderCount) {
		this.time = time;
		this.power = power;
		this.startCount = encoderCount;
		System.out.println("MMADE IT ");
		setTimeout(5);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		doneTimer.reset();
		started = false;
		mToteLift = ToteLift.getInstance();
	}

	@Override
	protected void execute() {
		if(Drive.getInstance().getAverageCount() > startCount) {
			if(!started) {
				doneTimer.reset();
				doneTimer.start();
				started = true;
			}
			mToteLift.runLiftAt(power);
			
			System.out.println("TIMER: " + doneTimer.get());
			System.out.println("RUNJING " + power);
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return doneTimer.get() > time;
		return false;
	}

	@Override
	protected void end() {
		ToteLift.getInstance().runLiftAt(0);
		System.out.println("MoveToteLiftTime.end");
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
