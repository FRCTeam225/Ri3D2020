package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.techfire225.*;

import frc.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {

    CANSparkMax intake = new CANSparkMax(PortMap.intakePort, MotorType.kBrushless);

    Solenoid flip = new Solenoid(PortMap.flipPort);

    public void set(double speed) {
       intake.set(speed);
    }

    public void setFlip(boolean down){
        flip.set(down);
    }
}