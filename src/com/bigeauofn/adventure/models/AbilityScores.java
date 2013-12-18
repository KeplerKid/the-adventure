package com.bigeauofn.adventure.models;

import java.util.HashMap;

public class AbilityScores {

	private HashMap<String, AbilityScore> abilityList;

	public AbilityScores(HashMap<String, AbilityScore> scores) {
		this.abilityList = scores;
	}
	
	public AbilityScore getAbilityScore(String abilityName) {
		return abilityList.get(abilityName);
	}
}
