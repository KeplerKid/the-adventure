package com.bigeauofn.adventure.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.bigeauofn.adventure.dicebag.*;

public class Ability {

	private String type;
	private String name;
	private String source;
	private String defense;
	private int range;
	private KeywordCollection keywords;
	private ArrayList<String> effects;
	private ArrayList<String> requirements;
	private boolean usesProficiencyBonus;
	private boolean usesWeapon;
	private ArrayList<Dice> dice;

	public Ability(String source, String defense, int range,
			ArrayList<String> effects, ArrayList<String> requirements,
			boolean usesProficiencyBonus, boolean usesWeapon,
			ArrayList<Dice> dice) {

		this.source = source;
		this.defense = defense;
		this.range = range;
		this.effects = effects;
		this.requirements = requirements;
		this.usesProficiencyBonus = usesProficiencyBonus;
		this.usesWeapon = usesWeapon;
		this.dice = dice;
	}

	public Ability(HashMap<String, String> ability) {
		this.type = ability.get("type");
		this.name = ability.get("name");
		this.source = ability.get("source");
		this.defense = ability.get("defense");
		this.range = Integer.parseInt(ability.get("range"));
		this.effects = new ArrayList<String>(Arrays.asList(ability.get(
				"effects").split(",")));
		this.requirements = new ArrayList<String>(Arrays.asList(ability.get(
				"requirements").split(",")));
		this.usesProficiencyBonus = Boolean.parseBoolean(ability
				.get("usesproficiencybonus"));
		this.usesWeapon = Boolean.parseBoolean("usesweapon");
		this.dice = DiceFactory.getDice(ability.get("dice"));
	}

	/**
	 * Sums up all bonuses related to the ability itself, does not consider
	 * feats or other effects.
	 * 
	 * @param attacker
	 *            actor using the ability
	 * @param attackRoll
	 *            d20 attack roll used in conjunction with the ability
	 * @return An updated RollResult with the appropriate modifiers added to it.
	 */
	public RollResult getAbilityAttackBonuses(Actor attacker,
			RollResult attackRoll) {
		System.out.println("getAbilAtkBonus: " + this.name);
		int sumBonuses = 0;
		Weapon w = attacker.getEquipedWeapon();

		sumBonuses += (attacker.getStatInteger(source) - 10) / 2;
		System.out.println("Bonus from " + source + ": " + sumBonuses);
		
		if (this.usesProficiencyBonus) {
			System.out.println("adding proficiency bonus of " + w.getProficiencyBonus());
			sumBonuses += w.getProficiencyBonus();
		}

		if (this.usesWeapon) {
			System.out.println("adding ehancement bonus of " + w.getEnhancementLevel());
			sumBonuses += w.getEnhancementLevel();
		}

		attackRoll.addModifier(sumBonuses);

		return attackRoll;
	}

	// TODO need to iterate through appropriate effects
	// not all abilities add the Source as 
	public RollResult addAbilityBonusDamage(Actor attacker, RollResult dm) {
		dm.addModifier((attacker.getStatInteger(source) - 10) / 2);
		return dm;
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

	public ArrayList<String> getEffects() {
		return effects;
	}

	public ArrayList<String> getRequirements() {
		return requirements;
	}

	public boolean isUsesProficiencyBonus() {
		return usesProficiencyBonus;
	}

	public void setUsesProficiencyBonus(boolean usesProficiencyBonus) {
		this.usesProficiencyBonus = usesProficiencyBonus;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
//		sb.append("Ability Named -");
		sb.append(this.name);
		/*
		sb.append("\n Type - ");
		sb.append(this.type);
		sb.append("\n Source - ");
		sb.append(this.source);
		sb.append("\n range -");
		sb.append(this.range);
		for (String s : this.effects) {
			sb.append("\n\tEffect - " + s);
		}
		for (String s : this.requirements) {
			sb.append("\n\t requirement - " + s);
		}
		sb.append("\nUses Profeciency Bonus - ");
		sb.append(this.usesProficiencyBonus);
		sb.append("\nUses Weapon - ");
		sb.append(this.usesWeapon);
		sb.append("Dice Count - ");
		sb.append(this.dice.size());
		sb.append("\n");
		for (Dice d : this.dice) {
			sb.append("\t" + d.toString());
		}
		*/
		return sb.toString();
	}

}
