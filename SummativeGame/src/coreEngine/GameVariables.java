package coreEngine;

import java.awt.image.BufferedImage;

import loaders.ImageLoader;

/**
 * class to store and initialize all the variables that affect the gameplay
 * @author Graham
 *
 */
public class GameVariables {

	//the width and height of a player
	private static double playerWidth = 64;
	private static double playerHeight = 64;
	//the speed of a player
	private static double playerSpeed = 0.002;
	//stores the default name and score for the high scores text file, used when creating a new file
	//NOTE: default name must have NO spaces
	private static final String defaultHighScoreName = "WendyWako";
        
        private static final int FlashlightDiameter = 1280;
	
	private static final int MAX_GAME_TILES = 64;
	//VARIABLES BELOW ARE IDENTIFIERS AND FILE LOCATIONS FOR GANE TILES
	//NOTE: All tile id's must start with 9, and start at 9000
	private static final int DEFAULT_TILE_ID = 9000;
	private static final String DEFAULT_TILE_LOCATION = "defaultTile";
	public static BufferedImage DEFAULT_TILE;
	
	private static final int GRASS_TILE_ID = 9001;
	private static final String GRASS_TILE_LOCATION = "floors/grass";
	public static BufferedImage GRASS_TILE;
	
	private static final int DIRT_TILE_ID = 9002;
	private static final String DIRT_TILE_LOCATION = "floors/dirt";
	public static BufferedImage DIRT_TILE;
	
	private static final int STONE_TILE_ID = 9003;
	private static final String STONE_TILE_LOCATION = "walls/stone";
	public static BufferedImage STONE_TILE;
	
	private static final int MOSSYSTONE_TILE_ID = 9004;
	private static final String MOSSYSTONE_TILE_LOCATION = "walls/mossystone";
	public static BufferedImage MOSSYSTONE_TILE;
	
	private static final int BRICK_TILE_ID = 9005;
	private static final String BRICK_TILE_LOCATION = "walls/brick";
	public static BufferedImage BRICK_TILE;
	//IDENTIFIERS AND FILE LOCATIONS FOR GANE TILES END HERE
	
	//used to connect all tile ids to their buffered images
	public static void initializeTiles(){
		DEFAULT_TILE = ImageLoader.loadImage(DEFAULT_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		GRASS_TILE = ImageLoader.loadImage(GRASS_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		DIRT_TILE = ImageLoader.loadImage(DIRT_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		STONE_TILE = ImageLoader.loadImage(STONE_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		MOSSYSTONE_TILE = ImageLoader.loadImage(MOSSYSTONE_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		BRICK_TILE = ImageLoader.loadImage(BRICK_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
	}

	public static BufferedImage getDEFAULT_TILE() {
		return DEFAULT_TILE;
	}

	public static int getDefaultTileId() {
		return DEFAULT_TILE_ID;
	}

	public static int getMaxGameTiles() {
		return MAX_GAME_TILES;
	}

	public static String getDefaultHighScoreName() {
		return defaultHighScoreName;
	}

	public static String getDefaulthighscorename() {
		return defaultHighScoreName;
	}

	public static int getGrassTileId() {
		return GRASS_TILE_ID;
	}

	public static int getDirtTileId() {
		return DIRT_TILE_ID;
	}

	public static int getStoneTileId() {
		return STONE_TILE_ID;
	}

	public static int getMossystoneTileId() {
		return MOSSYSTONE_TILE_ID;
	}

	public static int getBrickTileId() {
		return BRICK_TILE_ID;
	}

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

    public static int getFlashlightDiameter() {
        return FlashlightDiameter;
    }   
}
