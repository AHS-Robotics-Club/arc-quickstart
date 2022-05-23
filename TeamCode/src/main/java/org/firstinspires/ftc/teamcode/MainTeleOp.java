package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.command.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

@TeleOp(name = "MainTeleOp")
public class MainTeleOp extends CommandOpMode {

    private Motor frontLeft, frontRight, backLeft, backRight; //Drivetrain Motors

    private DriveSubsystem driveS; //Drive train subsystem

    private DriveCommand drive_Com; //Drive command

    private GamepadEx gPad1;
    private RevIMU revIMU;

    @Override
    public void initialize() {
        //Drivetrain
        frontLeft = new Motor(hardwareMap,"frontLeft");
        frontRight = new Motor(hardwareMap,"frontRight");
        backLeft = new Motor(hardwareMap,"backLeft");
        backRight = new Motor(hardwareMap,"backRight");


        //Extra Setup
        revIMU = new RevIMU(hardwareMap);
        revIMU.init();

        gPad1 = new GamepadEx(gamepad1);

        driveS = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);
        drive_Com = new DriveCommand(driveS, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX);


        // Sets default command for drivetrain
        register(driveS);
        driveS.setDefaultCommand(drive_Com);
    }
}