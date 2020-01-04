package org.lib.motion;

public class ProfilePoint {
	
	public double pos = 0;
	public double vel = 0;
	public double acc = 0;
	
	public ProfilePoint(double pos, double vel, double acc) {
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
	}
	
	public ProfilePoint() {}
}