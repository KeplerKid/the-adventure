package com.bigeauofn.adventure.models;

public class Attack {

	private Actor attacker;
	private Actor defender;
	private Ability ability;
	private Weapon weapon;
	
	public Attack(Actor attacker, Actor defender, Ability ability, Weapon weapon) {
		this.attacker = attacker;
		this.defender = defender;
		this.ability = ability;
		this.weapon = weapon;
	}
	
	public void resolve() {
		int damageDealt = 0;
		
		int d20result = Dice.quickRoll(1, 20);
		boolean hit = false;
		boolean crit = false;

		if (d20result == 20) {
			hit = true;
			crit = true; // not necessarily always true?
		} else {
			hit = doesAttackerHitDefender(d20result);
		}
		
		if (hit) {
			damageDealt = rollDamage(crit);
		}
		
		dealDamage(damageDealt);
		
	}

	private void dealDamage(int damageDealt) {
		int currentHP = defender.getCurrentHP();
		defender.setCurrentHP(currentHP - damageDealt);
	}

	private int rollDamage(boolean crit) {
		int damage = 0;
		
		int numDie = ability.getDamageDieCount();
		int numSides;
		
		if (ability.isUsesWeapon()) {
			numDie *= weapon.getNumDice();
			numSides = weapon.getNumSides();
		} else {
			numSides = ability.getDamageDieSides();
		}

		System.out.println("numDie" + numDie);
		System.out.println("numSides" + numSides);
		if (!crit) {
			damage = Dice.quickRoll(numDie, numSides);
			System.out.println("not crit");
		} else {
			// max damage
			damage = numDie * numSides;
			System.out.println("CRITICAL HIT!");
		}
		
		System.out.println("weapon damage roll: " + damage);
		
		damage += weapon.getEnhancementLevel();
		
		damage += (attacker.getStatInteger(ability.getSource()) - 10) / 2 ;
		
		System.out.println(damage + " damage dealt");
		return damage;
	}

	private boolean doesAttackerHitDefender(int d20result) {
		
		int attackTotal = addAttackBonuses(d20result);
		
		return attackTotal >= defender.getStatInteger(ability.getDefense()); 
	}
	
	private int addAttackBonuses(int d20result) {
		int attackerTotal = 0;
		
		int abilityModBonus = (attacker.getStatInteger(ability.getSource()) - 10) / 2;
		int halfLevelBonus = attacker.getStatInteger("level") / 2;
		int enhancementBonus = weapon.getEnhancementLevel();		
		int weaponProficiencyBonus = 0;
		if (ability.isUsesProficiencyBonus()) {
			weaponProficiencyBonus += weapon.getProficiencyBonus();
		}
		
		attackerTotal = d20result + abilityModBonus + halfLevelBonus + weaponProficiencyBonus + enhancementBonus;
		
		System.out.println("attackerTotal: " + attackerTotal);
		
		return attackerTotal;
	}
	
	
}
