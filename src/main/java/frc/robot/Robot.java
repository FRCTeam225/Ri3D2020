package frc.robot;

import org.techfire225.webapp.Webserver;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  Compressor compressor = new Compressor();
  public static Drivetrain drivetrain;
  public static Shooter shooter;
  public static Intake intake;
  public static Climber climber;
  public static ColorSpinner colorSpin;

  Joystick driver = new Joystick(0);
  Joystick operator = new Joystick(1);
  JoystickButton triggerColorwheel = new JoystickButton(operator, OI.START);

  Webserver webserver;

  @Override
  public void robotInit() {
    compressor.start();

    drivetrain = new Drivetrain();
    shooter = new Shooter();
    intake = new Intake();
    colorSpin = new ColorSpinner();
    climber = new Climber();

//    colorSpin.Init();
//    colorMatcher.Init();
    
    try {
      webserver = new Webserver();
    }
    catch ( Exception ex ) {
      System.err.println("Failed to start webserver");
    }
  }

  @Override
  public void robotPeriodic() {
    drivetrain.update();
    colorSpin.update();
    intake.update();
    shooter.update();
    climber.update();
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
    if ( driver.getRawButton(OI.RB) ) {
      drivetrain.vision_align();
      shooter.set(Constants.getConstants().debugShooterSet);
    }
    else
      drivetrain.cheesydrive(driver);
    
    if ( operator.getRawButton(OI.A) ) {
      intake.autointake();
    }
    else if ( operator.getRawButton(OI.B) ) {
      intake.eject();
    }
    else if ( driver.getRawButton(OI.LB) ) {
      intake.shoot();
    }
    else {
      intake.disable();
    }
    
    if ( operator.getRawButton(OI.X) ) {
      shooter.stop();
      colorSpin.stop();
    }
    if ( operator.getRawButton(OI.LB) ) {
      colorSpin.run_encoder();
      System.out.println("Start rotation");
    }
    if ( operator.getRawButton(OI.RB) ) {
      System.out.println("Start color find colorwheel");
      colorSpin.find_color();
    }

    climber.set(-operator.getRawAxis(OI.LY));
    colorSpin.setHeight(operator.getRawButton(OI.START));
    //colorSpin.SpinMotor(operator.getRawAxis(OI.LY)); 
    //colorSpin.Periodic();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
