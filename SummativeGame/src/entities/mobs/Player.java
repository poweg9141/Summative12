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
 * @author Graham, Michael
 *
 */
public class Player extends Mob {

    //stores the players image
    private BufferedImage player;
    private BufferedImage[] player_left, player_right;
    //stores an instance of the game the player is in
    private Game game;
    //stores the x and y displacement the player moved the last frame
    private float dx, dy;
    //stores the index of the sprite sheet the players current image is at
    private int index;
    //stores the last time the index updated
    private long lastTime;
    //stores the desired time in seconds between each players image switch
    private double desiredTimeBetweenFrame = 0.2;

    /**
     * to create a player for the game
     *
     * @param game the game the player is to be put in
     * @param playerSheet the sprite sheet the players image are in
     * @param x the x position of the player on the screen
     * @param y the y position of the player on the screen
     */
    public Player(Game game, SpriteSheet playerSheet, float x, float y) {
        super(game, x, y, Mob.STANDARD_DIAMETER, Mob.STANDARD_DIAMETER);
        //stores the players left and right images from the sprite sheet into the left and right arrays
        player_left = new BufferedImage[2];
        player_left[0] = playerSheet.getSpriteAtIndex(2);
        player_left[1] = playerSheet.getSpriteAtIndex(3);
        player_right = new BufferedImage[2];
        player_right[0] = playerSheet.getSpriteAtIndex(0);
        player_right[1] = playerSheet.getSpriteAtIndex(1);
        player = player_right[0];
        //sets the initial index to 1
        index = 1;
        //stores the game instance
        this.game = game;
        //sets the players bounding box, to the displacement in from the image the player starts at, and the width and height of the player
        bounds.setBounds(24, 45, 14, 19);
        //sets the players health to 100
        health = 100;
    }

    /*
     * (non-Javadoc)
     * @see entities.Entity#tick()
     */
    @Override
    public void tick() {
        //if the player os not dead, frame the camera around the player
        if (!isDead()) {
            game.getCamera().framEntity(this);
        }else{
            //if the player is dead, handles their death
            handleDeath();
        }
        //sets the last time if it hasnt already been set
        if(lastTime == 0){
            lastTime = Time.getCurrentTime();
        }
        //if it has been the desired time between the players image switch, switch the image
        if(Time.hasBeenTime(lastTime, desiredTimeBetweenFrame)){            
            lastTime = Time.getCurrentTime();
            if(index == 0){
                index = 1;
            }else{
                index = 0;
            }
        }
        //sets the players image to the correct image based on the index set above and whether they are moving left or right
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
        //if the player is on a rubble tile, slow the player down
        if(game.getWorld().getTileAtPosition((int) ((x + bounds.x)/GameVariables.getSTANDARD_TILE_DIAMETER()), 
                (int)((y + bounds.y)/GameVariables.getSTANDARD_TILE_DIAMETER())).getId() == 6){
            dx *= 0.3;
            dy *= 0.3;
        }
        //calls the move method and store the players displacements
        super.move(dx, dy);
        this.dx = dx;
        this.dy = dy;
    }    

    //getters and setters below
    
    public float getXDisplacement() {
        return dx;
    }

    public float getYDisplacement() {
        return dy;
    }
}
