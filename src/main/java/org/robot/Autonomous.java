package org.firstcapital.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class Autonomous extends CommandGroup {
	public abstract String getAutonomousName();
	
	@Override
	protected void interrupted() {
		super.interrupted();	
	}
	
	@Override
	protected void end() {
		super.end();
	}
}