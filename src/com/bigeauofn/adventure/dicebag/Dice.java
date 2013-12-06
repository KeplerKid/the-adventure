package com.bigeauofn.adventure.dicebag;

import java.util.Random;

public class Dice {
	private int numberOfSides;
	
	public  Dice(int numberOfSides){
		this.numberOfSides = numberOfSides;
		System.out.println(this.toString() + " was just creater");
	}
	
	public RollResult rollDice(){
		RollResult toReturn;
		Random randomResult = new Random();
		toReturn = new RollResult(randomResult.nextInt(this.numberOfSides) + 1);
		
		// only d20s can crit
		if(this.numberOfSides == 20) {
			toReturn.setCrit();
		}
		
		this.logRoll(toReturn);
		return toReturn;
	}
	
	@Override
	public String toString(){
		return "A D" + this.numberOfSides  + " Dice";
	}
	
	private void logRoll(RollResult rr){
		System.out.println(this.toString() + " Just had a " + rr.toString());
	}
	

}
