package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import frc.robot.PortMap;
import frc.robot.Constants;

public class Shooter {
    private Constants constants;

    private CANSparkMax[] shooterMotors = new CANSparkMax[] {
        new CANSparkMax(PortMap.SHOOTER_CAN[0], MotorType.kBrushless),
        new CANSparkMax(PortMap.SHOOTER_CAN[1], MotorType.kBrushless),
    };

    private CANPIDController pidController;
    private CANEncoder encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

    public Shooter() {
        constants = Constants.getConstants();

        for (CANSparkMax spark : shooterMotors) {
            spark.setIdleMode(CANSparkMax.IdleMode.kCoast);
        }

        pidController = shooterMotors[0].getPIDController();
        encoder = shooterMotors[0].getEncoder();

        // PID coefficients
        kP = Constants.shooter_kP;
        kI = Constants.shooter_kI;
        kD = Constants.shooter_kD;
        kIz = Constants.shooter_kIz;
        kFF = Constants.shooter_kFF;
        kMaxOutput = Constants.shooter_kMaxOutput;
        kMinOutput = Constants.shooter_kMinOutput;
        maxRPM = Constants.shooter_maxRPM;


    }
}