package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.auto.lib.AutonomousSequence;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.steps.SpinColorWheel;
import edu.wpi.first.wpilibj.DriverStation;

public class ColorSpinner {
    CANSparkMax drive = new CANSparkMax(18, MotorType.kBrushless);
    Solenoid drop = new Solenoid(8);
    AutonomousSequence seq = new AutonomousSequence();

    public void Periodic() {
        seq.update();
    }
    public void SpinMotor(double speed) {
        drive.set(speed);
    }

    public void StartRotation() {
        seq.addStep(new SpinColorWheel());
    }

    public void StartColorFind() {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        Color target;

        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
            case 'B':
                target = ColorMatcher.kBlueTarget;
                break;
            case 'G':
                target = ColorMatcher.kGreenTarget;
                break;
            case 'R':
                target = ColorMatcher.kRedTarget;
                break;
            case 'Y':
                target = ColorMatcher.kYellowTarget;
                break;
            default:
                target = null;
                break;
            }
        } else {
            target = null;
        }

        if(target == null) {
            System.out.println("No color was provided to spin to.");
        } else {
            seq.addStep(new SpinColorWheel());
        }
    }
}