package org.usfirt.frc.team3309.subsystems;

import org.usfirst.frc.team3309.robot.ModifiedGyro;
import org.usfirst.frc.team3309.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class Drive {
	private boolean isPrintingDriveInfo = false;

    //all of the sensors and motor controllers
    private Victor[] leftVictors = new Victor[2];
    private Victor[] rightVictors = new Victor[2];
    private Victor strafeVictor1;

    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private ModifiedGyro gyro;

    //enable this is you ever want to go just forward
    private boolean straightPidEnabled = false;
    private boolean straightAngleSet = false;

    //This method takes when the equation returns above the max or below the min and fixes it so it does not.  Uses some math and gets the job done
    private double skimGain = .25;

    //change this to change threshold
    private final double THRESHOLD = .3;
    //tells if gyro is a yay or nay
    private boolean gyroEnabled = true;
    //the max angular velocity (duh)
    private int MAX_ANGULAR_VELOCITY = 720;

    //Now for all the possible kp constants
    private double KP_NORMAL = .002;
    private double pid_Kp_NoThrottle_Right = 0.08;
    private double pid_Kp_NoThrottle_Left = 0.075;
    private double pid_Kp_Throttle_Right = 0.0875;
    private double pid_Kp_Throttle_Left = 0.08;

    private static Drive instance;

    private PIDController straightPID = null;

    private double throttle = 0;

    private int strafeCounter = 0;
    

    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();

        }
        return instance;
    }

    //the constructor
    private Drive() {
        //initialize Victors in their arrays
        leftVictors[0] = new Victor(RobotMap.DRIVE_LEFT_1);
        leftVictors[1] = new Victor(RobotMap.DRIVE_LEFT_2);
        rightVictors[0] = new Victor(RobotMap.DRIVE_RIGHT_1);
        rightVictors[1] = new Victor(RobotMap.DRIVE_RIGHT_2);
        strafeVictor1 = new Victor(RobotMap.DRIVE_STRAFE_1);

        //initialize Encoders
        leftEncoder = new Encoder(RobotMap.DRIVE_ENCODER_LEFT, RobotMap.DRIVE_ENCODER_LEFT, true, CounterBase.EncodingType.k1X);
        rightEncoder = new Encoder(RobotMap.DRIVE_ENCODER_RIGHT, RobotMap.DRIVE_ENCODER_RIGHT, false, CounterBase.EncodingType.k1X);
        //initialize gyro
        gyro = new ModifiedGyro(RobotMap.DRIVE_GYRO);

        
        //pid To be used Later
        /*StraightPID straight = new StraightPID();
        straightPID = new PIDController(.001, 0, .02, straight, straight);
        straightPID.disable();*/
    }

    double pidRequestedValue;
    boolean aimAngleIsSet = false;

}
