package entities.mobs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import coreEngine.Game;
import graphics.Camera;
import java.awt.Color;

/**
 * the player
 *
 * @author Graham
 *
 */
public class Player extends Mob {

    //stores the players image
    private BufferedImage player;
    //stores an instance of the game the player is in
    private Game game;
    private float dx, dy;

    /**
     * to create a player for the game
     *
     * @param game the game the player is to be put in
     * @param player a buffered image of the player to be rendered to the screen
     * @param x the x position of the player on the screen
     * @param y the y position of the player on the screen
     */
    public Player(Game game, BufferedImage player, float x, float y) {
        super(game, x, y, Mob.STANDARD_DIAMETER, Mob.STANDARD_DIAMETER);
        this.player = player;
        this.game = game;
        bounds.setBounds(24, 45, 14, 19);
        health = 1000;
    }

    /*
     * (non-Javadoc)
     * @see entities.Entity#tick()
     */
    @Override
    public void tick() {
        if (!isDead()) {
            game.getCamera().framEntity(this);
        }else{
            handleDeath();
        }
    }

    /*
     * (non-Javadoc)
     * @see entities.Entity#render(java.awt.Graphics)
     */
    @Override
    public void render(Graphics g) {
        //draws the player image on the screen
        g.drawImage(player, (int) (x + game.getCamera().getxOff()), (int) (y + game.getCamera().getyOff()), width, height, null);
    }

    /*
     * (non-Javadoc)
     * @see entities.mobs.Mob#move(float, float)
     */
    public void move(float dx, float dy) {
        //moves the player the passed in x and y displacements
        super.move(dx, dy);
        this.dx = dx;
        this.dy = dy;
    }    

    public float getXDisplacement() {
        return dx;
    }

    public float getYDisplacement() {
        return dy;
    }
}
