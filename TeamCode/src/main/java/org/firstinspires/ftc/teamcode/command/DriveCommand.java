package org.firstinspires.ftc.teamcode.command;

import com.acmerobotics.roadrunner.drive.Drive;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.rr.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem mecDrive;
    private final DoubleSupplier m_strafe, m_forward, m_turn;

    public DriveCommand(DriveSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn) {
        mecDrive = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        mecDrive.drive(m_strafe.getAsDouble(), m_forward.getAsDouble(), m_turn.getAsDouble());
    }
}
