package com.bigeauofn.adventure.models;

public class Dice {

	public static int[] roll(int numDice, int numSides) {
		
		int[] rolls = new int[numDice];
		
		for (int i = 0; i < numDice; i++) {
			 rolls[i] = (int)(Math.random() * numSides) + 1;
		}
		return rolls;
	}
	
	public static int sumRoll(int[] rolls) {
		int sum = 0;
		for (int roll : rolls) {
			sum += roll;
		}
		return sum;
	}
	
	public static int quickRoll(int numDice, int numSides) {
		return sumRoll( roll(numDice, numSides) );
	}
	
}
