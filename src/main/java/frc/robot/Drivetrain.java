package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.robot.CheesyMath;
import frc.robot.OI;

import edu.wpi.first.wpilibj.Joystick;

public class Drivetrain {

    CheesyMath cheesydrive = new CheesyMath();

    CANSparkMax[] left = new CANSparkMax[] {
        new CANSparkMax(1, MotorType.kBrushless),
        new CANSparkMax(2, MotorType.kBrushless),
        new CANSparkMax(3, MotorType.kBrushless),
    };

    CANSparkMax[] right = new CANSparkMax[] {
        new CANSparkMax(4, MotorType.kBrushless),
        new CANSparkMax(5, MotorType.kBrushless),
        new CANSparkMax(6, MotorType.kBrushless),
    };

    public void cheesydrive(Joystick driver) {
        cheesydrive.update(driver.getRawAxis(OI.LY), -driver.getRawAxis(OI.RX));

        set(cheesydrive.getLeftOutput(), cheesydrive.getRightOutput());
    }

    public void set(double left_speed, double right_speed) {
        for ( CANSparkMax m_left : left )
            m_left.set(-left_speed);
        for ( CANSparkMax m_right : right )
            m_right.set(right_speed);
    }
}