package org.lib.motion;

import edu.wpi.first.wpilibj.Timer;

public class TFPID {
	
	double kP;
	double kI;
	double kD; 
	double kF;
	double setpoint;
	double error;
	double input;
	
	double lastT;
	double errSum;
	double prevError;
	
	boolean reset;

	double maxOut = 1;
	double minOut = -1;
	double maxI = 0;
	double minI = 0;
	
	public TFPID(double kP, double kI, double kD, double kF) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.kF = kF;
		
		if ( kI == 0 )
			maxI = 0;
		else
			maxI = 1/kI;
		minI = -maxI;
		
		reset();
	}
	
	public TFPID() {
		kP = kI = kD = kF = 0;
		maxI = minI = 0;
	}
	
    
	public void setIConstraints(double maxI, double minI) {
		this.maxI = maxI;
		this.minI = minI;
	}
	
	public void setPIDF(double kP, double kI, double kD, double kF) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.kF = kF;
		
		if ( kI == 0 )
			maxI = 0;
		else
			maxI = 1/kI;
		minI = -maxI;
	}
	
    public void setP(double kP) {
        this.kP = kP;
    }
    
    public void setI(double kI) {
        this.kI = kI;
    }
    
    public void setD(double kD) {
        this.kD = kD;
    }
	
	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}
	
	public double getError() {
		if ( reset )
			return Double.MAX_VALUE;
		return error;
	}
	
	public double getInput() {
		return input;
	}
	
	public void reset() {
		reset = true;
	}
	
	public double getSetpoint() {
		return setpoint;
	}
	
	public void setOutputConstraints(double max, double min){
		maxOut = max;
		minOut = min;
	}
	
	public double calculate(double input) {
		double now = Timer.getFPGATimestamp();
		double dt = now-lastT;
		lastT = now;
		return calculate(dt, input);
	}
	
	public double calculate(double dT, double input) {
		this.input = input;
		
		error = setpoint - input;
		
		errSum += error*dT;
		
		if ( errSum > maxI )
			errSum = maxI;
		else if ( errSum < minI )
			errSum = minI;

		double deltaPos = error-prevError;
		prevError = error;

		if ( reset ) {
			deltaPos = 0;
			lastT = Timer.getFPGATimestamp();
			prevError = 0;
			errSum = 0;
			reset = false;
		}
		
		double out = (kP*error) + (kI*errSum) + (kD*(deltaPos/dT)) + (kF*setpoint);
		
		if ( out > maxOut )
			out = maxOut;
		else if ( out < minOut )
			out = minOut;
		
		return out;
	}
}
