 package org.auto.lib;

 public interface AutonomousStep {
   // Called when this step starts
   public void init();
   // Called continously while this step is running
   // Return true as long as this step should run (return false when the step is finished)
   public boolean run();
   // Called after run() returns false and before the next step starts
   public void end();
 }