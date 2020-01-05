package org.techfire225;

public class CheesyMath {
    double turn_gain = .6;
    double skim_gain = 0;
    double turn_velocity_multiplier_gain = 1.1;
    
    double wheelNonLinearity = 0.5;
    double leftOutput = 0;
    double rightOutput = 0;
    double controllerDeadband = 0.06;
    
    public CheesyMath() {
        
    }

    public void update(double throttle, double turnInput) {
        
        turnInput = Math.sin((Math.PI/2)*wheelNonLinearity*turnInput)/Math.sin((Math.PI/2)*wheelNonLinearity);
        turnInput = Math.sin((Math.PI/2)*wheelNonLinearity*turnInput)/Math.sin((Math.PI/2)*wheelNonLinearity);
        turnInput = Math.sin((Math.PI/2)*wheelNonLinearity*turnInput)/Math.sin((Math.PI/2)*wheelNonLinearity);
        
        if ( Math.abs(turnInput) < controllerDeadband)
            turnInput = 0;
        
        double turn = 0;
        if ( Math.abs(throttle) < controllerDeadband ) {
            throttle = 0;
            turn = turnInput*turn_gain;
        }
        else
            turn = (turnInput*turn_gain)*Math.abs(turn_velocity_multiplier_gain*throttle);
        
        double left_orig = throttle-turn;
        double right_orig = throttle+turn;

        leftOutput = left_orig+skim(right_orig);
        rightOutput = right_orig+skim(left_orig);

    }

    public double getLeftOutput() {
        return leftOutput;
    }

    public double getRightOutput() {
        return rightOutput;
    }
    
    private double skim(double v) {
        if (v > 1.0)
            return -((v - 1.0) * skim_gain);
        else if (v < -1.0)
            return -((v + 1.0) * skim_gain);
        return 0;
    }
}