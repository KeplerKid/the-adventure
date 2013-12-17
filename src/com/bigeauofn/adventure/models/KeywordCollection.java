package com.bigeauofn.adventure.models;

import java.util.ArrayList;
import java.util.List;

public class KeywordCollection {

	
	/**
	 * No other class should need direct access to keywords, as they will all simply be strings.
	 * @author JimseyJoe
	 *
	 */
	private class Keyword {
		private String keyword;
		
		public Keyword(String keyword) {
			this.keyword = keyword;
		}

		public String getKeyword() {
			return this.keyword;
		}
		
		public boolean equals(Object otherKeyword) {
			return this.keyword.equals(((Keyword) otherKeyword).getKeyword());
		}
	}
	
	
	// start KeywordCollection implementation
	private ArrayList<Keyword> keywords;
	
	public KeywordCollection() {
		this.keywords = new ArrayList<Keyword>();
	}
	
	public void addKeywords(List<String> keywords) {
		for (String s : keywords) {
			this.keywords.add(new Keyword(s));
		}
	}
	
	public boolean hasKeyword(String s) {
		return keywords.contains(s);
	}
}
