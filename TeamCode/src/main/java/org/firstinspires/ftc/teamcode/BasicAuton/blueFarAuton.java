/*
package org.firstinspires.ftc.teamcode.BasicAuton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.FireHardwareMap;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name="blueFarAuton", group="Auton")
public class blueFarAuton extends LinearOpMode {

    private TfodProcessor tfod;
    private VisionPortal visionPortal;
    FireHardwareMap robot = null;

    @Override
    public void runOpMode() {
        robot = new FireHardwareMap(this.hardwareMap);
        BasicAutoDriving autoDriving = new BasicAutoDriving(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while (opModeIsActive()) {
//            initTfod();
//            int tickID = findPixelLocation(autoDriving);
            findPixelLocation(autoDriving);


        }
    }

    public int findPixelLocation(BasicAutoDriving bad) {
        bad.drive(178);
        return 0;
    }
}
 */