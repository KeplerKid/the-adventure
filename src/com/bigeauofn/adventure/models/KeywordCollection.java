package com.bigeauofn.adventure.models;

public class KeywordCollection {

	private class Keyword implements Comparable<Keyword> {
		private String keyword;
		
		public Keyword(String keyword) {
			this.keyword = keyword;
		}

		public String getKeyword() {
			return this.keyword;
		}
		
		@Override
		public int compareTo(Keyword otherKeyword) {
			
			return 0;
		}
		
		public boolean equals(Object otherKeyword) {
			return this.keyword.equals(((Keyword) otherKeyword).getKeyword());
		}
	}
	
	public KeywordCollection() {
		
	}
}
