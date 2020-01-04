package org.lib.framework;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
* @author Adam
*/
public class POVButton extends Button {
   Joystick joystick;
   int pov;
   
   public POVButton(Joystick joystick, int pov) {
       this.joystick = joystick;
       this.pov = pov;
   }
   
   public boolean get() {
       if (joystick.getPOV() == pov)
    	   return true;
       return false;
   }
   
}
