package tiles.types;

import tiles.Tile;

/**
 * any tile that is solid
 * @author Graham
 *
 */
public class WallTile extends Tile{

	/**
	 * used ot create a tile that is solid
	 * @param id the id of the tile
	 */
	public WallTile(int id) {
		super(id, true);
	}

}
