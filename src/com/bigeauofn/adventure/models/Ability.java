package com.bigeauofn.adventure.models;

import java.util.ArrayList;

public class Ability {

	private String source;
	private String defense;
	private int range;
	private ArrayList<Effect> effects;
	private ArrayList<Requirement> requirements;
	private boolean usesProficiencyBonus;
	private boolean usesWeapon;
	private int damageDieCount;
	private int damageDieSides;
	
	public int getDamageDieCount() {
		return damageDieCount;
	}

	public void setDamageDieCount(int damageDieCount) {
		this.damageDieCount = damageDieCount;
	}

	public int getDamageDieSides() {
		return damageDieSides;
	}

	public void setDamageDieSides(int damageDieSides) {
		this.damageDieSides = damageDieSides;
	}

	public Ability(String source, 
			String defense, 
			int range,
			ArrayList<Effect> effects,
			ArrayList<Requirement> requirements,
			boolean usesProficiencyBonus) {

		this.source = source;
		this.defense = defense;
		this.range = range;
		this.effects = effects;
		this.requirements = requirements;
		this.usesProficiencyBonus = usesProficiencyBonus;
		
	}

	public Ability(String source, 
			String defense, 
			int range,
			boolean usesProficiencyBonus) {

		this.source = source;
		this.defense = defense;
		this.range = range;
		this.requirements = null;
		this.effects = null;
		this.usesProficiencyBonus = usesProficiencyBonus;
		
	}

	public boolean isUsesWeapon() {
		return usesWeapon;
	}

	public void setUsesWeapon(boolean usesWeapon) {
		this.usesWeapon = usesWeapon;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDefense() {
		return defense;
	}

	public void setDefense(String defense) {
		this.defense = defense;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}

	public void setEffects(ArrayList<Effect> effects) {
		this.effects = effects;
	}

	public ArrayList<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<Requirement> requirements) {
		this.requirements = requirements;
	}

	public boolean isUsesProficiencyBonus() {
		return usesProficiencyBonus;
	}

	public void setUsesProficiencyBonus(boolean usesProficiencyBonus) {
		this.usesProficiencyBonus = usesProficiencyBonus;
	}
	
	

	
}
