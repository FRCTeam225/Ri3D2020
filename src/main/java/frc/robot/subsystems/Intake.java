package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PortMap;

public class Intake {
//    TalonSRX[] indexer_motors = new TalonSRX[] {
//        new TalonSRX(PortMap.INDEXER_CAN[0]),
//        new TalonSRX(PortMap.INDEXER_CAN[1]),
//    };

    CANSparkMax feeder_motor = new CANSparkMax(PortMap.FEED_CAN, MotorType.kBrushless);
    //CANSparkMax intake_motor = new CANSparkMax(PortMap.INTAKE_CAN, MotorType.kBrushless);
    Solenoid intake_solenoid = new Solenoid(PortMap.INTAKE_SOLENOID);

    Constants constants = Constants.getConstants();

    public Intake() {
    }

    public void set(boolean running, boolean reverse) {
        double speed = 0;
        if ( running )
            speed = reverse ? -constants.indexer_speed : constants.indexer_speed;

//        for ( TalonSRX motor : indexer_motors ) {
//            motor.set(ControlMode.PercentOutput, speed);
//        }
        feeder_motor.set(-speed);
        intake_solenoid.set(true);

        //SmartDashboard.putString("MotorType", indexer_motors[0].getMotorType().toString());
    }
}