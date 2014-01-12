package com.bigeauofn.adventure.models;

import com.bigeauofn.adventure.dicebag.Dice;
import com.bigeauofn.adventure.dicebag.RollResult;

import java.util.ArrayList;
import java.util.HashMap;

public class Item {
    private String name;
    private HashMap<String, AbilityScore> attributes;
    private ArrayList<Dice> dice;

    public Item(String name) {
        this.name = name;
        this.attributes = new HashMap<>();
        this.dice = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void addAttribute(String name, int value, String type) {
        AbilityScore toAdd = new AbilityScore(name, value, type);
        this.attributes.put(name, toAdd);
    }

    public void addDice(int numberOfSides) {
        Dice toAdd = new Dice(numberOfSides);
        this.dice.add(toAdd);
    }

    public RollResult rollDice() {
        RollResult toReturn = null;
        for (Dice d : this.dice) {
            if (toReturn == null) {
                toReturn = d.rollDice();
            } else {
                toReturn.addResult(d.rollDice());
            }
        }
        toReturn.addModifier(this.getAttribute("damage").getValue());
        return toReturn;
    }

    public AbilityScore getAttribute(String name) {
        return this.attributes.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" Has ");
        sb.append(this.attributes.size());
        sb.append(" Attributes\n");
        for (AbilityScore a : this.attributes.values()) {
            sb.append("\t");
            sb.append(a.toString());
        }
        sb.append(" and Has ");
        sb.append(this.dice.size());
        sb.append(" Dice\n");
        for (Dice d : this.dice) {
            sb.append(d.toString());
        }
        return sb.toString();
    }
}
