package com.bigeauofn.adventure.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Feat {

	private ArrayList<Prerequisite> prereqs;
	private String event;
	private ArrayList<Condition> condition; 
	
	public Feat(String type, String key, String value) {
		prereqs = new ArrayList<Prerequisite>();
		prereqs.add(new Prerequisite(type, key, value));
	}
	
	public boolean meetsRequirements(Actor wannaBe) {
		boolean isMeetingRequirements = true;
		
		for (Prerequisite p : prereqs) {
		
			D20C toTest = wannaBe.getCompareObject(p.getCompareType(), p.getCompareKey());
			if (p.compareTo(toTest) < 0) {
				isMeetingRequirements = false;
			}
			
		}

		if (isMeetingRequirements) {
			System.out.println("meets reqs");
		} else {
			System.out.println("doesn't meet reqs");
		}
		return isMeetingRequirements;
	}
	
	public Feat(HashMap<String, String> featMap) {
		prereqs = new ArrayList<Prerequisite>();
		
	}
}
