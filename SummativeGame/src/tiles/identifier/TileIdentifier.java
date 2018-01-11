package tiles.identifier;

import java.awt.image.BufferedImage;

import coreEngine.GameVariables;
import loaders.ImageLoader;

public class TileIdentifier {

    //returns the correct bufferd image based on the id passed in
    public static BufferedImage getTileImageFromID(int textureID) {
        if (textureID == GameVariables.getDirtTileId()) {
            return GameVariables.getDIRT_TILE();
        } else if (textureID == GameVariables.getGrassTileId()) {
            return GameVariables.getGRASS_TILE();
        } else if (textureID == GameVariables.getStoneTileId()) {
            return GameVariables.getSTONE_TILE();
        } else if (textureID == GameVariables.getMossystoneTileId()) {
            return GameVariables.getMOSSYSTONE_TILE();
        } else if (textureID == GameVariables.getBrickTileId()) {
            return GameVariables.getBRICK_TILE();
        } else if (textureID == GameVariables.getRUBBLE_TILE_ID()) {
            return GameVariables.getRUBBLE_TILE();
        }
        //if the id does not exist, returns the default texture
        return GameVariables.getDEFAULT_TILE();
    }
}
