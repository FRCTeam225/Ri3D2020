package frc.robot;

import org.techfire225.webapp.Webserver;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  Compressor compressor = new Compressor();
  public static Drivetrain drivetrain;
  public static Shooter shooter;
  public static Intake intake;
  public static ColorSpinner colorSpin;
  public static ColorMatcher colorMatcher = new ColorMatcher();

  Joystick driver = new Joystick(0);
  Joystick operator = new Joystick(1);

  Webserver webserver;

  @Override
  public void robotInit() {
    compressor.start();
    colorMatcher.Init();

    drivetrain = new Drivetrain();
    shooter = new Shooter();
    intake = new Intake();

    try {
      webserver = new Webserver();
    }
    catch ( Exception ex ) {
      System.err.println("Failed to start webserver");
    }
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

  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drivetrain.cheesydrive(driver);
    intake.set(operator.getRawButton(OI.A) || operator.getRawButton(OI.B), operator.getRawButton(OI.B));
    
    if ( operator.getRawButton(OI.Y) )
      shooter.set(Constants.getConstants().debugShooterSet);
    if ( operator.getRawButton(OI.X) )
      shooter.stop();
    if ( operator.getRawButton(OI.LB) )
      colorSpin.StartRotation();
    if ( operator.getRawButton(OI.RB) )
      colorSpin.StartColorFind();

      colorSpin.Periodic();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
