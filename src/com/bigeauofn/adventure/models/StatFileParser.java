package com.bigeauofn.adventure.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class StatFileParser {

	private HashMap<String, String> baseData;
	private HashMap<String, Item> items;

	public StatFileParser(){
		
	}
	
	public HashMap<String, String> getBaseData(){
		return this.baseData;
	}
	
	private BufferedReader openFileForReading(String fileNameAndPath) {
		// create the file reader
		FileReader fr = null;
		try {
			fr = new FileReader(fileNameAndPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new BufferedReader(fr);
	}
	
	public boolean isNewThing(String line){
		return  line.startsWith("[");
	}
	public void selectParser(HashMap<String, String> items){
		switch (items.get("Thing")){
			case "[Base Data]":
				parseBaseData(items);
				break;
			case"[item]":
				parseItems(items);
				break;
			case "[effect]":
				break;
			case "[feat]":
				break;
			case "[condition]":
				break;
			default:
				break;
		}
	}
	private void parseBaseData(HashMap<String, String> baseData){
		this.baseData = baseData;
	}
	private void parseItems(HashMap<String, String> newItem){
		if(this.items == null){
			this.items = new HashMap<String, Item>();
		}
		Item toPut = new Item(newItem.get("name"));
		this.items.put(toPut.getName(), toPut);
		for(String s :newItem.keySet()){
			if(!s.equals("name") && !s.equals("type") && !s.equals("dice") && !s.equals("Thing")){
				System.out.println(s + " = " + Integer.parseInt(newItem.get(s)));
				toPut.addAttribute(s, Integer.parseInt(newItem.get(s)), "Item");
			}else if(s.equals("dice")){
				String dice = newItem.get(s);
				int splitter = dice.indexOf("d");
				String value = dice.substring(splitter+1, dice.length());
				toPut.addDice(Integer.parseInt(value));
			}
		}
		System.out.println(toPut.toString());
	}
	
	
	public HashMap<String, String> parseFile(String path) {
		
		BufferedReader br = openFileForReading(path);
		
		try {
			String line;
			HashMap<String,String> items = new HashMap<String,String>();
			while ((line = br.readLine()) != null) {

				if(isNewThing(line)){
					if(items != null && items.size() > 1){
					selectParser(items);
					}
					items = new HashMap<String,String>();
					items.put("Thing", line);
				}else{
					if(!line.startsWith(";") && line.contains("=")){
						int splitter = line.indexOf("=");
						String key = line.substring(0, splitter);
						String value = line.substring(splitter+1, line.length());
						items.put(key, value);
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.baseData;
	}

	private void parseValue(String line, HashMap<String, Object> stats) {
		int splitter = line.indexOf("=");
		String key = line.substring(0, splitter);
		String value = line.substring(splitter+1, line.length());
		
		boolean isANumber = false;
		int existingValue = 0;
		
		if (stats.get(key) != null) {
			try {
				existingValue = Integer.parseInt(stats.get(key).toString());
				isANumber = true;
			} catch (NumberFormatException e) {
			}
		}
		
		if (isANumber) {
			int newValue = Integer.parseInt(value) + existingValue;
			stats.put(key, newValue + "");
		} else  {
			stats.put(key, value);
		}
		System.out.println(key + " " + value);
	}
	
	// TODO
	private Weapon parseWeapson(BufferedReader br) {
		Weapon weapon = null;
		String line;
		try {
			while ((line = br.readLine()) != null) {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weapon;
	}
}
