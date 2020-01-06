package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.techfire225.webapp.FireLog;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PortMap;

public class Intake {
    TalonSRX[] indexer_motors = new TalonSRX[] {
        new TalonSRX(PortMap.INDEXER_CAN[0]),
        new TalonSRX(PortMap.INDEXER_CAN[1]),
    };

    CANSparkMax intake_motor = new CANSparkMax(PortMap.INTAKE_CAN, MotorType.kBrushless);

    CANSparkMax feeder_motor = new CANSparkMax(PortMap.FEED_CAN, MotorType.kBrushless);
    Solenoid intake_solenoid = new Solenoid(PortMap.INTAKE_SOLENOID);

    TimeOfFlight index_sensor = new TimeOfFlight(PortMap.TOF_FIRST_CAN);

    Constants constants = Constants.getConstants();

    public Intake() {
    }

    public void update() {
        FireLog.log("indexer_tof", index_sensor.getRange());
    }

    public void set(boolean running, boolean reverse) {
        double feedSpeed = 0;
        double indexSpeed = 0;
        double intakeSpeed = 0;
        if ( running ) {
            feedSpeed = reverse ? constants.feeder_speed : -constants.feeder_speed;
            indexSpeed = reverse ? constants.indexer_speed : -constants.indexer_speed;
            intakeSpeed = reverse ? constants.intake_speed : -constants.intake_speed;
        }

        for ( TalonSRX motor : indexer_motors ) {
            motor.set(ControlMode.PercentOutput, indexSpeed);
        }
        feeder_motor.set(feedSpeed);
        intake_motor.set(intakeSpeed);
        intake_solenoid.set(running && !reverse);

        //SmartDashboard.putString("MotorType", indexer_motors[0].getMotorType().toString());
    }
}