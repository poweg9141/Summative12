package entities.mobs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import coreEngine.Game;
import coreEngine.GameVariables;
import graphics.Camera;
import graphics.SpriteSheet;
import java.awt.Color;
import utils.Time;

/**
 * the player
 *
 * @author Graham
 *
 */
public class Player extends Mob {

    //stores the players image
    private BufferedImage player;
    private BufferedImage[] player_left, player_right;
    //stores an instance of the game the player is in
    private Game game;
    private float dx, dy;
    private int index;
    private long lastTime;
    private double desiredTimeBetweenFrame = 0.2;

    /**
     * to create a player for the game
     *
     * @param game the game the player is to be put in
     * @param player a buffered image of the player to be rendered to the screen
     * @param x the x position of the player on the screen
     * @param y the y position of the player on the screen
     */
    public Player(Game game, SpriteSheet playerSheet, float x, float y) {
        super(game, x, y, Mob.STANDARD_DIAMETER, Mob.STANDARD_DIAMETER);
        player_left = new BufferedImage[2];
        player_left[0] = playerSheet.getSpriteAtIndex(2);
        player_left[1] = playerSheet.getSpriteAtIndex(3);
        player_right = new BufferedImage[2];
        player_right[0] = playerSheet.getSpriteAtIndex(0);
        player_right[1] = playerSheet.getSpriteAtIndex(1);
        player = player_right[0];
        index = 1;
        this.game = game;
        bounds.setBounds(24, 45, 14, 19);
        health = 100;
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
        if(lastTime == 0){
            lastTime = Time.getCurrentTime();
        }
        if(Time.hasBeenTime(lastTime, desiredTimeBetweenFrame)){            
            lastTime = Time.getCurrentTime();
            if(index == 0){
                index = 1;
            }else{
                index = 0;
            }
        }
        if(dx < 0){
            player = player_left[index];
        }else if(dx > 0){
            player = player_right[index];
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
        if(game.getWorld().getTileAtPosition((int) ((x + bounds.x)/GameVariables.getSTANDARD_TILE_DIAMETER()), 
                (int)((y + bounds.y)/GameVariables.getSTANDARD_TILE_DIAMETER())).getId() == 6){
            dx *= 0.3;
            dy *= 0.3;
        }
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
