package frc.robot.steps;

import org.auto.lib.AutonomousStep;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;
import frc.robot.Robot;

public class SpinColorWheel implements AutonomousStep {
    private Color lastValidatedColor = Color.kBlack;
    private Color[] colorReadings;
    private byte panelsSeen;

    @Override
    public void init() {
        colorReadings = new Color[3];
        colorReadings[0] = Color.kBlack;
        colorReadings[1] = Color.kBlack;
        colorReadings[2] = Color.kBlack;
        panelsSeen = 0;
    }

    @Override
    public boolean run() {
        boolean readConsistent = false;

        //run the motor to spin the wheel
        Robot.colorSpin.SpinMotor(Constants.getConstants().colorwheel_fast);

        //shift buffer with last 3 results
        colorReadings[2] = colorReadings[1];
        colorReadings[1] = colorReadings[0];
        //colorReadings[0] = Robot.colorMatcher.getResult().color;

        //see if we have had consistent color readings 3 samples in a row
        if ((colorReadings[0] == colorReadings[1]) && (colorReadings[1] == colorReadings[2])) {
            readConsistent = true;
        }

        //if we meet all the conditions, increment the panel count and reset conditions
        if ((readConsistent == true) && (lastValidatedColor != colorReadings[0]))
        {
            panelsSeen++;
            lastValidatedColor = colorReadings[0];
        }

        if(panelsSeen >= Constants.getConstants().panelsToSpin) {
            //we have seen all the panels, stop
            return false;
        } else {
            //keep running
            return true;
        }
    }

    @Override
    public void end() {
        //stop the motor because we don't want to go further
        Robot.colorSpin.SpinMotor(0);
    }

}