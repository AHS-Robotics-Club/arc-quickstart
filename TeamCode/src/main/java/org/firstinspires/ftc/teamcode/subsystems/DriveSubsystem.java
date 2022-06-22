package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class DriveSubsystem extends SubsystemBase {
    private MecanumDrive drive;
    private Motor frontLeft, frontRight, backLeft, backRight;
    private RevIMU revIMU;

    // Constructor takes in hardware that makes up the subsystem
    // You can think of constructors as initializing objects
    public DriveSubsystem(Motor fL, Motor fR, Motor bL, Motor bR, RevIMU imu){
        fL = frontLeft;
        fR = frontRight;
        bL = backLeft;
        bR = backRight;

        imu = revIMU;

        // Initialize Mecanum Drive object, takes in parameters for all motors
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    // This method runs the subsystem
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed){
        drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed, true); // Squared inputs allow for more precise movement
    }
}
