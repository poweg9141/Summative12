package entities;

import java.awt.Graphics;

/**
 * anything in that game that isnt just an image
 * @author Graham
 *
 */
public abstract class Entity {

	//the entitys x and y position
	protected float x, y;
	//the entitys width and height
	protected int width, height;
	
	/**
	 * creates an entity
	 * @param x the x position of the entity on the screen
	 * @param y the y position of the entity on the screen
	 * @param width the width of the entity in pixels
	 * @param height the height of the entity in pixels
	 */
	public Entity(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * used to update the entitys variables every frame
	 */
	public  abstract void tick();
	
	/**
	 * used to render the entity onto the screen every frame
	 * @param g the graphics object of the game the entity is being rendered in
	 */
	public abstract void render(Graphics g);

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
}
