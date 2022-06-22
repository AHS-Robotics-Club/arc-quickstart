package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final DoubleSupplier m_strafe, m_forward, m_turn;
    private double multiplier;

    public DriveCommand(){
        m_driveSubsystem = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        multiplier = mult;

        addRequirements(subsystem);
    }
    public DriveCommand(DriveSubsystem subsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, double DRIVE_MULT){
        m_driveSubsystem = subsystem;
        m_strafe = strafe;
        m_forward = forward;
        m_turn = turn;
        multiplier = 1.0;

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        m_driveSubsystem.drive(m_strafe.getAsDouble() * 0.9 * multiplier,
                m_forward.getAsDouble() * 0.9 * multiplier,
                m_turn.getAsDouble() * 0.88 * multiplier);
    }
}
