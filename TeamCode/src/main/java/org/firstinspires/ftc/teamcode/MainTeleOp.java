package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

// P.S. Collapsable regions: if you see a comment that reads //region [name], click the '-' button to the left to collapse it.

//TODO make this a better comment: Lets the android robot control app know this is a TeleOp.
@TeleOp(name = "Main Tele Op")
public class MainTeleOp extends CommandOpMode {
    /*
    $$$$$$$\                      $$\                               $$\     $$\
    $$  __$$\                     $$ |                              $$ |    \__|
    $$ |  $$ | $$$$$$\   $$$$$$$\ $$ | $$$$$$\   $$$$$$\  $$$$$$\ $$$$$$\   $$\  $$$$$$\  $$$$$$$\   $$$$$$$\
    $$ |  $$ |$$  __$$\ $$  _____|$$ | \____$$\ $$  __$$\ \____$$\\_$$  _|  $$ |$$  __$$\ $$  __$$\ $$  _____|
    $$ |  $$ |$$$$$$$$ |$$ /      $$ | $$$$$$$ |$$ |  \__|$$$$$$$ | $$ |    $$ |$$ /  $$ |$$ |  $$ |\$$$$$$\
    $$ |  $$ |$$   ____|$$ |      $$ |$$  __$$ |$$ |     $$  __$$ | $$ |$$\ $$ |$$ |  $$ |$$ |  $$ | \____$$\
    $$$$$$$  |\$$$$$$$\ \$$$$$$$\ $$ |\$$$$$$$ |$$ |     \$$$$$$$ | \$$$$  |$$ |\$$$$$$  |$$ |  $$ |$$$$$$$  |
    \_______/  \_______| \_______|\__| \_______|\__|      \_______|  \____/ \__| \______/ \__|  \__|\_______/
    */

    //region Hardware
    // Motors
    private Motor frontLeft, frontRight, backLeft, backRight; // Driving motors

    // ⬇ Add subsystems here ⬇ //
    private DriveSubsystem driveSubsystem; // Adds functionality for the robot to move

    // ⬇ Add commands here ⬇ //
    private DriveCommand driveCommand; // TODO: Make comment better: Adds commands to act upon functionality, given by subsystem

    // Feedback and Input
    private GamepadEx gPad1;
    private FtcDashboard ftcDashboard; // Provides telemetry; allows you to monitor robots during operation
    private RevIMU imu; // Inertial measurement unit: Can measure movement and rotation on all 3 Axes
    private ElapsedTime time;

    //endregion

    //region Constants
    private final double DRIVE_MULT = 1.0; // How fast you want the motor to spin normally
    private final double SLOW_MULT = 0.5; // How fast you want the motor to spin when slow button is pressed

    //endregion

    @Override
    public void initialize() {
        /*
         /$$$$$$           /$$   /$$     /$$           /$$ /$$                       /$$     /$$
        |_  $$_/          |__/  | $$    |__/          | $$|__/                      | $$    |__/
          | $$   /$$$$$$$  /$$ /$$$$$$   /$$  /$$$$$$ | $$ /$$ /$$$$$$$$  /$$$$$$  /$$$$$$   /$$  /$$$$$$  /$$$$$$$   /$$$$$$$
          | $$  | $$__  $$| $$|_  $$_/  | $$ |____  $$| $$| $$|____ /$$/ |____  $$|_  $$_/  | $$ /$$__  $$| $$__  $$ /$$_____/
          | $$  | $$  \ $$| $$  | $$    | $$  /$$$$$$$| $$| $$   /$$$$/   /$$$$$$$  | $$    | $$| $$  \ $$| $$  \ $$|  $$$$$$
          | $$  | $$  | $$| $$  | $$ /$$| $$ /$$__  $$| $$| $$  /$$__/   /$$__  $$  | $$ /$$| $$| $$  | $$| $$  | $$ \____  $$
         /$$$$$$| $$  | $$| $$  |  $$$$/| $$|  $$$$$$$| $$| $$ /$$$$$$$$|  $$$$$$$  |  $$$$/| $$|  $$$$$$/| $$  | $$ /$$$$$$$/
        |______/|__/  |__/|__/   \___/  |__/ \_______/|__/|__/|________/ \_______/   \___/  |__/ \______/ |__/  |__/|_______/
        */

        // ⬇ Initialize Motors here ⬇ //
        // Drivetrain motors
        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");

        // Configuring Motors //
        frontLeft.motor.setDirection(DcMotor.Direction.FORWARD);
        frontRight.motor.setDirection(DcMotor.Direction.FORWARD);
        backLeft.motor.setDirection(DcMotor.Direction.FORWARD);
        backRight.motor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        frontLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // ⬇ Initialize Telemetry and Input ⬇ //
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        imu = new RevIMU(hardwareMap);
        imu.init();
        gPad1 = new GamepadEx(gamepad1);
        ftcDashboard = FtcDashboard.getInstance();
        time = new ElapsedTime();

        // ⬇ Initialize Subsystems and Commands ⬇ //
        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, imu);

        driveCommand = new DriveCommand(driveSubsystem, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, DRIVE_MULT);

        /*
        ▒█▀▀█ █▀▀█ █▀▄▀█ █▀▀ █▀▀█ █▀▀█ █▀▀▄
        ▒█░▄▄ █▄▄█ █░▀░█ █▀▀ █░░█ █▄▄█ █░░█
        ▒█▄▄█ ▀░░▀ ▀░░░▀ ▀▀▀ █▀▀▀ ▀░░▀ ▀▀▀░
         */
        //  ⬇ Add stuff for gamepad buttons here ⬇ //
        //TODO I don't need to write anything here for now right?

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }
}
