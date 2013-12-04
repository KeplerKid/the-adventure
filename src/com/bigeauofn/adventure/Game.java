package com.bigeauofn.adventure;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.bigeauofn.adventure.models.Actor;

public class Game implements Actor.ActorHandler {

	public static int xMouseFudge = -8;
	public static int yMouseFudge = -30;

	public static int xScreenFudge = 16;
	public static int yScreenFudge = 38;
	public static int boardSideLength = 640;
	public static int infoAreaHeight = 100;

	private static World world;
	private static MenuPanel menu;
	private static Actor player;

	private JLabel actorName;
	private JLabel actorHP;
	private JButton attackBtn;
	
	public Game() {

		// initialize the players
		player = new Actor("actors/Tikquor.txt", this);
		Actor badGuy = new Actor("actors/Gobby.txt", this);

		// create the world
		world = new World();
		world.addActor(player);
		world.addActor(badGuy);
		
		// setup the frame
		JFrame gameFrame = new JFrame("The Adventure");
		gameFrame.setLayout(new BorderLayout());
		gameFrame.setSize(boardSideLength + xScreenFudge,
				boardSideLength + yScreenFudge + infoAreaHeight);

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameFrame.addMouseListener(world);
		gameFrame.add(world, BorderLayout.CENTER);

		// setup the menu panel
		actorName = new JLabel("Unknown");
		actorHP = new JLabel("-1");
		attackBtn = new JButton("Attack!");
		
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

}
