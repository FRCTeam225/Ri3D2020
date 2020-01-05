package org.techfire225;

import edu.wpi.first.wpilibj.Joystick;

public class Button {
	Joystick js;
	int button;
	boolean lastState;
	public Button(Joystick js, int button) {
		this.js = js;
		this.button = button;
	}
	
	public boolean get() {
		boolean state = js.getRawButton(button);
		
		if ( state && !lastState ) {
			lastState = true;
			return true;
		}
		else if ( !state )
			lastState = false;
		
		return false;
	}
}