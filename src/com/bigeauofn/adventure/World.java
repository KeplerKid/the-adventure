package com.bigeauofn.adventure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.bigeauofn.adventure.models.Actor;
import com.bigeauofn.adventure.models.Doodad;

public class World extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private int xTileSize = 80;
	private int yTileSize = 80;

	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private ArrayList<Doodad> doodads = new ArrayList<Doodad>();
	private Actor selectedActor;

	private class MoveActionYay extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private int xDiff = 0;
		private int yDiff = 0;
		
		public MoveActionYay(int xDiff, int yDiff) {
			this.xDiff = xDiff;
			this.yDiff = yDiff;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("yay");
			System.out.println(xDiff);
			System.out.println(yDiff);
			updateActorPosition(selectedActor, new int[] { xDiff, yDiff });
			repaint();
		}

	}

	public World() {
		setBackground(Color.DARK_GRAY);
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("typed a"), "moveLeft");
		getActionMap().put("moveLeft", new MoveActionYay(-1, 0));
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("typed d"), "moveRight");
		getActionMap().put("moveRight", new MoveActionYay(1, 0));
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("typed w"), "moveUp");
		getActionMap().put("moveUp", new MoveActionYay(0, -1));
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("typed s"), "moveDown");
		getActionMap().put("moveDown", new MoveActionYay(0, 1));
	}

	public ArrayList<Actor> getCharacters() {
		return actors;
	}

	public void setCharacters(ArrayList<Actor> characters) {
		this.actors = characters;
	}

	public ArrayList<Doodad> getDoodads() {
		return doodads;
	}

	public void setDoodads(ArrayList<Doodad> doodads) {
		this.doodads = doodads;
	}

	public void addActor(Actor c) {
		actors.add(c);
		if (actors.size() == 1) {
			selectedActor = actors.get(0);
		}
	}

	public void removeCharacter(Actor c) {
		actors.remove(c);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawGrid(g);

		// for (Doodad d : doodads) {
		// g.drawImage(d.getAvatar(), d.getLocation()[0],
		// d.getLocation()[1], null);
		// }

		for (Actor c : actors) {

			int xLoc = c.getLocation()[0];
			int yLoc = c.getLocation()[1];

			// System.out.println("Drawing " + c.getName() + " at " + xLoc *
			// xTileSize + ", " + yLoc + yTileSize);
			g.drawImage(c.getAvatar(), xLoc * xTileSize, yLoc * yTileSize, null);

		}

		// consider doodads drawn OVER the characters

	}

	private void drawGrid(Graphics g) {

		int squareLength = getWidth();

		for (int i = 0; i < squareLength; i += squareLength / 8) {
			g.drawLine(0, i, squareLength, i);
			g.drawLine(i, 0, i, squareLength);
		}
	}

	public void updateActorPosition(Actor actor, int[] positionUpdate) {

		int[] destination = new int[2];
		destination[0] = actor.getLocation()[0];
		destination[1] = actor.getLocation()[1];

		destination[0] += positionUpdate[0];
		destination[1] += positionUpdate[1];

		boolean destinationAvailable = true;

		for (Actor a : actors) {
			if (a.isAtLocation(destination)) {
				destinationAvailable = false;
			}
		}

		if (destinationAvailable) {
			selectedActor.updatePosition(positionUpdate);
		} else {
			System.out.println("Destination is occupied, cannot move there");
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("mouse clicked");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// System.out.println("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// System.out.println("mouse exited");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int[] tile = convertMoiseCoordToTile(e);
		for (Actor a : actors) {
			if (a.isAtLocation(tile)) {
				selectActor(a);
			}
		}
	}

	private void selectActor(Actor a) {
		selectedActor = a;
		a.select();
	}

	public Actor getSelectedActor() {
		return selectedActor;
	}

	public void setSelectedActor(Actor selcetedActor) {
		this.selectedActor = selcetedActor;
	}

	private int[] convertMoiseCoordToTile(MouseEvent e) {
		int[] tileLoc = new int[2];

		tileLoc[0] = (e.getX() + Game.xMouseFudge) / (getWidth() / 8);
		tileLoc[1] = (e.getY() + Game.yMouseFudge) / (getWidth() / 8);

		System.out.println(tileLoc[0] + ", " + tileLoc[1]);

		return tileLoc;
	}

}
