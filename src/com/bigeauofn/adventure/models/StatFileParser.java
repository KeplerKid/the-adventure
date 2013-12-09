package com.bigeauofn.adventure.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StatFileParser {

	private static final String WEAPON = "[weapon]";
	
	public static BufferedReader openFileForReading(String fileNameAndPath) {
		// create the file reader
		FileReader fr = null;
		try {
			fr = new FileReader(fileNameAndPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new BufferedReader(fr);
	}
	
	public static boolean isNewThing(String line){
		return  line.startsWith("[");
	}
	public void selectParser(String line){
		switch (line){
			case "[Base Data]":
				break;
			case"[item]":
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
	
	public static HashMap<String, Object> parseFile(String path) {
		HashMap<String, Object> stats = new HashMap<String, Object>();
		
		BufferedReader br = openFileForReading(path);
		ArrayList<String> things = new ArrayList<String>();
		try {
			
			String line;
			HashMap<String,String> items;
			while ((line = br.readLine()) != null) {
				if(isNewThing(line)){
					items = new HashMap<String,String>();
					items.put("Thing", line);
				}else{
					if(!line.startsWith(";")){
						int splitter = line.indexOf("=");
						String key = line.substring(0, splitter);
						String value = line.substring(splitter+1, line.length());
					//	items.put(key, value);
					}
				}
				if (line.equals(WEAPON)) {
					Weapon weapon = parseWeapson(br);
				}
				parseValue(line, stats);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stats;
	}

	private static void parseValue(String line, HashMap<String, Object> stats) {
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
	private static Weapon parseWeapson(BufferedReader br) {
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
