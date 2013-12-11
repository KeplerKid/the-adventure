package com.bigeauofn.adventure;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.bigeauofn.adventure.models.Actor;
import com.bigeauofn.adventure.models.Attack;

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
		System.out.println(player.toString());

		badGuy = new Actor("actors/Gobby.txt", this);
		System.out.println(badGuy.toString());

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

				// Weapon weapon = attacker.getEquipedWeapon();

				Attack attack = new Attack(attacker, target);
				attack.resolve();
			}
			break;

		default:

		}
	}

	private Actor displayTargetSelector(ArrayList<Actor> targetChoices) {
		Actor target = null;

		return target;
	}

}
