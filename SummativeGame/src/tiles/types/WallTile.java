package tiles.types;

import coreEngine.Game;
import entities.mobs.Player;
import tiles.Tile;

/**
 * any tile that is solid
 * @author Graham
 *
 */
public class WallTile extends Tile{

	/**
	 * use it create a tile that is solid
	 * @param id the id of the tile
	 */
	public WallTile(Game game, int id) {
		super(game, id, true);
	}            
}
