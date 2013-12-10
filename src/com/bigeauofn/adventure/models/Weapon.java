package com.bigeauofn.adventure.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.bigeauofn.adventure.dicebag.DamageRoll;
import com.bigeauofn.adventure.dicebag.Dice;
import com.bigeauofn.adventure.dicebag.DiceFactory;
import com.bigeauofn.adventure.dicebag.RollResult;

public class Weapon {

	private String name;
	private int handsToWield;
	private int reach;

	// TODO replace these two integers with a dice
	private int numDice;
	private int numSides;

	// TODO actually make it use dice
	private ArrayList<Dice> dice;

	private int proficiencyBonus;
	private int enhancementLevel;
	private String group;

	// need to add properties, like "High Crit"

	public Weapon(String name, int handsToWield, int reach, int numDice,
			int numSides, int proficiencyBonus, int enhancementLevel) {
		this.name = name;
		this.handsToWield = handsToWield;
		this.reach = reach;
		this.numDice = numDice;
		this.numSides = numSides;
		this.proficiencyBonus = proficiencyBonus;
		this.enhancementLevel = enhancementLevel;
	}

	public Weapon(HashMap<String, String> weapon) {
		this.name = weapon.get("name");
		this.handsToWield = Integer.parseInt(weapon.get("hands"));
		this.reach = Integer.parseInt(weapon.get("reach"));
		this.dice = DiceFactory.getDice(weapon.get("dice"));
		this.proficiencyBonus = Integer
				.parseInt(weapon.get("proficiencyBonus"));
		this.enhancementLevel = Integer
				.parseInt(weapon.get("enhancementLevel"));
		this.group = weapon.get("group");
	}

	public ArrayList<Dice> getDice() {
		return dice;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public RollResult rollWeaponDice() {
		RollResult toReturn = null;

		for (Dice d : this.dice) {
			if (toReturn == null) {
				toReturn = d.rollDice();
			} else {
				toReturn.addResult(d.rollDice());
			}
		}
		return toReturn;

	}

	public RollResult getCriticalDamage() {
		RollResult toReturn = null;

		for (Dice d : this.dice) {
			if (toReturn == null) {
				toReturn = d.rollDice(true);
			} else {
				toReturn.addResult(d.rollDice(true));
			}
		}
		return toReturn;
	}

	}
