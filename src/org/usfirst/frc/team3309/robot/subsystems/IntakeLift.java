package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.commands.intakelift.IntakeLiftCommand;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Scheduler;

public class IntakeLift {

	private static IntakeLift instance;
	private Victor leftLift;
	private Victor rightLift;
	private Encoder leftEncoder;
	private Encoder rightEncoder;

	
	// master and slave
	private Victor masterVictor;
	private Victor slaveVictor;
	private Encoder masterEncoder;
	private Encoder slaveEncoder;

	public static IntakeLift getInstance() {
		if (instance == null) {
			instance = new IntakeLift();
		}
		return instance;
	}

	private IntakeLift() {
		leftLift = new Victor(RobotMap.INTAKE_LIFT_LEFT);
		rightLift = new Victor(RobotMap.INTAKE_LIFT_RIGHT);
		//leftEncoder = new Encoder(RobotMap.INTAKE_LIFT_LEFT_ENCODER_A, RobotMap.INTAKE_LIFT_LEFT_ENCODER_B, false, CounterBase.EncodingType.k1X);
		rightEncoder = new Encoder(RobotMap.INTAKE_LIFT_RIGHT_ENCODER_A, RobotMap.INTAKE_LIFT_RIGHT_ENCODER_B, false, CounterBase.EncodingType.k1X);

		masterVictor = rightLift;
		slaveVictor = leftLift;
		masterEncoder = rightEncoder;
		slaveEncoder = leftEncoder;
		
		IntakeLiftCommand com = new IntakeLiftCommand();
		
		
	}

	public double getRightEncoder() {
		return rightEncoder.get();
	}

	public double getLeftEncoder() {
		return rightEncoder.get();
	}

	public void runLiftAt(double power) {
		runLeftLiftAt(-power);
		runRightLiftAt(power);
	}

	public void runLeftLiftAt(double power) {
		leftLift.set(power);
	}

	public void runRightLiftAt(double power) {
		rightLift.set(power);
	}
	
	
	//slave and masters
	public double getMasterEncoder() {
		return masterEncoder.get();
	}

	public Victor getMasterVictor() {
		return masterVictor;
	}

	public double getSlaveEncoder() {
		return slaveEncoder.get();
	}

	public Victor getSlaveVictor() {
		return slaveVictor;
	}
	
	public void setSlaveVictor(double power) {
		getSlaveVictor().set(power);
	}
	public void setMasterVictor(double power) {
		getMasterVictor().set(power);
	}
}
