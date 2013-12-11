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
		this.dice = DiceFactory.getDice(numDice+"d"+ numSides);
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



	public String getGroup() {
		return group;
	}

	public String getName() {
		return name;
	}


	public int getHandsToWield() {
		return handsToWield;
	}

	public int getReach() {
		return reach;
	}


	public int getProficiencyBonus() {
		return proficiencyBonus;
	}

	public int getEnhancementLevel() {
		return enhancementLevel;
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
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Weapon Named ");
		sb.append(this.name);
		sb.append(" Hands to Wield - ");
		sb.append(this.handsToWield);
		sb.append(" Reach - ");
		sb.append(this.reach); 
		sb.append(" Dice Count - ");
		sb.append(this.dice.size()); 
		for(Dice d : this.dice){
			sb.append(" " + d.toString());
		}
		sb.append(" Proficiency Bonus - ");
		sb.append(this.proficiencyBonus);  
		sb.append(" Enhancement Level - ");
		sb.append(this.enhancementLevel); 
		sb.append(" Group - ");
		sb.append(this.group); 
		
		return sb.toString();
	}

}
