/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.ColorMatcher;
import frc.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot {
  Compressor compressor = new Compressor();
  Drivetrain drivetrain = new Drivetrain();
  Joystick driver = new Joystick(0);
  ColorMatcher colorMatcher = new ColorMatcher();

  @Override
  public void robotInit() {
    compressor.start();
    colorMatcher.Init();
  }

  @Override
  public void robotPeriodic() {
    colorMatcher.Periodic();
  }

  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drivetrain.cheesydrive(driver);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
