package entities.mobs;

import coreEngine.Game;
import coreEngine.GameVariables;
import entities.Entity;
import screen.DisplayManager;

/**
 * is anything in that game that has the ability to move or change form
 *
 * @author Graham
 *
 */
public abstract class Mob extends Entity {

    //the health of a standard mob
    public static final int STANDARD_HEALTH = 50;
    //the speed of a standard mob
    public static final float STANDARD_SPEED = 0.002f;
    //the diameter (width and height) of a standard mob
    public static final int STANDARD_DIAMETER = 64;

    //variables to store their health and speed
    protected int health;
    protected float speed;
    protected boolean damagedBefore;

    /**
     * creates a mob
     *
     * @param x the x position on the screen
     * @param y the y position on the screen
     * @param width the width of the mob
     * @param height the height of the mob
     */
    public Mob(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        health = STANDARD_HEALTH;
        speed = STANDARD_SPEED;
    }

    //moves the entity on the screen the value of the passed in x and y displacement
    protected void move(float dx, float dy) {
        if (dx > 0) {            
            int tx = (int) (x + dx + bounds.x + bounds.width) / GameVariables.getSTANDARD_TILE_DIAMETER();
            if(!colliding(tx, (int) (y + bounds.y) / GameVariables.getSTANDARD_TILE_DIAMETER()) &&
                    !colliding(tx, (int) (y + bounds.y + bounds.height) / GameVariables.getSTANDARD_TILE_DIAMETER())){
                x += dx;
            }            
        } else if (dx < 0) {            
            int tx = (int) (x + dx + bounds.x) / GameVariables.getSTANDARD_TILE_DIAMETER();
            if(!colliding(tx, (int) (y + bounds.y) / GameVariables.getSTANDARD_TILE_DIAMETER()) &&
                    !colliding(tx, (int) (y + bounds.y + bounds.height) / GameVariables.getSTANDARD_TILE_DIAMETER())){
                x += dx;
            }            
        }
        
        if(dy > 0){            
            int ty = (int) (y + dy + bounds.y + bounds.height) / GameVariables.getSTANDARD_TILE_DIAMETER();
            if(!colliding((int) (x + bounds.x) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty) &&
                   !colliding((int) (x + bounds.x + bounds.width) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty)){
                y += dy;
            }            
        }else if(dy < 0){            
            int ty = (int) (y + dy + bounds.y) / GameVariables.getSTANDARD_TILE_DIAMETER();
            if(!colliding((int) (x + bounds.x) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty) &&
                   !colliding((int) (x + bounds.x + bounds.width) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty)){
                y += dy;
            }
        }
    }

    protected boolean colliding(int x, int y) {
        return game.getWorld().getTileAtPosition(x, y).isSolid();
    }
    
    public void dealDamae(int damage){
        if(!damagedBefore){
            health -= damage;
            damagedBefore = true;
        }
        if(isDead())
            handleDeath();
    }
    
    protected void handleDeath(){
        game.stop(false);        
    } 
    
    public boolean isDead(){
        if(health > 0)
            return false;
        return true;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public void setDamagedBefore(boolean damaged){
        damagedBefore = damaged;
    }
}