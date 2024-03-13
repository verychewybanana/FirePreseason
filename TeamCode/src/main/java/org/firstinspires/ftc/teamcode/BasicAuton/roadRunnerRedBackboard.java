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
import org.firstinspires.ftc.teamcode.opencv.RedPositionDetector;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.List;
@Autonomous(name="roadRunnerRedBackboard", group="Auton")
public class roadRunnerRedBackboard extends LinearOpMode {


    FireHardwareMap robot = null;
    boolean recognized;


    @Override
    public void runOpMode() {
        RedPositionDetector pd = new RedPositionDetector(hardwareMap, telemetry);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        robot = new FireHardwareMap(this.hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPose);

        TrajectorySequence navigateRight = drive.trajectorySequenceBuilder(startPose)
                .strafeRight(12)
                .forward(10.0)
//                .lineToLinearHeading(new Pose2d(26, 15, Math.toRadians(x)))
                .build();

        TrajectorySequence parkFromRight = drive.trajectorySequenceBuilder(navigateRight.end())
                .strafeRight(30.0)
                .turn(Math.toRadians(90))
                .build();

        TrajectorySequence navigateMiddle = drive.trajectorySequenceBuilder(navigateRight.end())
                .waitSeconds(0.2)
                .strafeLeft(4.0)
                .forward(5.0)
                .build();

        TrajectorySequence parkFromMiddle = drive.trajectorySequenceBuilder(navigateMiddle.end())
                .waitSeconds(0.2)
                .strafeRight(35.0)
                .turn(Math.toRadians(90))
                .build();

        TrajectorySequence navigateLeft = drive.trajectorySequenceBuilder(navigateMiddle.end())
                .waitSeconds(0.2)
                .strafeLeft(5.0)
                .back(2.0)
                .build();

        TrajectorySequence parkFromLeft = drive.trajectorySequenceBuilder(navigateLeft.end())
                .waitSeconds(0.2)
                .strafeRight(40.0)
                .turn(Math.toRadians(90))
                .build();

        waitForStart();

        pd.startStreaming();

        if (opModeIsActive()) {
            sleep(200);

            drive.followTrajectorySequence(navigateRight);

            sleep(1500);

            recognized = false;
//            recognized = () ? true : false;
            if (recognized) {
                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);
//                drive.followTrajectorySequence(parkFromRight);
                requestOpModeStop();
            }

            telemetry.addData("Detected Right: ", "no");
            telemetry.update();

            drive.followTrajectorySequence(navigateMiddle);

            recognized = false;
//            recognized = () ? true : false;
            if (recognized) {
                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);
//                drive.followTrajectorySequence(parkFromMiddle);
                requestOpModeStop();
            }

            drive.followTrajectorySequence(navigateLeft);

            recognized = true;
//            recognized = () ? true : false;
            if (recognized) {
                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);
//                drive.followTrajectorySequence(parkFromLeft);
                requestOpModeStop();
            }


        }
    }

    public int getPixels(RedPositionDetector pd) {
        int left = pd.getLeftValue();
        int right = pd.getRightValue();

        telemetry.addData("Left pixels: ", left);
        telemetry.addData("Right pixels: ", right);
        telemetry.update();

        if (pd.getTotalPixelValues() < 20) {
            telemetry.addData("Found: ", "none");
            telemetry.update();
            return 0; // none found
        }

        if (left > right) {
            telemetry.addData("Found: ", "left");
            telemetry.update();
            return 1; // found on left side
        }

        telemetry.addData("Found: ", "right");
        telemetry.update();
        return 2; // found on right side
    }




}

