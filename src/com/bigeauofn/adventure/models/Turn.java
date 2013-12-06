package com.bigeauofn.adventure.models;

public class Turn {

	private boolean usedStandardAction;
	private boolean usedMoveAction;
	private boolean usedMinorAction;
	
	public Turn() {
		usedStandardAction = false;
		usedMoveAction = false;
		usedMinorAction = false;
	}
	
}
