package org.firstinspires.ftc.teamcode.BasicAuton;

import android.util.Size;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.EmptyHardwareMap;
import org.firstinspires.ftc.teamcode.FireHardwareMap;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.List;
@Autonomous(name="roadRunnerRedBackboard", group="Auton")
public class roadRunnerRedBackboard extends LinearOpMode {

    public TfodProcessor tfod;
    public VisionPortal visionPortal;
    EmptyHardwareMap robot = null;

    public boolean recognized;

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        robot = new EmptyHardwareMap(this.hardwareMap);

        initTfod();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

//        visionPortal.stopStreaming();

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPose);

        TrajectorySequence navigateRight = drive.trajectorySequenceBuilder(startPose)
                .strafeRight(5.9)
                .forward(15.0)
//                .lineToLinearHeading(new Pose2d(26, 15, Math.toRadians(x)))
                .build();

        TrajectorySequence slightLeftShiftFromRightTick = drive.trajectorySequenceBuilder(navigateRight.end())
                .strafeLeft(1.0)
                .build();

        TrajectorySequence parkFromRight = drive.trajectorySequenceBuilder(slightLeftShiftFromRightTick.end())
                .strafeRight(30.0)
                .turn(Math.toRadians(90))
                .build();

        TrajectorySequence navigateMiddle = drive.trajectorySequenceBuilder(navigateRight.end())
                .waitSeconds(0.2)
                .strafeLeft(9.0)
                .forward(7.0)
                .build();

        waitForStart();

        if (opModeIsActive()) {
            sleep(200);
            visionPortal.stopStreaming();

            drive.followTrajectorySequence(navigateRight);

            visionPortal.resumeStreaming();
            sleep(1500);
            recognized = (tfod.getRecognitions().size() > 0) ? true : false;
            if (recognized) {
                drive.followTrajectorySequence(slightLeftShiftFromRightTick);
                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);
                drive.followTrajectorySequence(parkFromRight);
                requestOpModeStop();
            }
            visionPortal.stopStreaming();

            telemetry.addData("Detected Right: ", "no");
            telemetry.update();

            drive.followTrajectorySequence(navigateMiddle);

            //        visionPortal.resumeStreaming();
            //        sleep(1000);
            //        recognized = (tfod.getRecognitions().size() > 0) ? true : false;
            //        if (recognized) {
            //            drive.followTrajectorySequence(parkFromRight);
            //            stop();
            //        }

            //OUTPUT PIXEL CODE

            //        drive.followTrajectorySequence(Step2);
        }
    }

    private void initTfod() {
        String[] labels = {"redElement"};

        tfod = new TfodProcessor.Builder()
                .setModelAssetName("updatedRed.tflite")
                .setModelLabels(labels)
                .setTrackerMinCorrelation(0.93f)
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

