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
    //stores whether or not the entity has been damaged before
    protected boolean damagedBefore;

    /**
     * creates a mob
     *
     * @param game the game the mob is being created in
     * @param x the x position on the screen
     * @param y the y position on the screen
     * @param width the width of the mob
     * @param height the height of the mob
     */
    public Mob(Game game, float x, float y, int width, int height) {
        //calls the super passing in the needed variables
        super(game, x, y, width, height);
        //sets the health and speed to the standard health and speed
        health = STANDARD_HEALTH;
        speed = STANDARD_SPEED;
    }

    //moves the entity on the screen the value of the passed in x and y displacement and checks for collisions
    protected void move(float dx, float dy) {
        //if the player is moving left, handles collisions for the x
        if (dx > 0) {
            //calculates the tile the players right side would end up on if they could move
            int tx = (int) (x + dx + bounds.x + bounds.width) / GameVariables.getSTANDARD_TILE_DIAMETER();
            //checks if the top right and bottom right of the players bounds box would move onto a solid tile
            if (!colliding(tx, (int) (y + bounds.y) / GameVariables.getSTANDARD_TILE_DIAMETER())
                    && !colliding(tx, (int) (y + bounds.y + bounds.height) / GameVariables.getSTANDARD_TILE_DIAMETER())) {
                //if the player wont move onto a solid tile, add the correct displacement to the players x
                x += dx;
            }
            //if the player if moving right, handles collisions for the x
        } else if (dx < 0) {
            //calculates the tile the players left side would end up on if they could move
            int tx = (int) (x + dx + bounds.x) / GameVariables.getSTANDARD_TILE_DIAMETER();
            //checks if the top left and bottom left of the players bounds box would move onto a solid tile
            if (!colliding(tx, (int) (y + bounds.y) / GameVariables.getSTANDARD_TILE_DIAMETER())
                    && !colliding(tx, (int) (y + bounds.y + bounds.height) / GameVariables.getSTANDARD_TILE_DIAMETER())) {
                //if the player wont move onto a solid tile, add the correct displacement to the players x
                x += dx;
            }
        }
        //if the player is moving down, handles collisions for the y
        if (dy > 0) {
            //calculates the tile the players bottom side would end up on if they could move
            int ty = (int) (y + dy + bounds.y + bounds.height) / GameVariables.getSTANDARD_TILE_DIAMETER();
            //checks if the bottom left and bottom right of the players bounds box would move onto a solid tile
            if (!colliding((int) (x + bounds.x) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty)
                    && !colliding((int) (x + bounds.x + bounds.width) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty)) {
                //if the player wont move onto a solid tile, add the correct displacement to the players y
                y += dy;
            }
            //if the player is moving up, handles collisions for the y
        } else if (dy < 0) {
            //calculates the tile the players top side would end up on if they could move
            int ty = (int) (y + dy + bounds.y) / GameVariables.getSTANDARD_TILE_DIAMETER();
            //checks if the top left and top right of the players bounds box would move onto a solid tile
            if (!colliding((int) (x + bounds.x) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty)
                    && !colliding((int) (x + bounds.x + bounds.width) / GameVariables.getSTANDARD_TILE_DIAMETER(), ty)) {
                //if the player wont move onto a solid tile, add the correct displacement to the players y
                y += dy;
            }
        }
    }

    //checks if the x and y positions passed are on a solid tile
    protected boolean colliding(int x, int y) {
        //returns whether or not the tile in the world at the passed in x and y values is solid
        return game.getWorld().getTileAtPosition(x, y).isSolid();
    }

    //deals damage to the entity
    public void dealDamae(int damage) {
        //if the entity has not been damaged before this frame
        if (!damagedBefore) {
            //subtracts the damage to be dealt from their health
            health -= damage;
            //sets damagedBefore to true so the entity is not acidentlty damaged twice
            damagedBefore = true;
        }
        //if the enemy is dead calls the method to handle their death
        if (isDead()) {
            handleDeath();
        }
    }

    //calls the games stop method, passing in that the plkayer lost
    protected void handleDeath() {
        game.stop(false);
    }

    //used to check if the player is dead
    public boolean isDead() {
        //if their health is above 0, return false
        if (health > 0) {
            return false;
        }
        //if its 0 or below, return true
        return true;
    }
    
    //getters and setters below

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

    public void setDamagedBefore(boolean damaged) {
        damagedBefore = damaged;
    }
}
