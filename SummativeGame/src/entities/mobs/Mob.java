package entities.mobs;

import entities.Entity;
/**
 * is anything in that game that has the ability to move or change form
 * @author Graham
 *
 */
public abstract class Mob extends Entity{

	//the health of a standard mob
	public static final int STANDARD_HEALTH = 50;
	//the speed of a standard mob
	public static final float STANDARD_SPEED = 0.002f;
	//the diameter (width and height) of a standard mob
	public static final int STANDARD_DIAMETER = 64;
	 
	//variables to store their health and speed
	protected int health;
	protected float speed;
	
	/**
	 * creates a mob
	 * @param x the x position on the screen
	 * @param y the y position on the screen
	 * @param width the width of the mob
	 * @param height the height of the mob
	 */
	public Mob(float x, float y, int width, int height) {
		super(x, y, width, height);
		health = STANDARD_HEALTH;
		speed = STANDARD_SPEED;
	}
	
	//moves the entity on the screen the value of the passed in x and y displacement
	protected void move(float dx, float dy){
		super.x += dx;
		super.y += dy;
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
}
