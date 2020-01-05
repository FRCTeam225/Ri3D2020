package org.auto.lib;

import java.util.ArrayList;

import org.techfire225.*;
import org.auto.lib.*;

public class AutonomousSequence {
  ArrayList<AutonomousStep> autonomousSteps;
 //public Constants constants = Constants.getConstants();

  int step = -1;
  boolean running = false;

  public AutonomousSequence() {
	  autonomousSteps = new ArrayList<>();
  }

  public void addStep(AutonomousStep step) {
    autonomousSteps.add(step);
  }

  public void start() {
    if ( autonomousSteps.size() == 0 )
      return;
    step = 0;
    running = true;
    autonomousSteps.get(0).init();
  }

  public void stop() {
    step = 0;
    running = false;
  }
  
  public void append(AutonomousSequence auto){
	  for (AutonomousStep step : auto.autonomousSteps){
		  autonomousSteps.add(step);
	  }
  }

  public void update() {
    if ( step >= autonomousSteps.size() ) {
      running = false;
      step = 0;
    }
    if (running){
    	AutonomousStep thisStep = autonomousSteps.get(step);
	    if ( !thisStep.run() ) {
	      thisStep.end();
	      step++;
	      if ( step < autonomousSteps.size() ) {
	        AutonomousStep nextStep = autonomousSteps.get(step);
	        nextStep.init();
	      }
	      else {
	        running = false;
	        step = 0;
	      }
    	
      }
    }
  }
}
