package org.firstinspires.ftc.teamcode.BasicAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FireHardwareMap;

@Autonomous(name="blueFarAuton", group="Auton")
public class blueFarAuton extends LinearOpMode {

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
            bad.drive(-65);
            sleep(2000);
            bad.drive(64);
            sleep(2000);
            bad.turn(-90);
            sleep(1500);
            bad.drive(190);
            sleep(9000);
        } else if (tickID == 1) {
            bad.turn(-12);
            sleep(500);
            bad.drive(-40);
            sleep(2000);
            bad.turn(12);
            sleep(500);
            bad.drive(32);
            sleep(2000);
            bad.turn(-90);
            sleep(1500);
            bad.drive(185);
            sleep(9000);
        } else if (tickID == 2) {
//            bad.drive(-52);
//            sleep(2000);
//            bad.turn(50);
//            sleep(1000);
//            bad.drive(-15);
//            sleep(1000);
            scorePreLoaded(0, bad);
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
