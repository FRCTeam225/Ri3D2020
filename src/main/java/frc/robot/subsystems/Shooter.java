package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.PortMap;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.techfire225.webapp.FireLog;

public class Shooter {
  CANSparkMax[] shooter_motors = new CANSparkMax[] {
    new CANSparkMax(PortMap.SHOOTER_CAN[0], MotorType.kBrushless),
    new CANSparkMax(PortMap.SHOOTER_CAN[1], MotorType.kBrushless),
  };

  CANEncoder encoder;
  CANPIDController controller;

  Constants constants = Constants.getConstants();

  double setpoint = 0;

  public Shooter() {
      shooter_motors[1].follow(shooter_motors[0], true);
      shooter_motors[0].set(0);

      for ( CANSparkMax motor : shooter_motors ) {
          motor.setIdleMode(IdleMode.kCoast);
      }

      encoder = shooter_motors[0].getEncoder();
      controller = shooter_motors[0].getPIDController();

      stop();
  }

  public void set(double setpoint) {
    FireLog.log("shooterSetpoint", Math.abs(setpoint));
    controller.setReference(setpoint, ControlType.kVelocity);
  }

  public void stop() {
    controller.setReference(0, ControlType.kDutyCycle);
  }

  public void updateConstants() {
    controller.setP(constants.shooterP);
    controller.setP(constants.shooterI);
    controller.setP(constants.shooterD);
    controller.setFF(constants.shooterF);
  }
}