package tiles.identifier;

import java.awt.image.BufferedImage;

import coreEngine.GameVariables;
import loaders.ImageLoader;

public class TileIdentifier {

	//returns the correct bufferd image based on the id passed in
	public static BufferedImage getTileImageFromID(int textureID){
		if(textureID == GameVariables.getDirtTileId()){
			return GameVariables.DIRT_TILE;
		}else if(textureID == GameVariables.getGrassTileId()){
			return GameVariables.GRASS_TILE;
		}else if(textureID == GameVariables.getStoneTileId()){
			return GameVariables.STONE_TILE;
		}else if(textureID == GameVariables.getMossystoneTileId()){
			return GameVariables.MOSSYSTONE_TILE;
		}else if(textureID == GameVariables.getBrickTileId()){
			return GameVariables.BRICK_TILE;
		}
		//if the id does not exist, returns the default texture
		return GameVariables.DEFAULT_TILE;
	}
}