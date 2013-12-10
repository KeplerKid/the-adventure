package com.bigeauofn.adventure.dicebag;

import java.util.ArrayList;
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
	
	// TODO make it happen
	public static ArrayList<Dice> getDice(String s) {
		ArrayList<Dice> toReturn = new ArrayList<Dice>();
		
		int splitter = s.indexOf("d");
		int numDice, numSides;
		numDice = Integer.parseInt(s.substring(0, splitter));
		numSides = Integer.parseInt(s.substring(splitter + 1, s.length()));
		
		for (int i = 0; i < numDice; i++) {
			toReturn.add(new Dice(numSides));
		}
		
		return toReturn;
	}
}
