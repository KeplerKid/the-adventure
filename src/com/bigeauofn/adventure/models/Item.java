package com.bigeauofn.adventure.models;
import java.util.ArrayList;
import java.util.HashMap;

import com.bigeauofn.adventure.dicebag.*;

public class Item {
	private String name;
	private HashMap<String, Attribute> attributes;
	private ArrayList<Dice> dice;
		
	public Item(String name){
		this.name = name;
		this.attributes = new HashMap<String, Attribute>();
		this.dice = new ArrayList<Dice>();
	}
	public String getName(){
		return this.name;
	}
	public void addAttribute(String name, int value, String type){
		System.out.println(value);
		Attribute toAdd = new Attribute(name, value,type);
		this.attributes.put(name, toAdd);
	}
	public void addDice(int numberOfSides){
		Dice toAdd = new Dice(numberOfSides);
		this.dice.add(toAdd);
	}
	public RollResult RollDice(){
		RollResult toReturn = null;
		for(Dice d : this.dice){
			if(toReturn == null){
				toReturn = d.rollDice();
			}else{
				toReturn.addResult(d.rollDice());
			}
		}
		toReturn.addModifier(this.getAttribute("damage").getValue());
		return toReturn;
	}
	public Attribute getAttribute(String name){
		return this.attributes.get(name);
	}
	
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		sb.append(" Has ");
		sb.append(this.attributes.size());
		sb.append(" Attributes\n");
		for(Attribute a : this.attributes.values()){
				sb.append(a.toString());
		}
		sb.append(" and Has ");		
		sb.append(this.dice.size());
		sb.append(" Dice\n");
		for(Dice d :this.dice){
			sb.append(d.toString());
		}
		return sb.toString();
	}
}
