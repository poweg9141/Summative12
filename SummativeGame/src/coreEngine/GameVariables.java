package coreEngine;

import java.awt.image.BufferedImage;
import java.util.Timer;

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
	private static double playerSpeed = 0.02;
        //stores the speed of the enemies
        private static double entitySpeed = 0.000000000001;
	//stores the default name and score for the high scores text file, used when creating a new file
	//NOTE: default name must have NO spaces
	private static final String defaultHighScoreName = "WendyWako";
        
        private static final int FlashlightDiameter = 1280;
        
        private static String levelFile = "LevelFive";
        
        private static boolean isNight = true;
        
        private static int runnersToRender = 6;
        private static int huntersToRender = 2;
        
        private static int hunterDamage = 1;
	
	private static final int MAX_GAME_TILES = 64;
        private static final int STANDARD_TILE_DIAMETER = 64;
	//VARIABLES BELOW ARE IDENTIFIERS AND FILE LOCATIONS FOR GANE TILES
	//NOTE: All tile id's must start with 9, and start at 9000
	private static final int DEFAULT_TILE_ID = 9000;
	private static final String DEFAULT_TILE_LOCATION = "defaultTile";
	private static BufferedImage DEFAULT_TILE;
	
	private static final int GRASS_TILE_ID = 9001;
	private static final String GRASS_TILE_LOCATION = "floors/grass";
	private static BufferedImage GRASS_TILE;
	
	private static final int DIRT_TILE_ID = 9002;
	private static final String DIRT_TILE_LOCATION = "floors/dirt";
	private static BufferedImage DIRT_TILE;
	
	private static final int STONE_TILE_ID = 9003;
	private static final String STONE_TILE_LOCATION = "walls/stone";
	private static BufferedImage STONE_TILE;
	
	private static final int MOSSYSTONE_TILE_ID = 9004;
	private static final String MOSSYSTONE_TILE_LOCATION = "walls/mossystone";
	private static BufferedImage MOSSYSTONE_TILE;
	
	private static final int BRICK_TILE_ID = 9005;
	private static final String BRICK_TILE_LOCATION = "walls/brick";
	private static BufferedImage BRICK_TILE;
        
        private static final int RUBBLE_TILE_ID = 9006;
	private static final String RUBBLE_TILE_LOCATION = "floors/rubble";
	private static BufferedImage RUBBLE_TILE;        
        private static final double RUBBLE_SPEED_MULTIPLIER = 0.75;
	//IDENTIFIERS AND FILE LOCATIONS FOR GANE TILES END HERE
	
	//used to connect all tile ids to their buffered images
	public static void initializeTiles(){
		DEFAULT_TILE = ImageLoader.loadImage(DEFAULT_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		GRASS_TILE = ImageLoader.loadImage(GRASS_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		DIRT_TILE = ImageLoader.loadImage(DIRT_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		STONE_TILE = ImageLoader.loadImage(STONE_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		MOSSYSTONE_TILE = ImageLoader.loadImage(MOSSYSTONE_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
		BRICK_TILE = ImageLoader.loadImage(BRICK_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
                RUBBLE_TILE = ImageLoader.loadImage(RUBBLE_TILE_LOCATION, ImageLoader.IMAGE_PNG_FORMAT_ID);
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
        
        public static double getEntitySpeed(){
            return entitySpeed;
        }

    public static BufferedImage getGRASS_TILE() {
        return GRASS_TILE;
    }

    public static BufferedImage getDIRT_TILE() {
        return DIRT_TILE;
    }

    public static BufferedImage getSTONE_TILE() {
        return STONE_TILE;
    }

    public static BufferedImage getMOSSYSTONE_TILE() {
        return MOSSYSTONE_TILE;
    }

    public static BufferedImage getBRICK_TILE() {
        return BRICK_TILE;
    }

    public static int getRUBBLE_TILE_ID() {
        return RUBBLE_TILE_ID;
    }

    public static BufferedImage getRUBBLE_TILE() {
        return RUBBLE_TILE;
    }    

    public static int getSTANDARD_TILE_DIAMETER() {
        return STANDARD_TILE_DIAMETER;
    }

    public static String getLevelFile() {
        return levelFile;
    }

    public static void setLevelFile(String levelFile) {
        GameVariables.levelFile = levelFile;
    }

    public static boolean isIsNight() {
        return isNight;
    }

    public static void setIsNight(boolean isNight) {
        GameVariables.isNight = isNight;
    }

    public static int getRunnersToRender() {
        return runnersToRender;
    }

    public static void setRunnersToRender(int runnersToRender) {
        GameVariables.runnersToRender = runnersToRender;
    }

    public static int getHuntersToRender() {
        return huntersToRender;
    }

    public static void setHuntersToRender(int huntersToRender) {
        GameVariables.huntersToRender = huntersToRender;
    }

    public static int getHunterDamage() {
        return hunterDamage;
    }

    public static void setHunterDamage(int hunterDamage) {
        GameVariables.hunterDamage = hunterDamage;
    }
}
