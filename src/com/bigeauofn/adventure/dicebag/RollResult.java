package com.bigeauofn.adventure.dicebag;

import java.util.Comparator;

import com.bigeauofn.adventure.models.HitPoints;

public class RollResult implements Comparable<RollResult>,
		Comparator<RollResult>, DamageRoll, AttackRoll {
	
	private int result;
	private boolean isCrit = false;

	// temporary constructor for damage rolls, don't use the boolean
	// "We're taking a horrible shortcut to get something done in time, here" - P.R.
	public RollResult(int result, boolean isCritical) {
		this.result = result;
	}
	
	// only d20s can crit
	void setCrit() {
		isCrit = this.result == 20;
	}

	public RollResult(int result){
		this.result = result;
	}

	public void addModifier(int modifier) {
		System.out.println("Adding " + modifier + " to get a new total of " + (this.result + modifier));
		this.result += modifier;
	}

	public void addResult(RollResult otherResult) {
		System.out.println("Adding " + otherResult.result + " to get a new total of " + (this.result + otherResult.result));
		this.result += otherResult.result;
	}
	
	public int compareToDefense(int defense) {
		return this.result - defense;
	}
	
	@Override
	public int compare(RollResult rr0, RollResult rr1) {
		return rr0.result  - rr1.result;
	}

	@Override
	public int compareTo(RollResult rr) {
		return this.result - rr.result ;
	}
	
	@Override
	public String toString(){
		return "Roll Result = " + this.result;
	}

	@Override
	public HitPoints getDamage() {
		return new HitPoints(this.result);
	}

	@Override
	public int getAttackTotal() {
		System.out.println("Attack Total: " + this.result);
		return this.result;
	}

	@Override
	public boolean isCritical() {
		return isCrit;
	}
	
}
