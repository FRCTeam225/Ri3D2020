package org.robot;

import edu.wpi.first.wpilibj.Joystick;

public class POV {

	Joystick js;
	int desired;
	boolean state;
	boolean lastState;

	public POV(Joystick js, int desired) {
		this.js = js;
		this.desired = desired;
	}
	
	public boolean get() {
		state = (js.getPOV() == desired);
		if ( state && !lastState ) {
			lastState = true;
			return true;
		}
		else if ( !state )
			lastState = false;
		
		return false;
	}
}

