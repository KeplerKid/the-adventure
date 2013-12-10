package com.bigeauofn.adventure.dicebag;

public class HitPoints {
	private int numberOfHitPoints;

	public HitPoints(int numberOfHitPoints) {
		this.numberOfHitPoints = numberOfHitPoints;
	}

	public void subtractHitPoints(HitPoints hp) {
		this.numberOfHitPoints -= hp.numberOfHitPoints;
	}

	public void addtHitPoints(HitPoints hp) {
		this.numberOfHitPoints += hp.numberOfHitPoints;
	}

	@Override
	public String toString() {
		return this.numberOfHitPoints + " Hit Points\n";
	}

}
