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

	public Attack(Actor attacker, Actor defender) {
		this.attacker = attacker;
		this.defender = defender;
	}

	public void resolve() {

		RollResult d20result = this.attacker.rollDice(20);
		boolean hit = false;

		d20result = attacker.getAttackModifiers(d20result);

		hit = defender.isHitByAttack(d20result, attacker.getAbility().getDefense());

		if (hit) {

			System.out.println("A Hit was Scored\n");

			//RollResult damageDealt = calculateDamage(d20result);
			defender.takeDamage(attacker.InflictDamage(d20result));

		} else {

			System.out.println("The Roll Missed\n");
		}

	}


	/**
	 * Retrieve a list of all available targets of this ability + weapon
	 * combination
	 * 
	 * @param attacker
	 *            The actor performing the attack
	 * @param world
	 *            mainly to get the other characters, and environment
	 * @return a list of eligible targets for the attack
	 */
	public static int[] getPossibleTargets(Actor attacker, World world) {

		int[] actorLoc = attacker.getLocation();
		int range = 0;
		//if (ability.isUsesWeapon()) {
			//range = weapon.getReach();
		//}

		int numSquares = (int) Math.pow(range, 3);
		int[] targetSquares = new int[numSquares];
		for (int i = 0; i < numSquares; i++) {
			// targetSquares
		}
		return null;
	}
}
