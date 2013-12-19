package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.dicebag.RollResult;

public class Skill implements D20C {
	private String name;
	private String type;
	private Actor actor;
	private String abilityName;
	private int armourPenalty;
	private int misc;
	private Boolean trained;
	
	public Skill(String name, Actor a,String abilityName,  String type){
		this.name = name;
		this.actor = a;
		this.type = type;
		this.abilityName = abilityName;
	}
	
	
	public String getName(){
		return this.name;
	}
	public int getValue(){
		int toReturn = 0;
		toReturn += actor.getAbilityScore(abilityName).getValue();
		toReturn += actor.getNotDoneYet().get("level").getValue() / 2;
		toReturn -= this.armourPenalty;
		if(this.trained){
			toReturn += 5;
		}
		toReturn += this.misc;
		
		return toReturn;
	}
	public String getType(){
		return this.type;
	}
	public void setArmourPenalty(int penalty){
		this.armourPenalty = penalty;
	}
	public void setTrained(){
		this.trained = true;
	}
	public void setMisc(int misc){
		this.misc = misc;
	}
	

	
	public int compareToRollResult(RollResult rr){
		// TODO
		
		return 4;
	}
	
	@Override
	public String toString(){
		return "Skill Named - " + this.name + " Has Value = " + this.getValue() ;
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
		return this.getValue() + "";
	}

}
