package com.bigeauofn.adventure.models;

import java.awt.image.BufferedImage;

import com.bigeauofn.adventure.dicebag.*;

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

	private Weapon equipedWeapon;
	
	private HitPoints currentHP;
	private HashMap<String, String> stats;
	private HashMap<Integer, Dice> diceSet;	

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
		diceSet = DiceFactory.getDiceSet();
	}

	public Actor(String filePath, ActorHandler handler) {
		mHandler = handler;
int yy = Integer.parseInt("+2");
		actorFilePath = filePath;

		stats = StatFileParser.parseFile(actorFilePath);

		name = stats.get("actorname");
		avatar = loadImage(stats.get("avatar"));
		location = new int[] { 0, 0 };

		currentHP = new HitPoints(getStatInteger("basehp"));
		currentHP.addtHitPoints(new HitPoints(6 * (getStatInteger("level") - 1)));
		currentHP.addtHitPoints(new HitPoints(getStatInteger("con")));
		diceSet = DiceFactory.getDiceSet();

	}
	
	public RollResult rollDice(Integer i) {
		return this.diceSet.get(i).rollDice();
	}
	
	public void equipWeapon(Weapon weapon) {
		equipedWeapon = weapon;
	}
	
	public Weapon getEquipedWeapon() {
		return equipedWeapon;
	}

	public void setEquipedWeapon(Weapon equipedWeapon) {
		this.equipedWeapon = equipedWeapon;
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



	public HitPoints getCurrentHP() {
		return currentHP;
	}

	public Weapon getWeapon() {
		return null;
	}

	public boolean isHitByAttack(AttackRoll attackRoll, String defense) {
		return attackRoll.getAttackTotal() >= this.getStatInteger(defense); 
	}
	public void takeDamage(DamageRoll damageDealt) {
		this.currentHP.subtractHitPoints(damageDealt.getDamage());
		// updateUI
		System.out.println(this.name + ": " + currentHP);
	}

}
