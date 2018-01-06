package coreEngine;

/**
 * class to store all the variables that affect the gameplay
 * @author Graham
 *
 */
public class GameVariables {

	//the width and height of a player
	private static double playerWidth = 64;
	private static double playerHeight = 64;
	//the speed of a player
	private static double playerSpeed = 0.002;

	public static double getPlayerSpeed() {
		return playerSpeed;
	}

	public static void setPlayerSpeed(double playerSpeed) {
		GameVariables.playerSpeed = playerSpeed;
	}
	
	public static double getPlayerWidth() {
		return playerWidth;
	}

	public static void setPlayerWidth(double playerWidth) {
		GameVariables.playerWidth = playerWidth;
	}

	public static double getPlayerHeight() {
		return playerHeight;
	}

	public static void setPlayerHeight(double playerHeight) {
		GameVariables.playerHeight = playerHeight;
	}
}
