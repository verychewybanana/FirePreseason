package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

public class FireHardwareMap {
    //create drivetrain motors
    public DcMotor frontRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;

    // create slide motors
    public DcMotor slideLeftMotor = null;
    public DcMotor slideRightMotor = null;
    public DcMotor stringMotor = null;

    //create mechanism servos
    public DcMotor intakeMotor = null;
    public Servo boxLeftServo = null;
    public Servo boxRightServo = null;
    public CRServo doorServo = null;
//    public Servo separatorServo = null;
    public CRServo airplaneServo = null;
//    public Servo hookServo = null;

//    public RevBlinkinLedDriver led = null;

    public ColorRangeSensor color = null;

    public BNO055IMU imu = null;

public RevBlinkinLedDriver led = null;


    //Hardware Map object
    com.qualcomm.robotcore.hardware.HardwareMap HardwareMap = null;

    public ElapsedTime runtime = new ElapsedTime();

    public FireHardwareMap(com.qualcomm.robotcore.hardware.HardwareMap hwmap){

        initialize(hwmap);
    }

    private void initialize(com.qualcomm.robotcore.hardware.HardwareMap hwmap){
        HardwareMap = hwmap;
        //the name of device should change based on name
        frontRightMotor = HardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = HardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = HardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = HardwareMap.get(DcMotor.class, "backLeftMotor");
        intakeMotor = HardwareMap.get(DcMotor.class, "intakeMotor");
        stringMotor = HardwareMap.get(DcMotor.class, "stringMotor");
        slideLeftMotor = HardwareMap.get(DcMotor.class, "slideLeftMotor");
        slideRightMotor = HardwareMap.get(DcMotor.class, "slideRightMotor");
       led = HardwareMap.get(RevBlinkinLedDriver.class, "led");
        color = HardwareMap.get(ColorRangeSensor.class, "color");
        //Making servo
        boxLeftServo = HardwareMap.get(Servo.class, "leftServo");
        boxRightServo = HardwareMap.get(Servo.class, "rightServo");
//        separatorServo = HardwareMap.get(Servo.class, "separatorServo");
        doorServo = HardwareMap.get(CRServo.class, "doorServo");
        airplaneServo = HardwareMap.get(CRServo.class, "airplaneServo");
//        hookServo = HardwareMap.get(Servo.class, "hookServo");

        slideLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //Set up motor direction
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
//        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
//        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        slideLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        slideRightMotor.setDirection((DcMotor.Direction.FORWARD));

        stringMotor.setDirection(DcMotor.Direction.FORWARD);

        boxLeftServo.setDirection(Servo.Direction.FORWARD);
        boxRightServo.setDirection(Servo.Direction.REVERSE);
//        separatorServo.setDirection(Servo.Direction.FORWARD);
        airplaneServo.setDirection(CRServo.Direction.REVERSE);
        doorServo.setDirection(CRServo.Direction.FORWARD);
//        hookServo.setDirection(Servo.Direction.FORWARD);

        //Set motor mode
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        //Set zero power behavior

        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stringMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set 0 power
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
//        intakeMotor.setPower(0);
//        actuatorMotor.setPower(0);

        boxLeftServo.setPosition(Constants.BOXLEFTSERVO_HOMEPOSITION);
        boxRightServo.setPosition(Constants.BOXRIGHTSERVO_HOMEPOSITION);




        boxLeftServo.setPosition(0.0);
        boxRightServo.setPosition(0.0);
//        separatorServo.setPosition(0.5);
        doorServo.setPower(0.35);
        airplaneServo.setPower(0.0);
//        hookServo.setPower(0.0);




        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        //return value of radians
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        //gets imu from rev hardware map and connects it to code
        imu = hwmap.get(BNO055IMU.class, "imuex");
        //sets the settings we declared above.
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);


    }








}