/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.mobs.enemies;

import coreEngine.Game;
import coreEngine.GameVariables;
import entities.mobs.Mob;
import entities.mobs.Player;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Graham, Michael
 */
public class Enemy extends Mob {

    //stores the game the enemy is in
    private Game game;
    //stores the players image
    private BufferedImage image;
    //stores whether this enemy runs from you or hunts you
    private boolean running;
    //stores whether or not this enemy has been caught
    private boolean caught;
    //stores the enemies damage each hit
    private int damagePerHit;

    /**
     * creates an enemy
     * @param game the game the enemy is in
     * @param image the image of the enemy
     * @param damagePerHit the amount of damage the enemy does each hit
     * @param x the starting x position of the enemy
     * @param y the starting y position of the enemy
     */
    public Enemy(Game game, BufferedImage image, int damagePerHit, float x, float y) {
        super(game, x, y, STANDARD_DIAMETER, STANDARD_DIAMETER);
        //stores the game and the enemies image
        this.game = game;
        this.image = image;
        //sets the collision bounds of the enemy
        bounds.setBounds(16, 16, 32, 32);
        //sets runnging and caught to their default values
        running  = true;
        caught = false;
        //stores the damage per hit
        this.damagePerHit = damagePerHit;
    }
    
    @Override
    public void render(Graphics g) {
        //renders the enemy at its x and y positons, adding the cameras displacement
        g.drawImage(image, (int) (x + game.getCamera().getxOff()), (int) (y + game.getCamera().getyOff()), width, height, null);
    }

    @Override
    public void tick() {
        //stores the players x and y position
        float px = game.getPlayer().getX();
        float py = game.getPlayer().getY();
        //sets the enemies initial x and y displacement to 0
        double dx = 0;
        double dy = 0;
        //if the player if left of the enemy, the enemy moves left
        if (px <= x) {
            dx = -(GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
            //if the player is right of the enemy, the enemy move right
        } else if (px >= x) {
            dx = (GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        }
        //if the player is above the enemy, the enemy move up
        if (py <= y) {
            dy = -(GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
            //if the player is below the enemy the enemy moves down
        } else if (py >= y) {
            dy = (GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        }
        //if the enemy is running, changes the movement to the opposite direction, so it runs from the player
        if (running) {
            dx = -dx;
            dy = -dy;
        }        
        //if the enemy is not caught checks if it is caught
        if(!caught){
            setCaught();
        }        
        //moves the enemy
        move((float) dx, (float) dy);
    }
    
    //determines if the enemy is intersecting with the player
    private void setCaught(){
        //gets the player and the players bounds
        Player player = game.getPlayer();
        Rectangle pBounds = player.getBounds();
        //if the enemys bounds intersect the players bounds sets caught to true
        if(player.getX() + pBounds.getWidth() >= x + bounds.x && player.getX() + pBounds.getX() <= x + bounds.width){
            if(player.getY() >= y - bounds.height && player.getY() - pBounds.height <= y){
                caught = true;
            }
        }
    }
    
    //getters and setters below
    
    public boolean isRunning(){
        return running;
    }
    
    public void setRunning(boolean running){
        this.running = running;
    }
    
    public boolean isCaught(){
        return caught;
    }
    
    //used to damage the player by the amount of damage this enemy does per hit
    public void damagePlayer(){
        game.getPlayer().dealDamae(damagePerHit);
    }
}
