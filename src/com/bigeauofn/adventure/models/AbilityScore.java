package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.dicebag.RollResult;

public class AbilityScore implements D20C {
	private String name;
	private String type;
	private int value;
	private int bonus;
	
	public AbilityScore(String name, int value, String type){
		this.name = name;
		this.value = value;
		this.bonus = 0;
		this.type = type;
	}
	
	
	public String getName(){
		return this.name;
	}
	public int getValue(){
		return this.value + this.bonus;
	}
	public String getType(){
		return this.type;
	}
	public void addBonus(int bonus){
		this.bonus += bonus;
	}
	protected void setName(String name){
		this.name = name;
	}
	protected void setValue(int value){
		this.value = value;
	}
	protected void setBonus(int bonus){
		this.bonus += bonus;
	}
	
	public int compareToRollResult(RollResult rr){
		// TODO
		
		return 4;
	}
	
	@Override
	public String toString(){
		return "Ability Named - " + this.name + " Has Value = " + this.value + " Current Bonus = " + this.bonus + "\n";
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
