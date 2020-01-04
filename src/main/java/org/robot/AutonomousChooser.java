package org.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class AutonomousChooser {
		
	private static AutonomousChooser instance = new AutonomousChooser();
	public static AutonomousChooser getInstance() { return instance; }

	Autonomous[] autonomi = new Autonomous[0];
	volatile int selected = -1;
	boolean buttonWasPressed = false;
	
	
	private AutonomousChooser() {}
	
	public void setAutonomi(Autonomous[] autonomi) {
		if ( autonomi == null )
			return;
		
		if ( autonomi.length == 0 )
			selected = 0;
		else {
			this.autonomi = autonomi;
			selected = 0;
		}
	}
	
	public String[] getAutonomiNames() {
		String[] list = new String[autonomi.length];
		for ( int i = 0; i < autonomi.length; i++ ) {
			list[i] = autonomi[i].getAutonomousName();
		}
		return list;
	}
	
	public void setSelectedAutonomous(int selected) {
		this.selected = selected;
	}
	
	public Command getSelectedAutonomous() {
		if ( selected == -1 || selected > autonomi.length )
			return null;
		try {
			return autonomi[selected].getClass().newInstance();
		} catch ( Exception e ) {
			System.err.println("Couldn't make fresh instance of auto command");
			return autonomi[selected];
		}
	}

	public String getSelectedAutonomousName() {
		if ( selected == -1 || selected > autonomi.length )
			return "Chooser is empty";
		
		return autonomi[selected].getAutonomousName();
	}
	
	public int getSelectedAutonomousIndex() {
		return selected;
	}
	
	public int size() {
		return autonomi.length;
	}
	
	
	public void consoleSelectorUI(Button up, Button down) {
		if ( up.get() && selected < autonomi.length-1 ) {
			if ( !buttonWasPressed ) {
				selected++;
				buttonWasPressed = true;
			}
		}
		else if ( down.get() && selected > 0 ) {
			if ( !buttonWasPressed ) {
				selected--;
				buttonWasPressed = true;
			}
		}
		else
			buttonWasPressed = false;
		
		System.out.println("Auto: "+getSelectedAutonomousName());
	}
}
