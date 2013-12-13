package com.bigeauofn.adventure.models;

public class Prerequisite implements Comparable<D20C>, D20C {

	// DEX 15
	// TYPE would be stat (others include ability, class, race)
	// KEY would be DEX
	// VALUE would be 15
	private String type;
	private String key;
	private String value;

	public Prerequisite(String type, String key, String value) {
		this.type = type;
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(D20C o){
		if (! o.getCompareType().equals(this.type)) {
			try {
				throw new InvalidD20CompareTypeException();
			} catch (InvalidD20CompareTypeException e) {
				System.out.println("this type:\t" + type);
				System.out.println("o's type:\t" + type);
				e.printStackTrace();
			}
		}
		
		if (type.equals("stat")) {
			return intCompare(this.getCompareValue(), o.getCompareValue());
		} else {
			return stringCompare(this.getCompareValue(), o.getCompareValue());
		}
	}

	/**
	 * @param a
	 *            prerequisite's value
	 * @param b
	 *            other D20C's value
	 * @return 0 if equal, else -1 (not equal)
	 */
	private int stringCompare(String a, String b) {
		int toReturn = -1;
		if (a.equals(b)) {
			toReturn = 0;
		}
		return toReturn;
	}

	private int intCompare(String a, String b) {
		System.out.println("Comparing a: " + a + " to " + b);
		return Integer.parseInt(b) - Integer.parseInt(a);
	}

	@Override
	public String getCompareType() {
		return this.type;
	}

	@Override
	public String getCompareKey() {
		return this.key;
	}

	@Override
	public String getCompareValue() {
		return this.value;
	}

}
