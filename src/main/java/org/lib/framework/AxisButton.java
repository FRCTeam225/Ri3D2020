package org.lib.framework;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
* @author Andrew
*/
public class AxisButton extends Button {
	
   Joystick joystick;
   int axis;
   double thresh;
   
   public AxisButton(Joystick joystick, int axis, double thresh) {
       this.joystick = joystick;
       this.axis = axis;
       this.thresh = thresh;
   }
   
   public boolean get() {
       if ( thresh > 0 ) {
           if ( joystick.getRawAxis(axis) > thresh )
               return true;
       } else if ( thresh < 0 ) {
           if ( joystick.getRawAxis(axis) < thresh)
               return true;
       }
       return false;
   }
}
