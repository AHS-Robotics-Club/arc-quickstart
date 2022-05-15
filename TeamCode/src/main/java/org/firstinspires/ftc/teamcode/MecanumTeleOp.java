package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.MecanumCommand;

import Subsystems.MecanumSubsystem;

@TeleOp(name = "TeleOp")
public class MecanumTeleOp extends CommandOpMode {
    private Motor fL, fR, bL, bR;

    private MecanumSubsystem driveSubsystem;

    private MecanumCommand driveCommand;

    private GamepadEx gamepad;

    @Override
    public void initialize() {
        fL = new Motor(hardwareMap, "fL");
        fR = new Motor(hardwareMap, "fR");
        bL = new Motor(hardwareMap, "bL");
        bR = new Motor(hardwareMap, "bR");

        gamepad = new GamepadEx(gamepad1);

        driveSubsystem = new MecanumSubsystem(fL, fR, bL, bR);
        driveCommand = new MecanumCommand(driveSubsystem, gamepad::getLeftX, gamepad::getLeftY, gamepad::getRightX);

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }
}
