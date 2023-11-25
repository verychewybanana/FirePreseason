package org.firstinspires.ftc.teamcode.BasicAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FireHardwareMap;

@Autonomous(name="redFarAuton", group="Auton")
public class redFarAuton extends LinearOpMode {

    FireHardwareMap robot = null;

    @Override
    public void runOpMode() {
        robot = new FireHardwareMap(this.hardwareMap);
        BasicAutoDriving autoDriving = new BasicAutoDriving(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Initialized");

        if (opModeIsActive()){




        }

    }

    public void scorePreLoaded(int tickID, BasicAutoDriving bad) {
        if (tickID == 0) { // middle tick
            bad.drive(70);
            sleep(2000);
            bad.drive(-65);
            sleep(2000);
            bad.turn(86);
            sleep(1500);
            bad.drive(180);
            sleep(3000);
        } else if (tickID == 1) { // left tick
            bad.turn(-13);
            sleep(500);
            bad.drive(48);
            sleep(2000);
            bad.drive(-40);
            sleep(2000);
            bad.turn(13);
            sleep(500);
            bad.turn(86);
            sleep(1500);
            bad.drive(180);
            sleep(3000);
        } else if (tickID == 2) { // right tick
            bad.drive(40);
            sleep(1500);
            bad.turn(45);
            sleep(500);
            bad.drive(18);
            sleep(1000);
            bad.drive(-18);
            sleep(1000);
            bad.turn(-45);
            sleep(500);
            bad.drive(-27);
            sleep(1500);
            bad.turn(80);
            sleep(1000);
            bad.drive(180);
            sleep(3000);


//            bad.turn(15);
//            sleep(500);
//            bad.drive(45);
//            sleep(2000);
//            bad.turn(-15);
//            sleep(500);
//            bad.drive(-38);
//            sleep(2000);
//            bad.turn(86);
//            sleep(1500);
//            bad.drive(40);
//            sleep(9000);
        }
    }

    public int parseDirection(String dir) {
        if (dir == "left") {
            return 1;
        } else if (dir == "center") {
            return 0;
        } else if (dir == "right") {
            return 2;
        }
        return 0;
    }
}
