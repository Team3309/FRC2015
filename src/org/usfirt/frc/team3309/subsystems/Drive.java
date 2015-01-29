package org.usfirt.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class Drive {
	private boolean isPrintingDriveInfo = false;

    //all of the sensors and motor controllers
    private Victor[] leftVictors = new Victor[3];
    private Victor[] rightVictors = new Victor[3];
    private Victor strafeVictor1;
    private Victor strafeVictor2;

    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private ModifiedGyro gyro;
    private Solenoid driveShifterHighGear;
    private Solenoid driveShifterLowGear;

    //all the constants for which drive is being used
    private final int MODE_HALO_DRIVE = 0;
    private final int MODE_TANK_DRIVE = 1;
    private int driveMode = 0;

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

    //speed barrier for encoder, when encoder.getRate() exceeds this value, high gear automatically happens
    private final double SPEED_BARRIER = 40;

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
    
    private PIDCommand strafePIDCommander = new strafePIDCommander();

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
        leftVictors[2] = new Victor(RobotMap.DRIVE_LEFT_3);
        rightVictors[0] = new Victor(RobotMap.DRIVE_RIGHT_1);
        rightVictors[1] = new Victor(RobotMap.DRIVE_RIGHT_2);
        rightVictors[2] = new Victor(RobotMap.DRIVE_RIGHT_3);

        strafeVictor1 = new Victor(RobotMap.DRIVE_STRAFE_1);
        strafeVictor2 = new Victor(RobotMap.DRIVE_STRAFE_2);

        driveShifterHighGear = new Solenoid(RobotMap.DRIVE_DRIVESHIFTER_RIGHT);
        driveShifterLowGear = new Solenoid(RobotMap.DRIVE_DRIVESHIFTER_LEFT);

        //initialize Encoders
        leftEncoder = new Encoder(RobotMap.DRIVE_ENCODER_LEFT_A, RobotMap.DRIVE_ENCODER_LEFT_B, true, CounterBase.EncodingType.k1X);
        rightEncoder = new Encoder(RobotMap.DRIVE_ENCODER_RIGHT_A, RobotMap.DRIVE_ENCODER_RIGHT_B, false, CounterBase.EncodingType.k1X);
        leftEncoder.start();
        rightEncoder.start();
        //initialize gyro
        gyro = new ModifiedGyro(RobotMap.DRIVE_GYRO);

        StraightPID straight = new StraightPID();
        straightPID = new PIDController(.001, 0, .02, straight, straight);
        straightPID.disable();
    }

    double pidRequestedValue;
    boolean aimAngleIsSet = false;

}
