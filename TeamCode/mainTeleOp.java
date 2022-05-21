package org.firstinspires.ftc.teamcode;

@TeleOp(name = "mainTeleOp")
public class mainTeleOp extends CommandOpMode {

    private Motor frontLeft, frontRight, backLeft, backRight;

    private DriveSubsystem driveS;
    private MecanumDriveSubsystem mecanumDriveS;

    private DriveCommand drive_Com;

    private GamepadEx gPad1;

    public void initialize() {

        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");

        gPad1 = new GamepadEx(gamepad1);

        mecanumDriveS = new MecanumDriveSubsystem(new SampleMecanumDrive(hardwareMap), false);

        frontLeft.motor.setDirection(DcMotor.Direction.FORWARD);
        frontRight.motor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        driveS = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, revIMU);
        drive_Com = new DriveCommand(driveS, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, DRIVE_MULT);


        register(driveS);
        driveS.setDefaultCommand(drive_Com);
    }
}