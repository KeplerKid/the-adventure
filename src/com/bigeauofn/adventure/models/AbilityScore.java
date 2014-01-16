package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.dicebag.RollResult;

import java.io.PrintStream;

public class AbilityScore implements D20C {
	private String name;
	private String type;
	private int value;
	
	public AbilityScore(String name, int value, String type){
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	
	public String getName(){
		return this.name;
	}
	public int getValue(){
		return this.value;
	}
	public String getType(){
		return this.type;
	}

    public static void printModTable(int minAbilityScore, int maxAbilityScore, PrintStream out) {
        StringBuilder sb;
        int intDivResult, doubleDivResult;

        for (int i = minAbilityScore; i <= maxAbilityScore; i++) {
            sb = new StringBuilder();
            sb.append("AbilityScore : ");
            sb.append(i);
            sb.append("\t(i - 10) / 2 : ");
            intDivResult = ((i - 10) / 2);
            sb.append(intDivResult);
            sb.append("\t(int)Math.floor((i - 10) / 2.0) : ");
            doubleDivResult = (int) Math.floor((i - 10) / 2.0);
            sb.append(doubleDivResult);
            if (doubleDivResult >= 0) {
                sb.append("\t");
            }
            sb.append("\tint modifier calulation correct? : ");
            sb.append(intDivResult == doubleDivResult);
            out.println(sb.toString());
        }
    }

    public static void printModTable(int minAbilityScore, int maxAbilityScore) {
        printModTable(minAbilityScore, maxAbilityScore, System.out);
    }

    public int getMod() {
        return (int) Math.floor((this.value - 10) / 2.0);
        /*
         * This :
         *  return (int)((this.value - 10) / 2);
         * is incorrect for this.value == [1, 9]
         *
         * IF YOU'RE CURIOUS
         * ([1, 9] - 10) / 2 = [-9, -1] / 2
         *
         * So how java and most processors
         *    implement integer division is your issue
         * [-9, -1] / 2
         *
         * if it were to implement it proper Euclidean Division
         *  a / b = q,r for q,r such that a = b * q + r where 0 <= r < |b|
         *     so -9 / 2 = -5,1 (since -9 = 2 * -5 + 1)
         *      and -1 / 2 = -1,1 (since -1 = 2 * -1 + 1)
         *       and so on ...
         *
         * This usually isn't implemented with two's complement hardware
         *  (which is good for adding and subtracting integers)
         * instead it's implemented like this
         *  a / b = q:r for q,r such that a = b * q + r where 0 <= |r| < b
         *    so -9 / 2 = -4,-1 (since -9 = 2 * -4 + (-1))
         *     and -1 / 2 = 0,-1 (since -1 = 2 * 0 + (-1))
         *      and so on ...
         *  This unfortunately yields the incorrect mod values for ability scores [1, 9]
         *
         * This however confuses me greatly:
		 * return Math.max((this.value -10) - 5,0);
		 */
    }


    public int compareToRollResult(RollResult rr){
		// TODO
		
		return 4;
	}
	
	@Override
	public String toString(){
		return "Ability Named - " + this.name + " Has Value = " + this.value + " Ability Mod = " + this.getMod() + "\n";
	}


	@Override
	public String getCompareType() {
		return this.type;
	}


	@Override
	public String getCompareKey() {
		return this.name;
	}


	@Override
	public String getCompareValue() {
		return this.value + "";
	}

}
