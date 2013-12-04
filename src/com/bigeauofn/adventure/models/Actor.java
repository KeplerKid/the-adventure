package com.bigeauofn.adventure.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Actor {

	public static interface ActorHandler {
		void onActorClicked(Actor actor);
	}

	private ActorHandler mHandler;
	public static final String BAD_GUY = "res/bad_guy.png";
	public static final String HERO = "res/hero.png";

	private String actorFilePath;
	private String name;
	private BufferedImage avatar;
	private int[] location;

	private int currentHP;
	private HashMap<String, String> stats;

	// private int STR, CON, DEX, INT, WIS, CHA, acrobatics, arcana, athletics,
	// bluff, diplomancy, dungeoneering, endurance, heal, history,
	// insight, intimidate, nature, perception, religion, stealth,
	// streetwise, thievery;

	public Actor(String name, String avatarPath, int[] location,
			ActorHandler handler) {
		this.mHandler = handler;
		this.name = name;
		this.avatar = loadImage(avatarPath);
		this.location = location;

	}

	public Actor(String filePath, ActorHandler handler) {
		mHandler = handler;

		actorFilePath = filePath;

		stats = StatFileParser.parseFile(actorFilePath);

		name = stats.get("actorname");
		avatar = loadImage(stats.get("avatar"));
		location = new int[] { 0, 0 };

		currentHP = getStatInteger("basehp");
		currentHP += 6 * (getStatInteger("level") - 1);
		currentHP += getStatInteger("con");

		System.out.println(currentHP);
		/*
		 * // Ability scores STR = getStatInteger("str"); CON =
		 * getStatInteger("con"); DEX = getStatInteger("dex"); INT =
		 * getStatInteger("int"); WIS = getStatInteger("wis"); CHA =
		 * getStatInteger("cha");
		 * 
		 * // Skill scores acrobatics = getStatInteger("acrobatics");
		 */
	}

	public int getStatInteger(String stat) {
		return Integer.parseInt(stats.get(stat));
	}

	public int getAttackModifiers(String attackAttribute) {
		int attackBonus = getStatInteger("level") / 2;
		attackBonus += (getStatInteger(attackAttribute) - 10) / 2;
		return attackBonus;
	}

	public int updateStatIntger(String stat, int amountToAdd) {
		int currentValue = getStatInteger(stat);
		int newValue = currentValue + amountToAdd;
		stats.put(stat, newValue + "");
		return newValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getAvatar() {
		return avatar;
	}

	public void setAvatar(BufferedImage avatar) {
		this.avatar = avatar;
	}

	public int[] getLocation() {
		return location;
	}

	/**
	 * Move the Character to the new position
	 * 
	 * @param newLoc
	 *            desired location
	 * @return updated position
	 */
	public int[] updatePosition(int[] newLoc) {

		location[0] += newLoc[0];
		location[1] += newLoc[1];
		return this.location;

	}

	private BufferedImage loadImage(String path) {

		BufferedImage im = null;
		try {
			System.out.println(path);
			im = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Error: Could not load image from: " + path);
		}
		return im;

	}

	public boolean equals(Object o) {
		Actor otherChar = (Actor) o;
		if (this.avatar.equals(otherChar.avatar)) {
			if (this.location[0] == otherChar.location[0]) {
				if (this.location[1] == otherChar.location[1]) {
					if (this.name.equals(otherChar.name)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isAtLocation(int[] tile) {
		boolean isAtLocation = false;
		if (tile[0] == location[0] && tile[1] == location[1]) {
			isAtLocation = true;
		} else {
			isAtLocation = false;
		}
		return isAtLocation;
	}

	public void select() {
		mHandler.onActorClicked(this);
	}

	public HashMap<String, String> getStats() {
		return stats;
	}

	public void setStats(HashMap<String, String> stats) {
		this.stats = stats;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

}
