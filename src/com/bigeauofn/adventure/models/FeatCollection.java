package com.bigeauofn.adventure.models;

import java.util.ArrayList;
import java.util.HashMap;

public class FeatCollection {

	private ArrayList<Feat> feats;
	
	public FeatCollection() {
		this.feats = new ArrayList<Feat>();
	}
	
	public void addFeat(HashMap<String, String> newFeat) {
		feats.add(new Feat(newFeat));
	}
	
	public void addFeat(Feat f) {
		feats.add(f);
	}
	
}
