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
    private final RevIMU revIMU;

    public DriveSubsystem(Motor fL, Motor fR, Motor bL, Motor bR, RevIMU imu) {
        frontLeft = fL;
        backLeft = bL;
        backRight = bR;

        revIMU = imu;

        mecanumDrive = new MecanumDrive(frontLeft, fR, backLeft, backRight);
    }
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        mecanumDrive.driveFieldCentric(-strafeSpeed, -forwardSpeed, -turnSpeed, revIMU.getHeading(), false);
    }
}
