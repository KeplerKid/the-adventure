package com.bigeauofn.adventure.models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.bigaeuofn.adventure.utilities.ImageUtility;
import com.bigeauofn.adventure.interfaces.GameObject;
import com.bigeauofn.adventure.interfaces.Targetable;
import com.bigeauofn.adventure.interfaces.Traversable;

/**
 * This class represents a single square in the World of the game. According to
 * the PHB, each Tile or square is equivalent to 5 feet. Targeting, Movement,
 * and Board layout will all utilize Tiles in their implementation.
 * 
 * A tile can contain a whole or part of a gameObject (actor, destructable,
 * readable, etc.), give and possess information about how traversable it is,
 * and be able to draw itself.
 * 
 * @author JimseyJoe
 * 
 */
public class Tile implements Targetable, Traversable {

	private String defaultBackgroundPath = "res/tiles/grass.png";

	private GameObject containedObject;

	private Image tileImage;
	private Point locationInWorld;

	public Tile(Point locationInWorld, String backgroundPath) {
		if (backgroundPath == null || backgroundPath.equals("")) {
			this.tileImage = ImageUtility.loadImage(defaultBackgroundPath);
		}
		this.locationInWorld = locationInWorld;
	}

	public void draw(Graphics g) {
		g.drawImage(getTileImage(), locationInWorld.x, locationInWorld.y, null);
	}

	private Image getTileImage() {
		if (this.tileImage == null) {
			this.tileImage = ImageUtility.loadImage(defaultBackgroundPath);
		}
		return this.tileImage;
	}

	public GameObject getContainedObject() {
		return containedObject;
	}

	public void setContainedObject(GameObject containedObject) {
		this.containedObject = containedObject;
	}

	public Point getLocationInWorld() {
		return locationInWorld;
	}

	public void setLocationInWorld(Point locationInWorld) {
		this.locationInWorld = locationInWorld;
	}
}
