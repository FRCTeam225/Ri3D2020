package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.PortMap;

import frc.robot.Constants;

public class Indexer {

    CANSparkMax[] indexer_motors = new CANSparkMax[] {
        new CANSparkMax(PortMap.INDEXER_CAN[0], MotorType.kBrushless),
        new CANSparkMax(PortMap.INDEXER_CAN[1], MotorType.kBrushless),
    };

    CANSparkMax feeder_motor = new CANSparkMax(PortMap.FEED_CAN, MotorType.kBrushless);

    Constants constants = Constants.getConstants();

    public Indexer() {}

    public void set(boolean running) {
        for ( CANSparkMax motor : indexer_motors ) {
            motor.set(running ? constants.indexer_speed : 0);
        }
        feeder_motor.set(running ? constants.feeder_speed : 0);
    }
}