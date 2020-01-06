package frc.robot.steps;

import org.auto.lib.AutonomousStep;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;
import frc.robot.Robot;

public class FindColorWheelSlot implements AutonomousStep {
    private Color targetColor = Color.kBlack;
    private Color[] colorReadings;

    public FindColorWheelSlot(Color target) {
        targetColor = target;
    }

    @Override
    public void init() {
        // fill initial color buffer
        colorReadings = new Color[3];
        colorReadings[0] = Color.kBlack;
        colorReadings[1] = Color.kBlack;
        colorReadings[2] = Color.kBlack;
    }

    @Override
    public boolean run() {
        boolean readConsistent = false;

        // run the motor to spin the wheel
        Robot.colorSpin.SpinMotor(Constants.getConstants().colorwheel_slow);

        // shift buffer with last 3 results
        colorReadings[2] = colorReadings[1];
        colorReadings[1] = colorReadings[0];
        colorReadings[0] = Robot.colorMatcher.getResult().color;

        // see if we have had consistent color readings 3 samples in a row
        if ((colorReadings[0] == colorReadings[1]) && (colorReadings[1] == colorReadings[2])) {
            readConsistent = true;
        }

        // if we meet all the conditions, increment the panel count and reset conditions
        if ((readConsistent == true) && (targetColor != colorReadings[0])) {
            // we are going to stop after 3 color reading (60ms)
            return false;
        } else {
            // keep running
            return true;
        }
    }

    @Override
    public void end() {
        // stop the motor because we don't want to go further
        Robot.colorSpin.SpinMotor(0);
    }

}