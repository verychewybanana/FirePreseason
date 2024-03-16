package org.firstinspires.ftc.teamcode.RRAutons;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FireHardwareMap;
import org.firstinspires.ftc.teamcode.RRAutons.opencv.BluePositionDetector;
import org.firstinspires.ftc.teamcode.RRAutons.opencv.RedPositionDetector;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="roadRunnerBlueBackboard", group="Auton")
public class roadRunnerBlueBackboard extends LinearOpMode {


    FireHardwareMap robot = null;
    boolean recognized;


    @Override
    public void runOpMode() {
        BluePositionDetector pd = new BluePositionDetector(hardwareMap, telemetry);


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        robot = new FireHardwareMap(this.hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPose);

        TrajectorySequence navigateLeft = drive.trajectorySequenceBuilder(startPose)
                .strafeLeft(12)
                .forward(10.0)
//                .lineToLinearHeading(new Pose2d(26, 15, Math.toRadians(x)))
                .build();

        TrajectorySequence parkFromLeft = drive.trajectorySequenceBuilder(navigateLeft.end())
                .strafeLeft(20.0)
                .turn(Math.toRadians(-90))
                .build();

        TrajectorySequence navigateMiddle = drive.trajectorySequenceBuilder(navigateLeft.end())
                .waitSeconds(0.2)
                .strafeRight(4.0)
                .forward(10.0)
                .build();

        TrajectorySequence parkFromMiddle = drive.trajectorySequenceBuilder(navigateMiddle.end())
                .waitSeconds(0.2)
                .strafeLeft(25.0)
                .turn(Math.toRadians(-90))
                .build();

        TrajectorySequence navigateRight = drive.trajectorySequenceBuilder(navigateMiddle.end())
                .forward(6.0)
                .turn(Math.toRadians(-90))
                .build();

        TrajectorySequence parkFromRight = drive.trajectorySequenceBuilder(navigateRight.end())
                .waitSeconds(0.2)
                .back(25.0)
                .build();

        waitForStart();

        pd.startStreaming();

        if (opModeIsActive()) {
            sleep(200);

            drive.followTrajectorySequence(navigateLeft);

            sleep(1500);

            recognized = false;
            // camera queries if there are more than 50 white pixels then drop
//            recognized = (pd.getTotalPixelValues() > 50) ? true : false;
            if (recognized) {
                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);
                drive.followTrajectorySequence(parkFromLeft);
                requestOpModeStop();
            }

            drive.followTrajectorySequence(navigateMiddle);

            recognized = false;
//            recognized = () ? true : false;
            if (recognized) {
                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);
                drive.followTrajectorySequence(parkFromMiddle);
                requestOpModeStop();
            }

            drive.followTrajectorySequence(navigateRight);

            recognized = true;
//            recognized = () ? true : false;
            if (recognized) {
                robot.intakeMotor.setPower(-0.7);
                sleep(1500);
                robot.intakeMotor.setPower(0.0);
                sleep(500);
                drive.followTrajectorySequence(parkFromRight);
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

