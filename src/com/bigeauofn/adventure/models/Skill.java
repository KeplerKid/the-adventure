package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.dicebag.RollResult;
import com.bigeauofn.adventure.utilities.StringUtility;

public class Skill implements D20C {
    private String name;
    private String type;
    private Actor actor;
    private String abilityName;
    private int armourPenalty;
    private int misc;
    private Boolean trained = false;

    public Skill(String name, Actor a, String abilityName, String type) {
        this.name = name;
        this.actor = a;
        this.type = type;
        this.abilityName = abilityName;
    }


    public String getName() {
        return this.name;
    }

    public int getValue() {
        int toReturn = 0;
        toReturn += actor.getAbilityScore(abilityName).getValue();
        toReturn += actor.getNotDoneYet().get("level").getValue() / 2;
        toReturn -= this.armourPenalty;
        if (this.trained) {
            toReturn += 5;
        }
        toReturn += this.misc;

        return toReturn;
    }

    public String getType() {
        return this.type;
    }

    public void setArmourPenalty(int penalty) {
        this.armourPenalty = penalty;
    }

    public void setTrained() {
        this.trained = true;
    }

    public void setMisc(int misc) {
        this.misc = misc;
    }


    public int compareToRollResult(RollResult rr) {
        // TODO

        return 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" : ");
        sb.append(this.getValue());
        sb.append(" | Trained : ");
        sb.append(this.trained);
        return sb.toString();
    }

    public String toString(int maxName, int maxValue) {
        StringBuilder sb = new StringBuilder();
        String value;
        int valueLength;
        sb.append(this.name);
        if (this.name.length() < maxName) {
            sb.append(StringUtility.spaces(maxName - this.name.length()));
        }
        sb.append(" : ");
        value = Integer.toString(this.getValue());
        valueLength = value.length();
        if (valueLength < maxValue) {
            sb.append(StringUtility.spaces(maxValue - valueLength));
        }
        sb.append(value);
        sb.append(" | Trained : ");
        sb.append(this.trained);
        return sb.toString();
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
        return this.getValue() + "";
    }

}
