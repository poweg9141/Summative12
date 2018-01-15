/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loaders;

import coreEngine.GameVariables;
import environment.World;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import screen.DisplayManager;

/**
 *
 * @author Graham
 */
public class WorldLoader {

    public static int[][] loadWorld(World world, String fileName) {
        String dir = "res/files/worlds/" + fileName + ".txt";
        StringBuilder b = new StringBuilder();
        try {
            BufferedReader r = new BufferedReader(new FileReader(dir));
            String line;
            while ((line = r.readLine()) != null) {
                b.append(line + "\n");
            }
            r.close();
        } catch (FileNotFoundException ex) {
            DisplayManager.quitGameOnError(ex, "Could not find the world file, it may be corrupted. Try selecting a different world in the options menu.");
        } catch (IOException ex) {
            DisplayManager.quitGameOnError(ex, "The world file has been corrupted. Try selecting a different world in the options menu.");
        }

        String file = b.toString();
        String[] fileNums = file.split("\\s+");
        int width = Integer.parseInt(fileNums[0]);
        int height = Integer.parseInt(fileNums[1]);
        world.setTileWidth(width);
        world.setTileHeight(height);
        world.setPlayerX(Integer.parseInt(fileNums[2]) * GameVariables.getSTANDARD_TILE_DIAMETER());
        world.setPlayerY(Integer.parseInt(fileNums[3]) * GameVariables.getSTANDARD_TILE_DIAMETER());
        int[][] tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Integer.parseInt(fileNums[(x + y * width) + 4]);
            }
        }

        return tiles;
    }
}
