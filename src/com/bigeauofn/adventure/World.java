package com.bigeauofn.adventure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.bigaeuofn.adventure.utilities.ImageUtility;
import com.bigeauofn.adventure.models.Actor;
import com.bigeauofn.adventure.models.Doodad;

public class World extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private int xTileSize = 78;
	private int yTileSize = 78;

	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private ArrayList<Doodad> doodads = new ArrayList<Doodad>();

	private Actor selectedActor;
	private Actor selectedTarget;

	private class MoveAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private int xDiff = 0;
		private int yDiff = 0;

		public MoveAction(int xDiff, int yDiff) {
			this.xDiff = xDiff;
			this.yDiff = yDiff;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateActorPosition(selectedActor, new int[] { xDiff, yDiff });
			repaint();
		}

	}

	public World() {
		setBackground(Color.DARK_GRAY);

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("typed a"), "moveLeft");
		getActionMap().put("moveLeft", new MoveAction(-1, 0));

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("typed d"), "moveRight");
		getActionMap().put("moveRight", new MoveAction(1, 0));

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("typed w"), "moveUp");
		getActionMap().put("moveUp", new MoveAction(0, -1));

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("typed s"), "moveDown");
		getActionMap().put("moveDown", new MoveAction(0, 1));
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
		
		BufferedImage img = ImageUtility.loadImage("res/tiles/grass.png");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				g.drawImage(img, i * xTileSize, j * yTileSize, null);
			}
		}
		for (Actor c : actors) {

			int xLoc = c.getLocation()[0];
			int yLoc = c.getLocation()[1];

			int xPixelLoc = xLoc * xTileSize;
			int yPixelLoc = yLoc * yTileSize;

			g.drawImage(c.getAvatar(), xPixelLoc, yPixelLoc, null);

			// draw a blue rectangle around the selectedActor
			if (this.selectedActor != null && c.equals(this.selectedActor)) {

				Color oldColor = g.getColor();
				g.setColor(Color.BLUE);
				g.drawRect(xPixelLoc, yPixelLoc, xTileSize, yTileSize);
				g.setColor(oldColor);

			}

			// draw a red rectangle around the selected target
			if (this.selectedTarget != null && c.equals(this.selectedTarget)) {

				Color oldColor = g.getColor();
				g.setColor(Color.RED);
				g.drawRect(xPixelLoc + 2, yPixelLoc + 2, xTileSize - 4,
						yTileSize - 4);
				g.setColor(oldColor);
			}

		}

		// consider doodads drawn OVER the characters
		// alternatively consider a "depth" value, with actors being closer to
		// the "surface" upon a tie

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


	/**
	 * Temporary solution. Desired implementation will be getTargets(). The
	 * method will return an ArrayList<Actor> that was within the area of the
	 * ability's targeted square.
	 * 
	 * @return
	 */
	public Actor getTarget() {
		if (this.selectedTarget == null) {
			System.out.println("no target was selected by " + this.getName());
			return null;
		}
		System.out.println(this.selectedTarget.getName()
				+ " was retreived as a target");
		return this.selectedTarget;
	}

	/**
	 * should be addTarget, this will better handle area attacks
	 * 
	 * @param a
	 */
	private void selectTarget(Actor a) {
		System.out.println(a.getName() + " was targeted");
		repaint();
		this.selectedTarget = a;
	}

	private void selectActor(Actor a) {
		selectedActor = a;
		repaint();
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
	
	/**
	 * left click will set the actor as "selected". Right click will set the
	 * actor as the "target". On my mouse (Logitech G5) the getButton() values
	 * are as follows: 1 is left click 2 is middle mouse 3 is right click 4 is
	 * close thumb 5 is far thumb
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

		int[] tile = convertMoiseCoordToTile(e);

		for (Actor a : actors) {
			if (a.isAtLocation(tile)) {

				if (e.getButton() == 1) {
					// left click logic
					selectActor(a);

				} else if (e.getButton() == 3) {
					// right click logic
					selectTarget(a);
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	
}
