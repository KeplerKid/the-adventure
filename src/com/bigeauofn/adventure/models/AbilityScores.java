package com.bigeauofn.adventure.models;


import java.util.HashMap;

import com.bigaeuofn.adventure.utilities.ListUtility;

public class AbilityScores {

	private HashMap<String, AbilityScore> abilityList;

	public AbilityScores(HashMap<String, AbilityScore> scores) {
		this.abilityList = scores;
	}
	
	public AbilityScore getAbilityScore(String abilityName) {
		return abilityList.get(abilityName);
	}
	
	@Override
	public String toString(){
		StringBuilder toReturn = new StringBuilder();
		for(String s : ListUtility.asSortedList(this.abilityList.keySet())){
			toReturn.append(this.abilityList.get(s).toString());
		}
		
		return toReturn.toString();
	}
}
