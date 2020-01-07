package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.techfire225.CheesyMath;
import org.techfire225.webapp.FireLog;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.OI;

public class Drivetrain {
    Constants constants = Constants.getConstants();

    CheesyMath cheesydrive = new CheesyMath();
    NetworkTable vision_table = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry target_valid = vision_table.getEntry("tv");
    NetworkTableEntry target_offset = vision_table.getEntry("tx");

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

    public void update() {
        FireLog.log("visionError", target_offset.getDouble(0));
    }

    public void vision_align() {
        double output = 0;
       
        output = target_offset.getDouble(0) * constants.visionP;
        
        FireLog.log("visionOut", output);

        output *= constants.visionLimit;

        set(-output, output);

    }

    public void cheesydrive(Joystick driver) {
        cheesydrive.update(driver.getRawAxis(OI.LY), -driver.getRawAxis(OI.RX));

        set(cheesydrive.getLeftOutput(), cheesydrive.getRightOutput());
    }

    public void set(double left_speed, double right_speed) {
        for ( CANSparkMax m_left : left )
            m_left.set(-left_speed*0.5);
        for ( CANSparkMax m_right : right )
            m_right.set(right_speed*0.5);
    }
}