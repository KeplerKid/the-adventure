package com.bigeauofn.adventure;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.bigeauofn.adventure.models.Power;
import com.bigeauofn.adventure.models.Actor;
import com.bigeauofn.adventure.models.Attack;
import com.bigeauofn.adventure.models.Weapon;

public class Game implements Actor.ActorHandler, ActionListener {

	private static final String ATTACK = "attackCmd";
	public static int xMouseFudge = -8;
	public static int yMouseFudge = -30;

	public static int xScreenFudge = 16;
	public static int yScreenFudge = 38;
	public static int boardSideLength = 640;
	public static int infoAreaHeight = 100;

	private static World world;
	private static MenuPanel menu;
	private static Actor player;
	private static Actor badGuy;
	
	private JLabel actorName;
	private JLabel actorHP;
	private JButton attackBtn;
	private JList<Power> abilityList;
	private JList<Weapon> weaponList;

	public Game() {

		// initialize the players
		player = new Actor("actors/Tikquor_full.txt", this);
//		System.out.println(player.toString());

		badGuy = new Actor("actors/Gobby.txt", this);
//		System.out.println(badGuy.toString());

		// create the world
		world = new World();
		world.addActor(player);
		world.addActor(badGuy);

		// setup the frame
		JFrame gameFrame = new JFrame("The Adventure");
		gameFrame.setLayout(new BorderLayout());
		gameFrame.setSize(boardSideLength, boardSideLength + infoAreaHeight);

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameFrame.addMouseListener(world);
		gameFrame.add(world, BorderLayout.CENTER);

		// setup the menu panel
		actorName = new JLabel("Unknown");
		actorHP = new JLabel("-1");

		attackBtn = new JButton("Attack!");
		attackBtn.setActionCommand(ATTACK);
		// Added an explicit actionListener
		attackBtn.addActionListener(this);

		menu = new MenuPanel();

		// menu.setSize(640, 600);
		menu.add(actorName);
		menu.add(actorHP);
		menu.add(attackBtn);
		
		weaponList = new JList<Weapon>();
		weaponList.setListData(world.getSelectedActor().getWeaponList());
		weaponList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		weaponList.setLayoutOrientation(JList.VERTICAL);
		weaponList.setVisibleRowCount(-1);
		JScrollPane weaponListScroller = new JScrollPane(weaponList);
		weaponListScroller.setPreferredSize(new Dimension(250, 80));
		
		abilityList = new JList<Power>();
		abilityList.setListData(world.getSelectedActor().getAbilityList());
		abilityList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		abilityList.setLayoutOrientation(JList.VERTICAL);
		abilityList.setVisibleRowCount(-1);
		JScrollPane abilityListScroller = new JScrollPane(abilityList);
		abilityListScroller.setPreferredSize(new Dimension(250, 80));
		
		menu.add(weaponList);
		menu.add(abilityList);
		gameFrame.add(menu, BorderLayout.SOUTH);

		gameFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void onActorClicked(Actor actor) {
		actorName.setText(actor.getName());
		actorHP.setText(actor.getCurrentHP() + "");
		
		menu.remove(weaponList);
		JList<Weapon> jList = new JList<Weapon>(actor.getWeaponList());
		weaponList = jList;
//		weaponList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		weaponList.setLayoutOrientation(JList.VERTICAL);
//		weaponList.setVisibleRowCount(-1);
//		weaponList.setSelectedIndex(0);
//		JScrollPane weaponListScroller = new JScrollPane(weaponList);
//		weaponListScroller.setPreferredSize(new Dimension(250, 80));
		
		menu.remove(abilityList);
		JList<Power>  jList2 = new JList<Power>(actor.getAbilityList());
		abilityList = jList2;
//		abilityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		abilityList.setLayoutOrientation(JList.VERTICAL);
//		abilityList.setVisibleRowCount(-1);
//		abilityList.setSelectedIndex(0);
//		JScrollPane abilityListScroller = new JScrollPane(abilityList);
//		abilityListScroller.setPreferredSize(new Dimension(250, 80));
		
		menu.add(weaponList);
		menu.add(abilityList);
		
		menu.repaint();
	}


	@Override
	public void onActorHitPointChange(Actor actor) {
		if (world.getSelectedActor().equals(actor)) {
			actorHP.setText(actor.getCurrentHP() + "");
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String actionCmd = actionEvent.getActionCommand();
		switch (actionCmd) {

		case ATTACK:

			if (world.getTarget() != null) {
				Actor attacker = world.getSelectedActor();

//				int[] targets = Attack.getPossibleTargets(attacker, world);

				// TODO handle Area of Effect attacks
				Actor target = world.getTarget();
				attacker.setEquipedWeapon(weaponList.getSelectedValue());
				
				attacker.setSelectedAbility(abilityList.getSelectedValue());
				Attack attack = new Attack(attacker, target);
				
				attack.resolve();
			}
			break;

		default:

		}
	}

}
