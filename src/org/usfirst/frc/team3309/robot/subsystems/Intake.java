package org.usfirst.frc.team3309.robot.subsystems;

import org.usfirst.frc.team3309.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

@SuppressWarnings("unused")
public class Intake {
    private Victor rightClaw;
    private Victor leftClaw;
    private Solenoid intakeSolenoid;

    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }

    private Intake() {
        rightClaw = new Victor(RobotMap.CLAW_RIGHTSIDE);
        leftClaw = new Victor(RobotMap.CLAW_LEFTSIDE);
        intakeSolenoid = new Solenoid(RobotMap.INTAKE_SOLENOID);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void runClawInward() {
        rightClaw.set(1);
        leftClaw.set(1);
    }

    public void runClawOutward() {
        rightClaw.set(-1);
        leftClaw.set(-1);
    }

    public void stopClaw() {
        rightClaw.set(0);
        leftClaw.set(0);
    }
}
