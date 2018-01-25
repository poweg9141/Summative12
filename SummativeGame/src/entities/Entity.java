package entities;

import coreEngine.Game;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * anything in that game that isnt just an image
 *
 * @author Graham
 *
 */
public abstract class Entity {

    //stores the game the entity is in
    protected Game game;
    //the entitys x and y position
    protected float x, y;
    //the entitys width and height
    protected int width, height;
    protected Rectangle bounds;

    /**
     * creates an entity     
     * @param game the game the entities will be created for
     * @param x the x position of the entity on the screen
     * @param y the y position of the entity on the screen
     * @param width the width of the entity in pixels
     * @param height the height of the entity in pixels
     */
    public Entity(Game game, float x, float y, int width, int height) {
        //stores the passed in variables
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //creates a basic bounding box the size of the image, used for collision detection
        bounds = new Rectangle(0, 0, width, height);
    }

    /**
     * used to update the entities variables every frame
     */
    public abstract void tick();

    /**
     * used to render the entity onto the screen every frame     
     * @param g the graphics object of the game the entity is being rendered in
     */
    public abstract void render(Graphics g);

    //getters and setters below
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public Rectangle getBounds(){
        return bounds;
    }
}
