package com.bigeauofn.adventure.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.bigaeuofn.adventure.utilities.ImageUtility;
import com.bigeauofn.adventure.dicebag.AttackRoll;
import com.bigeauofn.adventure.dicebag.DamageRoll;
import com.bigeauofn.adventure.dicebag.Dice;
import com.bigeauofn.adventure.dicebag.DiceFactory;
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
	private Power selectedAbility;

	private HitPoints currentHP;
	private HashMap<String, String> baseData;
	private HashMap<String, AbilityScore> notDoneYet;
	private AbilityScores statBlock;
	private Skills skillBlock;
	
	private HashMap<Integer, Dice> diceSet;
	private ArrayList<Weapon> weapons;
	private ArrayList<Power> abilities;
	private FeatCollection feats;
	
	// private int STR, CON, DEX, INT, WIS, CHA, acrobatics, arcana, athletics,
	// bluff, diplomancy, dungeoneering, endurance, heal, history,
	// insight, intimidate, nature, perception, religion, stealth,
	// streetwise, thievery;

	public Actor(String name, String avatarPath, int[] location,
			ActorHandler handler) {
		this.mHandler = handler;
		this.name = name;
		this.avatar = ImageUtility.loadImage(avatarPath);
		this.location = location;
		this.diceSet = DiceFactory.getDiceSet();
	}

	public Actor(String filePath, ActorHandler handler) {
		
		this.mHandler = handler;
		this.actorFilePath = filePath;
		
		StatFileParser fp = new StatFileParser();
		
		this.baseData = fp.parseFile(this.actorFilePath);
		this.statBlock = new AbilityScores(fp.getAbilityScores());
		//Just used to test what in the abilities scores
		//System.out.println(this.statBlock.toString());
		this.weapons = fp.getWeapons();
		this.abilities = fp.getAbilities();
		this.feats = new FeatCollection();
		
		
		this.name = baseData.get("name").toString();
		this.avatar = ImageUtility.loadImage(baseData.get("avatar").toString());
		this.location = new int[] { 0, 0 };

		
		this.notDoneYet = fp.getNotDoneYet();
		this.currentHP = new HitPoints(this.notDoneYet.get("basehp").getValue());
		this.currentHP.addtHitPoints(new HitPoints(
				6 * (this.notDoneYet.get("level").getValue() - 1)));

		this.currentHP.addtHitPoints(new HitPoints(getAbilityScore("con").getValue()));
		this.diceSet = DiceFactory.getDiceSet();

		this.skillBlock = new Skills(this);
		this.skillBlock.addTrainedSkills(fp.getTrainedSkills());
		System.out.println(this.skillBlock.toString());
		this.testFeats();
	}

	private void testFeats() {
		System.out.println("feat 1");
		Feat f1 = new Feat("stat", "speed", 6 + "");
		if (f1.meetsRequirements(this))
			feats.addFeat(f1);
		
		System.out.println("feat 2");
		Feat f2 = new Feat("stat", "speed", 7 + "");
		if (f2.meetsRequirements(this))
			feats.addFeat(f2);
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

	public Power getAbility() {
		return this.selectedAbility;
	}

	public RollResult inflictDamage(RollResult d20result) {

		RollResult damageRolls = null;

		Weapon toUse = this.getEquipedWeapon();

		if (!d20result.isCritical()) {

			damageRolls = this.selectedAbility.hit(this);

		} else {
			// max damage
			damageRolls = toUse.getCriticalDamage();
		}

		// add damage modifiers
		int damage = toUse.getEnhancementLevel();
		
		damageRolls.addModifier(damage);
		
		// TODO onDamage()

		System.out.println(damageRolls + " damage dealt");
		return damageRolls;
	}

	public AbilityScore getAbilityScore(String abilityName) {
		return this.statBlock.getAbilityScore(abilityName);
	}

	public RollResult getAttackModifiers(RollResult attackRoll) {
		int attackBonus = this.notDoneYet.get("level").getValue() / 2;
		
		attackRoll.addModifier(attackBonus);
		
		attackRoll = this.selectedAbility.getAbilityAttackBonuses(this, attackRoll);
		
		// TODO onAttack()
		
		return attackRoll;
	}

	public int updateStatIntger(String stat, int amountToAdd) {
		int currentValue = getAbilityScore(stat).getValue();
		int newValue = currentValue + amountToAdd;
		this.baseData.put(stat, newValue + "");
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

		// TODO onMove()?
		this.location[0] += newLoc[0];
		this.location[1] += newLoc[1];
		return this.location;

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
		return this.baseData;
	}

	public void setStats(HashMap<String, String> stats) {
		this.baseData = stats;
	}

	public HitPoints getCurrentHP() {
		return this.currentHP;
	}

	public boolean isHitByAttack(AttackRoll attackRoll, String defense) {
		
		// TODO onDefense()
		return attackRoll.getAttackTotal() >= this.notDoneYet.get(defense).getValue();
	}

	public void takeDamage(DamageRoll damageDealt) {
		this.currentHP.subtractHitPoints(damageDealt.getDamage());

		// update UI
		this.mHandler.onActorHitPointChange(this);
		
		// TODO onTakeDamage();
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
		for (String s : this.baseData.keySet()) {
			sb.append("\n\t" + s + ":\t" + this.baseData.get(s));
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
		for (Power a : this.abilities) {
			sb.append("\n\t" + a.toString());
		}
		sb.append("------------------------------------------------------\n");
		return sb.toString();
	}

	
	/**
	 * used for creation of JList in the UI
	 * @return
	 */
	public Vector<Weapon> getWeaponList() {
		return new Vector<Weapon>(weapons);
	}

	/**
	 * used for creation of JList in the UI
	 * @return
	 */	
	public Vector<Power> getAbilityList() {
		return new Vector<Power>(abilities);
	}

	public void setSelectedAbility(Power selectedValue) {
		this.selectedAbility = selectedValue;
	}

	public Power getSelectedAbility() {
		return selectedAbility;
	}
	public HashMap<String, AbilityScore> getNotDoneYet(){
		return this.notDoneYet;
	}

	public D20C getCompareObject(String type, String key) {
		switch(type) {
			case "stat":
				return this.notDoneYet.get(key);
			case "ability":
				return this.statBlock.getAbilityScore(key);
			case "skill":
				// TODO
				return null;
			case "defense":
				// TODO
				return null;
			default:
				break;
		}
		return this.statBlock.getAbilityScore(key);
	}
}
