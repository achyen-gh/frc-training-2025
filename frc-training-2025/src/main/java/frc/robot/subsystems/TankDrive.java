// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.TankDriveConstants;

/**
 * Subsystem that represents the tank drive of the robot.
 * 
 * Encapsulates the various motors involved and exposes drive methods to rest of
 * robot.
 */
public class TankDrive extends SubsystemBase {

  /** The left primary motor controller */
  private TalonSRX m_leftPrimaryMotor = new TalonSRX(TankDriveConstants.kLeftPrimaryMotorID);

  /** The left secondary motor controller */
  private TalonSRX m_rightPrimaryMotor = new TalonSRX(TankDriveConstants.kLeftSecondaryMotorID);

  /** The right primary motor controller */
  private VictorSPX m_leftSecondaryMotor = new VictorSPX(TankDriveConstants.kRightPrimaryMotorID);

  /** The right secondary motor controller */
  private VictorSPX m_rightSecondaryMotor = new VictorSPX(TankDriveConstants.kRightSecondaryMotorID);

  /** Creates a new TankDrive. Setup the motors to follow/inverse as needed */
  public TankDrive() {
    // Set the Talons to Brake mode
    m_leftPrimaryMotor.setNeutralMode(NeutralMode.Brake);
    m_rightPrimaryMotor.setNeutralMode(NeutralMode.Brake);

    // Set one motor on left and right each to be followers
    m_leftSecondaryMotor.follow(m_leftPrimaryMotor);
    m_rightSecondaryMotor.follow(m_rightPrimaryMotor);

    // Invert the right motor controllers
    m_rightPrimaryMotor.setInverted(true);
    m_rightSecondaryMotor.setInverted(true);
  }

  /** Set the left-side speed of the tank drive. */
  public void setLeftSpeed(double speed) {
    m_leftPrimaryMotor.set(TalonSRXControlMode.PercentOutput, speed);
  }

  /** Set the right-side speed of the tank drive. */
  public void setRightSpeed(double speed) {
    m_rightPrimaryMotor.set(TalonSRXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // Log speeds for debug
    SmartDashboard.putNumber("TankDrive left speed", m_leftPrimaryMotor.getMotorOutputPercent());
    SmartDashboard.putNumber("TankDrive right speed", m_rightPrimaryMotor.getMotorOutputPercent());
  }
}
