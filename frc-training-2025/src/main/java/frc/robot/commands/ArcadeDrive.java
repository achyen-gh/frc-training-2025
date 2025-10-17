// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.IOConstants;
import frc.robot.constants.TankDriveConstants;
import frc.robot.subsystems.TankDrive;

/**
 * Command to read from the joystick and move the tank drive.
 */
public class ArcadeDrive extends Command {

  /** The tank drive to move. */
  private TankDrive m_tankDrive;

  /** The joystick to read inputs from. */
  private Joystick m_joystick;

  /** The intended translational speed. */
  private double m_speed;

  /** The intended rotational speed. */
  private double m_turn;

  /** The left-side drive speed. */
  private double m_left;

  /** The right-side drive speed. */
  private double m_right;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(TankDrive tankDrive, Joystick joystick) {
    m_tankDrive = tankDrive;
    m_joystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(tankDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Read joystick for translation and rotation speed
    m_speed = m_joystick.getRawAxis(IOConstants.kSpeedControlAxis);
    m_turn = m_joystick.getRawAxis(IOConstants.kRotationControlAxis);

    // Convert translation/rotation to left/right speeds.
    // Positive turn is read as turning to right.
    // Scale with multiplier.
    m_left = (m_speed + m_turn) * TankDriveConstants.kAxisSpeedMultiplier;
    m_right = (m_speed - m_turn) * TankDriveConstants.kAxisSpeedMultiplier;

    // Set drive speeds
    m_tankDrive.setLeftSpeed(m_left);
    m_tankDrive.setRightSpeed(m_right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Set speeds to 0 for safety
    m_tankDrive.setLeftSpeed(0);
    m_tankDrive.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
