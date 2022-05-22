package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import org.firstinspires.ftc.teamcode.commands.rr.MecanumDriveCommand;

public class DriveSubsystem extends SubsystemBase {

    private final MecanumDrive mecanumDrive;
    private final Motor frontLeft;
    private final Motor backLeft;
    private final Motor backRight;

    public DriveSubsystem(Motor fL, Motor fR, Motor bL, Motor bR) {
        frontLeft = fL;
        backLeft = bL;
        backRight = bR;

        mecanumDrive = new MecanumDrive(frontLeft, fR, backLeft, backRight);
    }
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        mecanumDrive.driveRobotCentric(-strafeSpeed, -forwardSpeed, -turnSpeed);
    }
}
