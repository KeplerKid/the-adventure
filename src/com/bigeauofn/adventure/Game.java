package com.bigeauofn.adventure;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.bigeauofn.adventure.models.Ability;
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

	public Game() {

		// initialize the players
		player = new Actor("actors/Tikquor_full.txt", this);
		badGuy = new Actor("actors/Tikquor_full.txt", this);

		// create the world
		world = new World();
		world.addActor(player);
		world.addActor(badGuy);

		// setup the frame
		JFrame gameFrame = new JFrame("The Adventure");
		gameFrame.setLayout(new BorderLayout());
		gameFrame.setSize(boardSideLength + xScreenFudge, boardSideLength
				+ yScreenFudge + infoAreaHeight);

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameFrame.addMouseListener(world);
		gameFrame.add(world, BorderLayout.CENTER);

		// setup the menu panel
		actorName = new JLabel("Unknown");
		actorHP = new JLabel("-1");
		
		attackBtn = new JButton("Attack!");
		attackBtn.setActionCommand(ATTACK);
		//Added an explicit actionListener
		attackBtn.addActionListener(this);

		menu = new MenuPanel();
		menu.add(actorName);
		menu.add(actorHP);
		menu.add(attackBtn);

		gameFrame.add(menu, BorderLayout.SOUTH);

		gameFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void onActorClicked(Actor actor) {
		System.out.println("hi");
		actorName.setText(actor.getName());
		actorHP.setText(actor.getCurrentHP() + "");
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String actionCmd = actionEvent.getActionCommand();
		System.out.println("A button was clicked");
		switch (actionCmd) {

		case ATTACK:
			
			Actor attacker = world.getSelectedActor();
			
			Ability ability = new Ability("str", "ac", 1, null, null, true, true, 1, -1);
			
			Weapon weapon = new Weapon("Greataxe +1", 2, 1, 1, 12, 2, 1);
			
			int[] targets = Attack.getPossibleTargets(attacker, ability, weapon, world);

			// TODO handle Area of Effect attacks
			Actor target = badGuy;
			
			
//			Weapon weapon = attacker.getEquipedWeapon();
			
			Attack attack = new Attack(attacker, target,
					ability, weapon);
			attack.resolve();
			break;

		default:

		}
	}

	private Actor displayTargetSelector(ArrayList<Actor> targetChoices) {
		Actor target = null;
		
		return target;
	}
	

}
