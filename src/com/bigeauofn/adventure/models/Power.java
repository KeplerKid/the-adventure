package com.bigeauofn.adventure.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.bigeauofn.adventure.dicebag.*;

public class Power {

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
	private String diceString;
	private ArrayList<AbilityScore> bonusDamage; // TODO
	
	public Power(HashMap<String, String> ability) {
		this.type = ability.get("type");
		this.name = ability.get("name");
		this.source = ability.get("source");
		this.defense = ability.get("defense");
		this.range = Integer.parseInt(ability.get("range"));
		this.usesWeapon = Boolean.parseBoolean(ability.get("usesweapon"));
		this.diceString = ability.get("dice");
//		this.bonusDamage = new ArrayList<Stat>();
//		for (String s : Arrays.asList(ability.get("bonusdamage")) {
//			bonusDamage.add(new Stat(s));
//		}
		this.effects = new ArrayList<String>(Arrays.asList(ability.get(
				"effects").split(",")));

		this.requirements = new ArrayList<String>(Arrays.asList(ability.get(
				"requirements").split(",")));

		this.keywords = new KeywordCollection();
		this.keywords.addKeywords(Arrays.asList(ability.get("keywords")));

		this.usesProficiencyBonus = Boolean.parseBoolean(ability
				.get("usesproficiencybonus"));
		
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

		sumBonuses += (attacker.getAbilityScore(source).getValue() - 10) / 2;
		System.out.println("Bonus from " + source + ": " + sumBonuses);

		if (this.usesProficiencyBonus) {
			System.out.println("adding proficiency bonus of "
					+ w.getProficiencyBonus());
			sumBonuses += w.getProficiencyBonus();
		}

		if (this.usesWeapon) {
			System.out.println("adding ehancement bonus of "
					+ w.getEnhancementLevel());
			sumBonuses += w.getEnhancementLevel();
		}

		attackRoll.addModifier(sumBonuses);

		return attackRoll;
	}

	/** TODO
	 * All effects that occur when an ability hits are taken care of here (eventaully).
	 */
	public RollResult hit(Actor attacker) {
		
		// damage roll
		RollResult damageRoll = dealDamage(attacker.getEquipedWeapon());
		
		// additional damage
		
		return damageRoll;
	}
	
	private RollResult dealDamage(Weapon equipedWeapon) {
		RollResult toReturn = null;
		if (this.usesWeapon) {
			toReturn = equipedWeapon.rollWeaponDice(this);
		} else {
			toReturn = rollAbilityDice();
		}
		return toReturn;
	}

	/**
	 * Rolls the damage dice for the ability, ex. will roll 2d4 for Magic Missle
	 * @return
	 */
	private RollResult rollAbilityDice() {
		ArrayList<Dice> damageDice = DiceFactory.getDice(this.diceString);
		RollResult toReturn = new RollResult(0);
		
		for (Dice d : damageDice) {
			toReturn.addResult(d.rollDice());
		}
		
		return toReturn;
	}

	/** TODO
	 * All effects/events that occur when an ability misses are taken care of here (eventually).
	 */
	public void miss(Actor attacker, Actor defender) {
		
	}
	
	// TODO need to iterate through appropriate effects
	// not all abilities add the Source as extra damage (ex: Fighter's Sure
	// Strike pg. 77)
	private RollResult addAbilityBonusDamage(Actor attacker, RollResult dm) {
		dm.addModifier((attacker.getAbilityScore(source).getValue() - 10) / 2);
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
		// sb.append("Ability Named -");
		sb.append(this.name);
		/*
		 * sb.append("\n Type - "); sb.append(this.type);
		 * sb.append("\n Source - "); sb.append(this.source);
		 * sb.append("\n range -"); sb.append(this.range); for (String s :
		 * this.effects) { sb.append("\n\tEffect - " + s); } for (String s :
		 * this.requirements) { sb.append("\n\t requirement - " + s); }
		 * sb.append("\nUses Profeciency Bonus - ");
		 * sb.append(this.usesProficiencyBonus); sb.append("\nUses Weapon - ");
		 * sb.append(this.usesWeapon); sb.append("Dice Count - ");
		 * sb.append(this.dice.size()); sb.append("\n"); for (Dice d :
		 * this.dice) { sb.append("\t" + d.toString()); }
		 */
		return sb.toString();
	}

	/**
	 * This handles the abilities that do X[W] damage like Brute Strike (page 77). 
	 * @return
	 */
	public int getWeaponDiceMultiplier() {
		int multiplier = 1;
		
		if (this.usesWeapon) {
			int splitter = this.diceString.indexOf("w");
			multiplier = Integer.parseInt(diceString.substring(0, splitter));
		}
		
		return multiplier;
	}

}
