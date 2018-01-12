package entities.mobs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import coreEngine.Game;

/**
 * the player
 * @author Graham
 *
 */
public class Player extends Mob{
	
	//stores the players image
	private BufferedImage player;
	//stores an instance of the game the player is in
	private Game game;

	/**
	 * to create a player for the game
	 * @param game the game the player is to be put in
	 * @param player a buffered image of the player to be rendered to the screen
	 * @param x the x position of the player on the screen
	 * @param y the y position of the player on the screen
	 */
	public Player(Game game, BufferedImage player, float x, float y) {
		super(x, y, Mob.STANDARD_DIAMETER, Mob.STANDARD_DIAMETER);
		this.player = player;
	}

	/*
	 * (non-Javadoc)
	 * @see entities.Entity#tick()
	 */
	@Override
	public void tick() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see entities.Entity#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		//draws the player image on the screen
		g.drawImage(player, (int) x, (int) y, width, height, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entities.mobs.Mob#move(float, float)
	 */
	public void move(float dx, float dy){
		//moves the player the passed in x and y displacements
		super.move(dx, dy);
                
	}
}
