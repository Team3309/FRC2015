package org.usfirst.frc.team3309.robot.commands.auto;

import org.usfirst.frc.team3309.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3309.robot.commands.intakelift.IntakeLiftRunEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAuto extends CommandGroup {

	public TestAuto() {
		this.addSequential(new TurnToAngle(90));
		this.addSequential(new WaitCommand());
	}
}
