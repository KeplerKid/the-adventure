package com.bigeauofn.adventure.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StatFileParser {

	private HashMap<String, String> baseData;
	private HashMap<String, Item> items;
	private ArrayList<Weapon> weapons;
	private ArrayList<Power> abilities;
	private HashMap<String, AbilityScore> abilityScores;
	private HashMap<String, AbilityScore> notDoneYet;

	public StatFileParser() {

	}

	public HashMap<String, String> getBaseData() {
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

	public boolean isNewThing(String line) {
		return line.startsWith("[");
	}

	public void selectParser(HashMap<String, String> valueSet) {
		switch (valueSet.get("Thing")) {
		case "[base data]":
			parseBaseData(valueSet);
			break;
		case "[abilities]":
			parseAbilities(valueSet);
			break;
		case "[item]":
			parseItems(valueSet);
			break;
		case "[weapon]":
			parseWeapon(valueSet);
			break;
		case "[ability]":
			parseAbility(valueSet);
			break;
		case "[stats]":
			parseNotDoneYet(valueSet);
			break;
		case "[feat]":
			break;
		case "[condition]":
			break;
		default:
			break;
		}
	}

	private void parseNotDoneYet(HashMap<String, String> valueSet) {
		for (String s : valueSet.keySet()) {
			if (!s.equals("Thing")) {
				AbilityScore toPut = new AbilityScore(s, Integer.parseInt(valueSet.get(s)),
						"stat");
				this.getNotDoneYet().put(s, toPut);
			}
		}
		
	}

	private void parseAbilities(HashMap<String, String> valueSet) {
		for (String s : valueSet.keySet()) {
			if (!s.equals("Thing")) {
				AbilityScore toPut = new AbilityScore(s, Integer.parseInt(valueSet.get(s)),
						"stat");
				this.getAbilityScores().put(s, toPut);
			}
		}
	}

	private void parseBaseData(HashMap<String, String> baseData) {
		this.baseData = baseData;
	}

	private void parseItems(HashMap<String, String> newItem) {
		if (this.items == null) {
			this.items = new HashMap<String, Item>();
		}

		Item toPut = new Item(newItem.get("name"));
		this.items.put(toPut.getName(), toPut);

		for (String s : newItem.keySet()) {
			if (!s.equals("name") && !s.equals("type") && !s.equals("dice")
					&& !s.equals("Thing")) {

				toPut.addAttribute(s, Integer.parseInt(newItem.get(s)), "Item");

			} else if (s.equals("dice")) {

				String dice = newItem.get(s);
				int splitter = dice.indexOf("d");
				String value = dice.substring(splitter + 1, dice.length());
				toPut.addDice(Integer.parseInt(value));

			}
		}
	}

	public HashMap<String, String> parseFile(String path) {

		BufferedReader br = openFileForReading(path);

		try {
			String line;
			HashMap<String, String> items = new HashMap<String, String>();
			while ((line = br.readLine()) != null) {

				if (isNewThing(line)) {
					if (items.size() > 1) {
						selectParser(items);
					}
					// reset the items map
					items = new HashMap<String, String>();
					// only one "Thing" in each map, each item is a "Thing"
					items.put("Thing", line);

				} else {
					if (!line.startsWith(";") && line.contains("=")) {

						// add a new KV pair
						int splitter = line.indexOf("=");
						String key = line.substring(0, splitter);
						String value = line.substring(splitter + 1,
								line.length());
						items.put(key, value);

					} else {
						// do nothing, ; indicates comment
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.baseData;
	}

	private void parseWeapon(HashMap<String, String> weapon) {

		Weapon toPut = new Weapon(weapon);
		this.getWeapons().add(toPut);

	}

	private void parseAbility(HashMap<String, String> ability) {
		Power toPut = new Power(ability);
		this.getAbilities().add(toPut);

	}

	public HashMap<String, Item> getItems() {
		return items;
	}

	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}

	public ArrayList<Weapon> getWeapons() {
		if (this.weapons == null) {
			this.weapons = new ArrayList<Weapon>();
		}
		return weapons;
	}

	public ArrayList<Power> getAbilities() {
		if (this.abilities == null) {
			this.abilities = new ArrayList<Power>();
		}
		return this.abilities;
	}

	public HashMap<String, AbilityScore> getAbilityScores() {
		if (this.abilityScores == null) {
			this.abilityScores = new HashMap<String, AbilityScore>();
		}
		return abilityScores;
	}
	public HashMap<String,AbilityScore> getNotDoneYet(){
		if(this.notDoneYet == null){
			this.notDoneYet = new HashMap<String,AbilityScore>();
		}
		return this.notDoneYet;
	}

}
