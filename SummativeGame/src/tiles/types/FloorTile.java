package tiles.types;

import coreEngine.Game;
import java.awt.image.BufferedImage;

import tiles.Tile;

/**
 * a tile that can be walked on
 *
 * @author Graham
 *
 */
public class FloorTile extends Tile {

    private int speedMultiplier;
    private boolean forceMove;
    private int damageDealtPerSecond;

    /**
     * create a floor tile
     *
     * @param id the id of the tile from the GameVariables class
     */
    public FloorTile(Game game, int id) {
        super(game, id, false);
    }

    public int getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(int speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    //to be implemented later
    /*
    public boolean isForceMove() {
        return forceMove;
    }

    public void setForceMove(boolean forceMove) {
        this.forceMove = forceMove;
    }

    public int getDamageDealt() {
        return (int) Math.floor(damageDealtPerSecond * game.getGameTimeSeconds());
    }

    public void setDamageDealtPerSecond(int damageDealtPerSecond) {
        this.damageDealtPerSecond = damageDealtPerSecond;
    }
     */
    @Override
    public boolean isSolid() {
        return false;
    }
}
