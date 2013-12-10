package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.World;
import com.bigeauofn.adventure.dicebag.RollResult;

public class Attack {

	public static int BURST; // goes out X squares in all directions from center
	public static int BLAST; // blast X would be a XxX area
	public static int WALL; // a contiguous section of squares
	public static int RANGED; // target is with X squares

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

		RollResult d20result = this.attacker.rollDice(20);
		boolean hit = false;

		d20result.addModifier(calculateAttackBonuses());

		hit = defender.isHitByAttack(d20result, ability.getDefense());

		if (hit) {

			System.out.println("A Hit was Scored\n");

			RollResult damageDealt = calculateDamage(d20result);
			defender.takeDamage(damageDealt);

		} else {

			System.out.println("The Roll Missed\n");
		}

	}

	private RollResult calculateDamage(RollResult d20result) {

		// TODO currently only handles attacks with weapons, not abilities
		if (ability.isUsesWeapon()) {
			// TODO make a critical weapon roll
		} else {

		}

		RollResult damageRolls = null;
		
		if (!d20result.isCritical()) {

			damageRolls = attacker.getEquipedWeapon().rollWeaponDice();

		} else {
			// max damage
			damageRolls = attacker.getEquipedWeapon().getCriticalDamage();
			System.out.println("CRITICAL HIT!");
		}

		// add damage modifiers
		int damage = weapon.getEnhancementLevel();
		damage += (attacker.getStatInteger(ability.getSource()) - 10) / 2;
		damageRolls.addModifier(damage);

		System.out.println(damageRolls + " damage dealt");
		return damageRolls;
	}

	/**
	 * sum up the attack bonuses including weapon proficiency, 1/2 of actor's
	 * level, the ability score modifier, and weapon enhancement bonus.
	 * 
	 * @param d20result
	 * @return the sum of the d20result and other bonuses
	 */
	private int calculateAttackBonuses() {
		// TODO include any bonuses from the ability used to make attack, e.g. a
		// Ranger's Careful Attack page 105 PHB
		int bonusesTotal = 0;

		int abilityModBonus = (attacker.getStatInteger(ability.getSource()) - 10) / 2;
		int halfLevelBonus = attacker.getStatInteger("level") / 2;
		int enhancementBonus = weapon.getEnhancementLevel();
		int weaponProficiencyBonus = 0;
		if (ability.isUsesProficiencyBonus()) {
			weaponProficiencyBonus += weapon.getProficiencyBonus();
		}

		bonusesTotal = abilityModBonus + halfLevelBonus
				+ weaponProficiencyBonus + enhancementBonus;

		System.out.println("attackerTotal: " + bonusesTotal);

		return bonusesTotal;
	}

	/**
	 * Retrieve a list of all available targets of this ability + weapon
	 * combination
	 * 
	 * @param attacker
	 *            The actor performing the attack
	 * @param ability
	 *            The selected ability to perform the attack with
	 * @param world
	 *            mainly to get the other characters, and environment
	 * @param weapon
	 *            the weapon used to make the attack
	 * @return a list of eligible targets for the attack
	 */
	public static int[] getPossibleTargets(Actor attacker, Ability ability,
			Weapon weapon, World world) {

		int[] actorLoc = attacker.getLocation();
		int range = 0;
		if (ability.isUsesWeapon()) {
			range = weapon.getReach();
		}

		int numSquares = (int) Math.pow(range, 3);
		int[] targetSquares = new int[numSquares];
		for (int i = 0; i < numSquares; i++) {
			// targetSquares
		}
		return null;
	}
}
