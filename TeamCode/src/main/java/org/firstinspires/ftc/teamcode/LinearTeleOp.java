package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**

 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left
 *

 */

@TeleOp(name="Robot Oriented TeleOpp", group="Linear Opmode")
public class LinearTeleOp extends LinearOpMode {

    /*
    Controls for gamepad2
    Dpad down - lower slides
    Dpad up - raise slides to high level
    Dpad right - raise slides to mid level
    Y - open door
    B - close door
    A - tilt box to scoring position
    X - return box to 0
    Left bumper - toggle separator
    Right bumper - toggle hook servo up or down
    Left joystick y - spin intake wheels
    Right joystick x - actuator motor
     */


    //ServoImplEx servo;
    //PwmControl.PwmRange range = new PwmControl.PwmRange(533,2425);
    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private FireHardwareMap HW = null;

    public final double leftRightServoSpeed = 0.01;
    public final double backRightMultiplier = 1.1;
    public boolean isStrafing = false;

    @Override

    public void runOpMode() {
        HW = new FireHardwareMap(this.hardwareMap);

        HW.doorServo.setPower(0.0);

        //servo = hardwareMap.get(ServoImplEx.class, "left_hand");
        //servo.setPwmRange(range);


        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Last updated: ", "");
        telemetry.update();

        waitForStart();
        runtime.reset();

        boolean separated = false;
        boolean hookUp = false;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;
            double i =0.0;

            double GRB = 0;
            // 0 = white 702, 631, 628
            // 1 = Green 210, 120,170
            //2 = Purple 290, 287, 360
            // 3 = Yellow 248, 308, 146

            if (HW.color.green() <= 752 && HW.color.green()>=562 && HW.color.red() <= 681 && HW.color.red() >= 581 && HW.color.blue()<=678 && HW.color.blue()>=578 ){
                HW.led.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
            } if (HW.color.green() <= 260 && HW.color.green()>=160 && HW.color.red() <= 170 && HW.color.red() >= 70 && HW.color.blue()<=170 && HW.color.blue()>=70) {
                HW.led.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
            }
            if (HW.color.green() <= 340 && HW.color.green()>=240 && HW.color.red() <= 340 && HW.color.red() >= 240 && HW.color.blue()<=410 && HW.color.blue()>=310 ){
                HW.led.setPattern(RevBlinkinLedDriver.BlinkinPattern.VIOLET);
            }
            if (HW.color.green() <= 298 && HW.color.green()>=198 && HW.color.red() <= 358 && HW.color.red() >= 258 && HW.color.blue()<=196 && HW.color.blue()>=96 ){
                HW.led.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
            }




            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x * 1.1;
            double yaw     =  gamepad1.right_stick_x;

//            if (lateral >= 0.5) isStrafing = true;


            double axial2 =  -gamepad2.left_stick_y;
//            double lateral2 =  gamepad2.left_stick_x * 1.1;
            double yaw2     =  gamepad2.right_stick_x;


            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;
            double slidePower;
            double intakeWheelPower = gamepad1.right_trigger - gamepad1.left_trigger;

            double hangMotorPower = 0;


            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));
            i = gamepad1.right_trigger;
            if (max > 1) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
//                axial2 /=max;
                i /= max;
//                (lateral2)/=max;
                yaw2 /=max;
            }

            if (gamepad1.right_bumper) {
                i=i;
            }
            else{
                leftFrontPower  /= 2;
                rightFrontPower /= 2;
                leftBackPower   /= 2;
                rightBackPower  /= 2;
            }




            double doorServoPower = HW.doorServo.getPower();

            if (gamepad2.b) {
                doorServoPower = 0.35;
            } else if (gamepad2.a) {
                doorServoPower = -0.8;
//            } else {
//                doorServoPower = 0.0;
            }

            double airplaneServoPower;

            if (gamepad1.y) {
                airplaneServoPower = 0.8;
            } else if (gamepad1.a) {
                airplaneServoPower = -0.8;
            } else {
                airplaneServoPower = 0;
            }

            double leftRightServoPosition = HW.boxLeftServo.getPosition();
            if (gamepad2.right_trigger > 0.1) {
                leftRightServoPosition = 0.3;
//                if (leftRightServoPosition > 0.6) leftRightServoPosition = 0.6;
            } else if (gamepad2.left_trigger > 0.1) {
                leftRightServoPosition = 0.0;
            } else {
                if ((-gamepad2.right_stick_y) > 0.1 || (-gamepad2.right_stick_y) < -0.1)
                    leftRightServoPosition += leftRightServoSpeed * (-gamepad2.right_stick_y);

            }

            if (leftRightServoPosition < 0.0) leftRightServoPosition = 0.0;
            else if (leftRightServoPosition > 0.3) leftRightServoPosition = 0.3;

//            double separatorServoPosition = HW.separatorServo.getPosition();
//            if (gamepad2.left_bumper) {
//                separatorServoPosition = 0.3;
//            }  else if (gamepad2.right_bumper) {
//                separatorServoPosition = 0.7;
//            }
//
//            double hookServoPower;

            if (gamepad1.dpad_up) {
                hangMotorPower = 0.9;
            } else if (gamepad1.dpad_down) {
                hangMotorPower = -0.9;
            }

            if (gamepad2.dpad_up) {
                slidePower = 0.8;
            } else if (gamepad2.dpad_down) {
                slidePower = -0.8;
            } else {
                slidePower = 0;
            }

//            axial2 = axial2/1.5;
//
//            lateral2 = lateral2/1.5;
//
            yaw2 = yaw2/1.5;

//            if (isStrafing)
//                rightBackPower *= backRightMultiplier;



            // Send calculated power to wheels
            HW.frontLeftMotor.setPower(leftFrontPower);
            HW.frontRightMotor.setPower(rightFrontPower);
            HW.backLeftMotor.setPower(leftBackPower*1.1);
            HW.backRightMotor.setPower(rightBackPower*1.1);
            HW.intakeMotor.setPower(intakeWheelPower/1.01);
//            HW.actuatorMotor.setPower(yaw2);

            HW.boxLeftServo.setPosition(leftRightServoPosition);
            HW.boxRightServo.setPosition(leftRightServoPosition);

            HW.slideLeftMotor.setPower(slidePower);
            HW.slideRightMotor.setPower(slidePower);


            HW.doorServo.setPower(doorServoPower);
//            HW.boxRightServo.setPower(leftRightServoPower*0.5);
//            HW.boxLeftServo.setPower(leftRightServoPower*0.45*0.5);
//            HW.separatorServo.setPosition(separatorServoPosition);
            HW.airplaneServo.setPower(airplaneServoPower);

            HW.stringMotor.setPower(hangMotorPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("LED GREEN", HW.color.green());
            telemetry.addData("LED red", HW.color.red());
            telemetry.addData("LED blue", HW.color.blue());
            telemetry.addData("LED ARGB", HW.color.argb());

//            telemetry.addData("Servo  left/Right", "%4.2f, %4.2f", axial2, axial2);
//            telemetry.addData("Intake Operational: ", HW.intakeMotor.isBusy());
//            telemetry.addData("Intake Number: ", i);
//            telemetry.addData("Current frontLeftMotor Encoder Position: ", HW.frontLeftMotor.getCurrentPosition());
//            telemetry.addData("frontLeftMotor Operational: ", HW.frontLeftMotor.isBusy());
//            telemetry.addData("Current frontRightMotor Encoder Position: ", HW.frontRightMotor.getCurrentPosition());
//            telemetry.addData("frontRightMotor Operational: ", HW.frontRightMotor.isBusy());
//            telemetry.addData("Current backLeftMotor Encoder Position: ", HW.backLeftMotor.getCurrentPosition());
//            telemetry.addData("backLeftMotor Operational: ", HW.backLeftMotor.isBusy());
//            telemetry.addData("Current backRightMotor Encoder Position: ", HW.backRightMotor.getCurrentPosition());
//            telemetry.addData("backRightMotor Operational: ", HW.backRightMotor.isBusy());
            telemetry.addData("slideLeftMotorTicks target ", HW.slideLeftMotor.getTargetPosition());
            telemetry.addData("slideLeftMotor current ", HW.slideLeftMotor.getCurrentPosition());
            telemetry.addData("slideRightMotor current ", HW.slideRightMotor.getCurrentPosition());
//            telemetry.addData("boxLeftServo pow: ", HW.boxLeftServo.getPower());
//            telemetry.addData("doorServo power: ", HW.doorServo.getPower());
//            telemetry.addData("doorServo exists: ", HW.doorServo.getDeviceName());
//            telemetry.addData("light: ", HW.color.getRawLightDetectedMax());
            telemetry.addData("boxLeftServo Position: ", HW.boxLeftServo.getPosition());
            telemetry.addData("boxRightServo Position: ", HW.boxRightServo.getPosition());
            telemetry.addData("boxServos target: ", leftRightServoPosition);
//            telemetry.addData("separatorServo current position: ", HW.separatorServo.getPosition());
            telemetry.addData("doorServo power: ", HW.doorServo.getPower());
            telemetry.update();

        }
    }
}