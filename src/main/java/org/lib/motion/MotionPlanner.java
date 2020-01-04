package org.lib.motion;

import edu.wpi.first.wpilibj.Timer;

public class MotionPlanner {
    public static class MotionPoint {
        public double pos;
        public double vel;

        public MotionPoint(double pos, double vel) {
            this.pos = pos;
            this.vel = vel;
        }

        public String toString() {
            return "Point: "+this.pos+" "+this.vel;
        }
    }

    final double epsilon = 0.001;

    double currentPosition;
    double currentVelocity;
    double targetPosition;

    double maxVelocity, maxAcceleration;

    double lastTime = -1;


    public MotionPlanner(double maxVelocity, double maxAcceleration) {
        this.maxVelocity = maxVelocity;
        this.maxAcceleration = maxAcceleration;
    }

    public MotionPlanner() {
        this.maxVelocity = 0;
        this.maxAcceleration = 0;
    }

    public void updateConstraints(double maxVelocity, double maxAcceleration) {
        this.maxVelocity = maxVelocity;
        this.maxAcceleration = maxAcceleration;
    }

    public void setTarget(double target) {
        this.targetPosition = target;
    }

    public void resetState(double position) {
        this.currentPosition = position;
        this.lastTime = Timer.getFPGATimestamp();
    }

    public boolean finished() {
        return currentVelocity == 0;
    }

    public MotionPoint tick() {
        double now = Timer.getFPGATimestamp();
        double dt = now - lastTime;
        lastTime = now;

        if ( dt > 1 ) {
            return new MotionPoint(0, 0);
        }

        double remainingDistance = targetPosition - currentPosition;
        if ( Math.abs(remainingDistance) > epsilon ) {
            if ( Math.abs(currentVelocity) < maxVelocity )
                currentVelocity += maxAcceleration * dt * Math.signum(remainingDistance);

            if ( Math.abs(currentVelocity) > maxVelocity )
                currentVelocity = maxVelocity * Math.signum(remainingDistance);
            
            double maxAllowedVelocity = Math.sqrt(2.0 * maxAcceleration * Math.abs(remainingDistance));

            if ( Math.abs(currentVelocity) > maxAllowedVelocity )
                currentVelocity = maxAllowedVelocity * Math.signum(remainingDistance);
        }
        else
            currentVelocity = 0;

        
        double nextPositionStep = currentVelocity * dt;
        if ( Math.signum(remainingDistance) != Math.signum(remainingDistance + nextPositionStep) )
            currentPosition = targetPosition;
        else
            currentPosition += nextPositionStep;
        return new MotionPoint(currentPosition, currentVelocity);
    }
}