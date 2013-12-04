package com.bigeauofn.adventure.models;

public class Player extends Actor {

	public Player(String name, ActorHandler mHandler) {
		super(name, Actor.HERO, new int[] {0, 0}, mHandler);
	}

	
}
