package com.bigeauofn.adventure.models;

public class Weapon {
	
	private String name;
	private int handsToWield;
	private int reach;
	private int numDice;
	private int numSides;
	private int proficiencyBonus;
	private int enhancementLevel;
	
	// need to add proverties, like 
	
	public Weapon(String name, int handsToWield, int reach, int numDice, int numSides, int proficiencyBonus, int enhancementLevel) {
		this.name = name;
		this.handsToWield = handsToWield;
		this.reach = reach;
		this.numDice = numDice;
		this.numSides = numSides;
		this.proficiencyBonus = proficiencyBonus;
		this.enhancementLevel = enhancementLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHandsToWield() {
		return handsToWield;
	}

	public void setHandsToWield(int handsToWield) {
		this.handsToWield = handsToWield;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public int getNumDice() {
		return numDice;
	}

	public void setNumDice(int numDice) {
		this.numDice = numDice;
	}

	public int getNumSides() {
		return numSides;
	}

	public void setNumSides(int numSides) {
		this.numSides = numSides;
	}

	public int getProficiencyBonus() {
		return proficiencyBonus;
	}

	public void setProficiencyBonus(int proficiencyBonus) {
		this.proficiencyBonus = proficiencyBonus;
	}

	public int getEnhancementLevel() {
		return enhancementLevel;
	}

	public void setEnhancementLevel(int enhancementLevel) {
		this.enhancementLevel = enhancementLevel;
	}

}
