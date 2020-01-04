package org.lib.framework;

import edu.wpi.first.wpilibj.command.Command;

public class HoldMode<Mode_T> extends Command {

	TFSubsystem<Mode_T> subsystem;
	Mode_T modeOn;
	Mode_T modeOff;
	
	public HoldMode(TFSubsystem<Mode_T> subsystem, Mode_T modeOn, Mode_T modeOff) {
		this.subsystem = subsystem;
		this.modeOn = modeOn;
		this.modeOff = modeOff;
	}
	
	@Override
	protected void initialize() {
		subsystem.setMode(modeOn);
	}
	
	@Override
	protected void end() {
		subsystem.setMode(modeOff);
	}
	
	@Override
	protected void interrupted() {
		subsystem.setMode(modeOff);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
