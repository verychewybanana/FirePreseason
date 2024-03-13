package org.firstinspires.ftc.teamcode.TimeAutons;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.EmptyHardwareMap;
import org.firstinspires.ftc.teamcode.FireHardwareMap;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name="redCloseAutonTime", group="TimeAutons")
@Disabled
public class redCloseTime extends LinearOpMode {
    public TfodProcessor tfod;
    public VisionPortal visionPortal;
    FireHardwareMap robot = null;

    public boolean recognized;
    public int num = 0;

    @Override
    public void runOpMode() {
        robot = new FireHardwareMap(this.hardwareMap);

        initTfod();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        if (opModeIsActive()) {
            robot.frontLeftMotor.setPower(0.5);
            robot.frontRightMotor.setPower(-0.5);
            robot.backLeftMotor.setPower(-0.5);
            robot.backRightMotor.setPower(0.5);

            sleep(1080);

            robot.frontLeftMotor.setPower(0.0);
            robot.frontRightMotor.setPower(0.0);
            robot.backLeftMotor.setPower(0.0);
            robot.backRightMotor.setPower(0.0);

            sleep(200);

            robot.frontLeftMotor.setPower(0.5);
            robot.frontRightMotor.setPower(0.5);
            robot.backLeftMotor.setPower(0.5);
            robot.backRightMotor.setPower(0.5);

            sleep(500);

            robot.frontLeftMotor.setPower(0.0);
            robot.frontRightMotor.setPower(0.0);
            robot.backLeftMotor.setPower(0.0);
            robot.backRightMotor.setPower(0.0);

            sleep(200);

            visionPortal.resumeStreaming();
            sleep(1500);
            recognized = (tfod.getRecognitions().size() > 0) ? true : false;

            if (num == 0) {
                telemetry.addData("Going Right", "");
                telemetry.update();
                robot.frontLeftMotor.setPower(-0.5);
                robot.frontRightMotor.setPower(0.5);
                robot.backLeftMotor.setPower(0.5);
                robot.backRightMotor.setPower(-0.5);

                sleep(200);

                robot.frontLeftMotor.setPower(0.0);
                robot.frontRightMotor.setPower(0.0);
                robot.backLeftMotor.setPower(0.0);
                robot.backRightMotor.setPower(0.0);

                sleep(200);

                robot.frontLeftMotor.setPower(0.5);
                robot.frontRightMotor.setPower(0.5);
                robot.backLeftMotor.setPower(0.5);
                robot.backRightMotor.setPower(0.5);

                sleep(100);

                robot.frontLeftMotor.setPower(0.0);
                robot.frontRightMotor.setPower(0.0);
                robot.backLeftMotor.setPower(0.0);
                robot.backRightMotor.setPower(0.0);

                sleep(200);

                robot.intakeMotor.setPower(-0.6);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);

                requestOpModeStop();
            }
            visionPortal.stopStreaming();

            robot.frontLeftMotor.setPower(-0.5);
            robot.frontRightMotor.setPower(0.5);
            robot.backLeftMotor.setPower(0.5);
            robot.backRightMotor.setPower(-0.5);

            sleep(500);

            robot.frontLeftMotor.setPower(0.0);
            robot.frontRightMotor.setPower(0.0);
            robot.backLeftMotor.setPower(0.0);
            robot.backRightMotor.setPower(0.0);

            sleep(200);

            robot.frontLeftMotor.setPower(0.5);
            robot.frontRightMotor.setPower(0.5);
            robot.backLeftMotor.setPower(0.5);
            robot.backRightMotor.setPower(0.5);

            sleep(370);

            robot.frontLeftMotor.setPower(0.0);
            robot.frontRightMotor.setPower(0.0);
            robot.backLeftMotor.setPower(0.0);
            robot.backRightMotor.setPower(0.0);

            visionPortal.resumeStreaming();
            sleep(1500);
            recognized = (tfod.getRecognitions().size() > 0) ? true : false;

            if (num == 1) {
                telemetry.addData("Found Central", "");
                telemetry.update();
//                robot.frontLeftMotor.setPower(0.5);
//                robot.frontRightMotor.setPower(-0.5);
//                robot.backLeftMotor.setPower(-0.5);
//                robot.backRightMotor.setPower(0.5);
//
//                sleep(200);
//
//                robot.frontLeftMotor.setPower(0.0);
//                robot.frontRightMotor.setPower(0.0);
//                robot.backLeftMotor.setPower(0.0);
//                robot.backRightMotor.setPower(0.0);
//
//                sleep(200);

                robot.frontLeftMotor.setPower(-0.5);
                robot.frontRightMotor.setPower(-0.5);
                robot.backLeftMotor.setPower(-0.5);
                robot.backRightMotor.setPower(-0.5);

                sleep(200);

                robot.frontLeftMotor.setPower(0.0);
                robot.frontRightMotor.setPower(0.0);
                robot.backLeftMotor.setPower(0.0);
                robot.backRightMotor.setPower(0.0);

                sleep(200);

//                robot.frontLeftMotor.setPower(-0.5);
//                robot.frontRightMotor.setPower(0.5);
//                robot.backLeftMotor.setPower(0.5);
//                robot.backRightMotor.setPower(-0.5);
//
//                sleep(200);
//
//                robot.frontLeftMotor.setPower(0.0);
//                robot.frontRightMotor.setPower(0.0);
//                robot.backLeftMotor.setPower(0.0);
//                robot.backRightMotor.setPower(0.0);
//
//                sleep(200);

                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);

                requestOpModeStop();
            }
            visionPortal.stopStreaming();

            robot.frontLeftMotor.setPower(-0.5);
            robot.frontRightMotor.setPower(0.5);
            robot.backLeftMotor.setPower(-0.5);
            robot.backRightMotor.setPower(0.5);

            sleep(700);

            robot.frontLeftMotor.setPower(0.0);
            robot.frontRightMotor.setPower(0.0);
            robot.backLeftMotor.setPower(0.0);
            robot.backRightMotor.setPower(0.0);

            sleep(200);

            robot.intakeMotor.setPower(-0.6);
            sleep(1500);
            robot.intakeMotor.setPower(0.0);
            sleep(500);



        }

    }

    private void initTfod() {
        String[] labels = {"redElement"};

        tfod = new TfodProcessor.Builder()
                .setModelAssetName("updatedRed.tflite")
                .setModelLabels(labels)
                .setTrackerMinCorrelation((float) 0.96)
                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        builder.setCameraResolution(new Size(640, 480));
        builder.enableLiveView(true);
        builder.addProcessor(tfod);

        visionPortal = builder.build();

        // Create the vision portal the easy way.
//        visionPortal = VisionPortal.easyCreateWithDefaults(
//                hardwareMap.get(WebcamName.class, "Webcam 1"), tfod);

        tfod.setZoom(1.0);


    }
}