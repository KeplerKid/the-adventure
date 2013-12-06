package com.bigeauofn.adventure.dicebag;

import java.util.HashMap;

public  class DiceFactory {

	public static HashMap<Integer, Dice> getDiceSet(){
		Integer[] diceTypes = { 4, 6, 8, 10, 12, 20,100 };
		HashMap<Integer, Dice> toReturn = new HashMap<Integer, Dice>();
		for(Integer i : diceTypes){
			toReturn.put(i,new Dice(i));
		}
		return toReturn;
	}
}
