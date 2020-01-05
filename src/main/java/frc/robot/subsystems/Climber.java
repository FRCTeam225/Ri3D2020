package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.techfire225.*;

import frc.robot.OI;
import frc.robot.PortMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Counter;

public class Climber {

    CANSparkMax[] climberMotors = {
        new CANSparkMax(PortMap.CLIMBER_CAN[0], MotorType.kBrushless),
        new CANSparkMax(PortMap.CLIMBER_CAN[1], MotorType.kBrushless)
    };

    CANSparkMax balance = new CANSparkMax(PortMap.BALANCE_CAN, MotorType.kBrushless);

    Counter hexEnc = new Counter(PortMap.BALANCE_DIO);

    public void set(double speed) {
        for(CANSparkMax climber : climberMotors)
            climber.set(speed);
    }

    public void setBalance(double speed){
        balance.set(speed);
    }

    public double getPos(){
        return climberMotors[0].getEncoder().getPosition();
    }

    /*public double getBalPos(){
        return hexEnc.get();
    }*/

    
}
