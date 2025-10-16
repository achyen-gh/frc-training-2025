// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ArcadeDrive;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.TankDrive;

/**
 * Instantiate subsystems/commands and bind as needed for robot operations.
 */
public class RobotContainer {

  /** The tank drive of the robot. */
  TankDrive m_tankDrive = new TankDrive();

  /** The joystick that controls the robot. */
  Joystick m_joystick = new Joystick(IOConstants.kJoystickPort);

  /** The command that reads joystick and moves robot. */
  ArcadeDrive m_arcadeDriveCommand = new ArcadeDrive(m_tankDrive, m_joystick);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_tankDrive.setDefaultCommand(m_arcadeDriveCommand);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
