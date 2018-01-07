package tiles.types;

import java.awt.image.BufferedImage;

import tiles.Tile;

/**
 * a tile that can be walked on
 * @author Graham
 *
 */
public class FloorTile extends Tile{	

	/**
	 * create a floor tile
	 * @param id the id of the tile from the GameVariables class
	 */
	public FloorTile(int id) {
		super(id, false);
	}
}
