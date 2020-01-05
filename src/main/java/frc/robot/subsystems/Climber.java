package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.techfire225.*;

import frc.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Counter;

public class Climber {

    CANSparkMax[] climberMotors = {
        new CANSparkMax(PortMap.climberPort[0], MotorType.kBrushless),
        new CANSparkMax(PortMap.climberPort[1], MotorType.kBrushless)};

    CANSparkMax balance = new CANSparkMax(PortMap.balancePort, MotorType.kBrushless);

    Counter hexEnc = new Counter(PortMap.balanceEnc);

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
