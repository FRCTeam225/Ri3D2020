package org.firstcapital.lib.framework;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class TFSubsystem<Mode_T> extends Subsystem {	
	
	private volatile Mode_T currentMode;
	protected volatile boolean onTarget = false;

	public void setMode(Mode_T mode) {
		currentMode = mode;
		onTarget = false;
	}
	
	public Mode_T getCurrentMode() {
		return currentMode;
	}
	
	public boolean atSetpoint() {
		return onTarget;
	}

	public abstract void update();
	
	//public abstract void refreshConstants();
}
