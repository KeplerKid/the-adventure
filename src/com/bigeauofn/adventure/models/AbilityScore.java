package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.dicebag.RollResult;

public class AbilityScore implements D20C {
	private String name;
	private String type;
	private int value;
	
	public AbilityScore(String name, int value, String type){
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	
	public String getName(){
		return this.name;
	}
	public int getValue(){
		return this.value;
	}
	public String getType(){
		return this.type;
	}
	public int getMod(){
		return Math.max((this.value -10) - 5,0);
	}

	
	

	
	public int compareToRollResult(RollResult rr){
		// TODO
		
		return 4;
	}
	
	@Override
	public String toString(){
		return "Ability Named - " + this.name + " Has Value = " + this.value + " Ability Mod = " + this.getMod() + "\n";
	}


	@Override
	public String getCompareType() {
		return this.type;
	}


	@Override
	public String getCompareKey() {
		return this.name;
	}


	@Override
	public String getCompareValue() {
		return this.value + "";
	}

}
