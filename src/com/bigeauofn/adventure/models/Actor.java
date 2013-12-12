package com.bigeauofn.adventure.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.bigeauofn.adventure.dicebag.AttackRoll;
import com.bigeauofn.adventure.dicebag.DamageRoll;
import com.bigeauofn.adventure.dicebag.Dice;
import com.bigeauofn.adventure.dicebag.DiceFactory;
import com.bigeauofn.adventure.dicebag.HitPoints;
import com.bigeauofn.adventure.dicebag.RollResult;

public class Actor {

	public static interface ActorHandler {
		void onActorClicked(Actor actor);
		void onActorHitPointChange(Actor actor);
	}

	private ActorHandler mHandler;
	public static final String BAD_GUY = "res/bad_guy.png";
	public static final String HERO = "res/hero.png";

	private String actorFilePath;
	private String name;
	private BufferedImage avatar;
	private int[] location;

	private Weapon equipedWeapon;
	private Ability selectedAbility;

	private HitPoints currentHP;
	private HashMap<String, String> stats;
	private HashMap<Integer, Dice> diceSet;
	private ArrayList<Weapon> weapons;
	private ArrayList<Ability> abilities;

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
		this.diceSet = DiceFactory.getDiceSet();
	}

	public Actor(String filePath, ActorHandler handler) {
		this.mHandler = handler;
		this.actorFilePath = filePath;
		StatFileParser fp = new StatFileParser();
		this.stats = fp.parseFile(this.actorFilePath);

		this.name = stats.get("name").toString();
		this.avatar = loadImage(stats.get("avatar").toString());
		this.location = new int[] { 0, 0 };

		this.currentHP = new HitPoints(getStatInteger("basehp"));
		this.currentHP.addtHitPoints(new HitPoints(
				6 * (getStatInteger("level") - 1)));
		this.currentHP.addtHitPoints(new HitPoints(getStatInteger("con")));
		this.diceSet = DiceFactory.getDiceSet();

		this.weapons = fp.getWeapons();
		this.abilities = fp.getAbilities();

	}

	public RollResult rollDice(Integer i) {
		return this.diceSet.get(i).rollDice();
	}

	public void equipWeapon(Weapon weapon) {
		this.equipedWeapon = weapon;
	}

	public Weapon getEquipedWeapon() {
		return this.equipedWeapon;
	}

	public void setEquipedWeapon(Weapon equipedWeapon) {
		this.equipedWeapon = equipedWeapon;
	}

	public Ability getAbility() {
		return this.selectedAbility;
	}

	public RollResult InflictDamage(RollResult d20result) {

		RollResult damageRolls = null;

		Weapon toUse = this.getEquipedWeapon();

		if (!d20result.isCritical()) {

			damageRolls = toUse.rollWeaponDice();

		} else {
			// max damage
			damageRolls = toUse.getCriticalDamage();
		}

		// add damage modifiers
		int damage = toUse.getEnhancementLevel();
		// damage += (this.getStatInteger(this.getAbility().getSource()) - 10) /
		// 2;
		damageRolls.addModifier(damage);

		System.out.println(damageRolls + " damage dealt");
		return damageRolls;
	}

	public int getStatInteger(String stat) {
		return Integer.parseInt(this.stats.get(stat).toString());
	}

	public RollResult getAttackModifiers(RollResult attackRoll) {
		int attackBonus = getStatInteger("level") / 2;
		attackRoll.addModifier(attackBonus);
		
		attackRoll = this.selectedAbility.getAbilityAttackBonuses(this, attackRoll);
		
		return attackRoll;
	}

	public int updateStatIntger(String stat, int amountToAdd) {
		int currentValue = getStatInteger(stat);
		int newValue = currentValue + amountToAdd;
		this.stats.put(stat, newValue + "");
		return newValue;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getAvatar() {
		return this.avatar;
	}

	public void setAvatar(BufferedImage avatar) {
		this.avatar = avatar;
	}

	public int[] getLocation() {
		return this.location;
	}

	/**
	 * Move the Character to the new position
	 * 
	 * @param newLoc
	 *            desired location
	 * @return updated position
	 */
	public int[] updatePosition(int[] newLoc) {

		this.location[0] += newLoc[0];
		this.location[1] += newLoc[1];
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

	// TODO decide if this is enough criteria
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
		this.mHandler.onActorClicked(this);
	}

	public HashMap<String, String> getStats() {
		return this.stats;
	}

	public void setStats(HashMap<String, String> stats) {
		this.stats = stats;
	}

	public HitPoints getCurrentHP() {
		return this.currentHP;
	}

	public boolean isHitByAttack(AttackRoll attackRoll, String defense) {
		return attackRoll.getAttackTotal() >= this.getStatInteger(defense);
	}

	public void takeDamage(DamageRoll damageDealt) {
		this.currentHP.subtractHitPoints(damageDealt.getDamage());

		// update UI
		this.mHandler.onActorHitPointChange(this);
		
		System.out.println(this.name + ": " + currentHP);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("------------------------------------------------------\n");
		sb.append("Actor named - ");
		sb.append(this.name);
		sb.append("\n");

		sb.append("\n");
		sb.append("Current Hit Points - ");
		sb.append(this.currentHP);
		sb.append("\n");
		sb.append("Stat block:");
		for (String s : this.stats.keySet()) {
			sb.append("\n\t" + s + ":\t" + this.stats.get(s));
		}

		sb.append("\nWeapons Count - ");
		sb.append(this.weapons.size());
		for (Weapon w : this.weapons) {
			sb.append("\n\t" + w.toString());
		}

		sb.append("\n\nPlayers Dice count - ");
		sb.append(this.diceSet.size());
		for (Integer i : this.diceSet.keySet()) {
			sb.append("\n\t" + this.diceSet.get(i).toString());
		}
		sb.append("\n");
		sb.append("Abilities Count - ");
		sb.append(this.abilities.size());
		for (Ability a : this.abilities) {
			sb.append("\n\t" + a.toString());
		}
		sb.append("------------------------------------------------------\n");
		return sb.toString();
	}

	
	/**
	 * used for creation of JList in the UI
	 * @return
	 */
	public Object[] getWeaponList() {
		for (Weapon w : weapons) {
			System.out.println(w);
		}
		return weapons.toArray();
	}

	/**
	 * used for creation of JList in the UI
	 * @return
	 */	
	public Object[] getAbilityList() {
		for (Ability a : abilities) {
			System.out.println(a);
		}
		return abilities.toArray();
	}

	public void setSelectedAbility(Ability selectedValue) {
		this.selectedAbility = selectedValue;
	}

	public Ability getSelectedAbility() {
		return selectedAbility;
	}

}
