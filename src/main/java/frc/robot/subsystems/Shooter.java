package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.PortMap;

public class Shooter {
    CANSparkMax[] shooterMotors = new CANSparkMax[] {
        new CANSparkMax(PortMap.SHOOTER_CAN[0], MotorType.kBrushless),
        new CANSparkMax(PortMap.SHOOTER_CAN[1], MotorType.kBrushless),
    };

    public Shooter() {
        for (CANSparkMax spark : shooterMotors) {
            spark.setIdleMode(CANSparkMax.IdleMode.kCoast);
        }

    }
}