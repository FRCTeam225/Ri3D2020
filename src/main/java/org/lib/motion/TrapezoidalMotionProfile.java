package org.lib.motion;

public class TrapezoidalMotionProfile {	
	
	double target, maxVelocity, maxAcceleration;
	double tToMaxVel, dToMaxVel;
	double tToVf, dToVf;
	double tCruising, dCruising;
	
	double vi = 0;
	double vf = 0;
	
	boolean reverse = false;
	
	public TrapezoidalMotionProfile(double target, double maxVelocity, double maxAcceleration, double vi, double vf) {
		reverse = target < 0;

		this.target = target = Math.abs(target);
		this.maxVelocity = maxVelocity = Math.abs(maxVelocity);
		this.maxAcceleration = maxAcceleration = Math.abs(maxAcceleration);
		this.vi = vi = Math.abs(vi);
		this.vf = vf = Math.abs(vf);
		
	    tToMaxVel = (maxVelocity-vi)/maxAcceleration;
	    dToMaxVel = (vi*tToMaxVel) + 0.5*maxAcceleration*(tToMaxVel*tToMaxVel);

	    tToVf = (maxVelocity-vf)/maxAcceleration;
	    dToVf = (maxVelocity*tToVf) + 0.5*-maxAcceleration*(tToVf*tToVf);

	    dCruising = target-dToMaxVel-dToVf;
	    tCruising = dCruising / maxVelocity;

	    if ( Math.abs(target) - Math.abs(dToMaxVel) - Math.abs(dToVf) < 0 ) {
			if ( vi == 0 && vf == 0 ) {
			    // Make triangular, can't achieve maxVelocity
			    // Just accelerate for half of the distance, -accel for the other half
			    tToMaxVel = tToVf = Math.abs(Math.sqrt(2.0 * maxAcceleration * (target / 2.0)) / maxAcceleration);
			    tCruising = dCruising = 0;
			    dToMaxVel = dToVf = target / 2.0;
			    this.maxVelocity = maxAcceleration*tToMaxVel;
			}
			else {
			    // Assuming we go at -maxAccel, how far do we go if we get to vf
			    double sign = Math.signum(vf-vi);
			    double d = Math.abs((Math.pow(vf,2)-Math.pow(vi, 2))/(2*maxAcceleration*sign));
			    
			    // Since d <= target, we can go from vi to vf without
			    // going more than target
			    // Just to make things simpler, cruise at the bigger velocity
			    if ( d <= target ) {
			        this.maxVelocity = maxVelocity = Math.max(vi, vf);
			        dCruising = target-d;
			        tCruising = dCruising / maxVelocity;
			        
			        // Going from a small velocity to a bigger one
			        if ( sign > 0 ) {
			            tToMaxVel = (vf-vi)/maxAcceleration;
			            dToMaxVel = d;
			            
			            tToVf = 0;
			            dToVf = 0;
			        }
			        // Going from a big velocity to a smaller one
			        else {
			            tToMaxVel = 0;
			            dToMaxVel = 0;
			            
			            tToVf = (vi-vf)/maxAcceleration;
			            dToVf = d;
			        }
			    }
			    else {
			        /*
			         * This is a problem.
			         * We can't go from vi to vf without overshooting our distance
			         * while obeying maxAccel
			         * so we need to rescale our acceleration to allow for this
			         *    
			         * This should be avoided since the result may be unachievable by the robot,
			         * but it's better than sitting like a brick       
			         */  
			        // Need to go from vi to vf and travel target distance
			        // What acceleration do we need?
			        this.maxAcceleration = maxAcceleration = ((vf*vf)-(vi*vi))/(2.0*target);    
			        this.maxVelocity = maxVelocity = 0;
			        
			        // Going from a small velocity to a bigger one
			        if ( sign > 0 ) {
			            tToMaxVel = (vf-vi)/maxAcceleration;
			            dToMaxVel = target;
			            
			            tToVf = 0;
			            dToVf = 0;
			            
			            dCruising = 0;
			            tCruising = 0;
			        }
			        // Going from a big velocity to a smaller one
			        else {
			            tToMaxVel = 0;
			            dToMaxVel = 0;
			            
			            tToVf = (vi-vf)/maxAcceleration;
			            dToVf = target;
			            
			            dCruising = 0;
			            tCruising = 0;
			        }
			    }
			}
	    }
	}
	
	public TrapezoidalMotionProfile(double target, double maxVelocity, double maxAcceleration) {
		this(target, maxVelocity, maxAcceleration, 0, 0);
	}
	
    public static TrapezoidalMotionProfile triangularProfileInDuration(double t, double d) {
        t /= 2.0;
        double a = d/(t*t);
        double v = a*t;
        
        return new TrapezoidalMotionProfile(d, v, a, 0, 0);   
    }
    
    public static TrapezoidalMotionProfile triangularProfileInDuration(TrapezoidalMotionProfile matchThis, double d) {
        return triangularProfileInDuration(matchThis.getDuration(), d);
    }
    
	
	public ProfilePoint getAtTime(double t) {
		ProfilePoint point = new ProfilePoint();
		
	    if ( t > tToMaxVel+tToVf+tCruising ) {
	        t = tToMaxVel+tToVf+tCruising;
	    }
	    if ( t <= tToMaxVel && tToMaxVel != 0 ) {
	        point.pos = (vi*t) + (0.5) * maxAcceleration * (t*t);
	        point.vel = (maxAcceleration * t) + vi;
	        point.acc = maxAcceleration;
	    }
	    else if ( t < (tToMaxVel+tCruising) ) {
	        double i = t-tToMaxVel;
	        point.pos = dToMaxVel + (maxVelocity * i);
	        point.vel = maxVelocity;
	        point.acc = 0;
	    }
	    else if ( t <= (tToMaxVel+tToVf+tCruising) ) {
	        double i = t-tToMaxVel-tCruising;
	        point.pos = (dCruising + dToMaxVel) + (maxVelocity * i) + (0.5) * -maxAcceleration * (i*i);
	        point.vel = maxVelocity + ((-maxAcceleration) * i);
	        point.acc = -maxAcceleration;
	    }

	    if ( t == tToMaxVel+tToVf+tCruising ) {
	        point.pos = target;
	        point.vel = vf;
	        if ( vf == 0 )
	            point.acc = 0;
	        else
	            point.acc = -maxAcceleration;
	    }
	    
	    if ( reverse ) {
	    	point.pos = -point.pos;
	    	point.vel = -point.vel;
	    	point.acc = -point.acc;
	    }
		return point;
	}
	
	public double getDuration() {
		return tToMaxVel + tToVf + tCruising;
	}
}
