package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Indexer {

    CANSparkMax[] indexer_motors = new CANSparkMax[] {
        new CANSparkMax(0, MotorType.kBrushless),
        new CANSparkMax(0, MotorType.kBrushless),
    };

    CANSparkMax feeder_motor = new CANSparkMax(0, MotorType.kBrushless);

    Constants constants = Constants.getConstants();

    public Indexer() {}

    public void set(boolean running) {
        for ( CANSparkMax motor : indexer_motors ) {
            motor.set(running ? constants.indexer_speed : 0);
        }
        feeder_motor.set(running ? constants.feeder_speed : 0);
    }
}