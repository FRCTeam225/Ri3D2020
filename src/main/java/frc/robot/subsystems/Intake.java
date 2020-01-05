package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.robot.CheesyMath;
import org.robot.PortMap;

import frc.robot.OI;

import edu.wpi.first.wpilibj.Joystick;

public class Intake {

    CANSparkMax intake = new CANSparkMax(PortMap.intakePort, MotorType.kBrushless);


    public void set(double speed) {
       intake.set(speed);
    }
}