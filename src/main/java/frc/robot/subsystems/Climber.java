package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.techfire225.*;
import org.techfire225.webapp.FireLog;

import frc.robot.Constants;
import frc.robot.PortMap;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber {

    CANSparkMax[] climberMotors = {
        new CANSparkMax(PortMap.CLIMBER_CAN[0], MotorType.kBrushless),
        new CANSparkMax(PortMap.CLIMBER_CAN[1], MotorType.kBrushless)
    };

    Solenoid lockSolenoid = new Solenoid(PortMap.CLIMB_SOLENOID);

    Constants constants = Constants.getConstants();

    public void set(double speed) {
        if ( Math.abs(speed) < 0.08 )
            speed = 0;

        if ( speed < 0 && getPos() <= 10 ) {
            speed = 0;
        }
        else if ( speed > 0 && getPos() >= constants.climber_limit ) {
            speed = 0;
        }

        lockSolenoid.set(Math.abs(speed) > 0);

        climberMotors[0].set(speed);
        climberMotors[1].set(-speed);
    }

    public double getPos() {
        return climberMotors[0].getEncoder().getPosition();
    }

    public void update() {
        FireLog.log("climberPos", getPos());
    }
}
