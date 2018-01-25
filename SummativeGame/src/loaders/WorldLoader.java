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
import screen.DisplayManager;

/**
 *loads a world with a passed in file
 * @author Graham
 */
public class WorldLoader {

    //returns a 2d array of tiles in the world
    public static int[][] loadWorld(World world, String fileName) {
        //creates a string builder to store strings from a .txt file passed in
        String dir = "res/files/worlds/" + fileName + ".txt";
        StringBuilder b = new StringBuilder();
        //trys to read the file
        try {
            //creates a buffered reader to read in from a text file
            BufferedReader r = new BufferedReader(new FileReader(dir));
            String line;
            //whiel their is a next line to read, reads it and adds it to the string builder
            while ((line = r.readLine()) != null) {
                b.append(line + "\n");
            }
            //closes the buffered reader
            r.close();
            //catches the two possible exceptions and quits the game on the correct error if they occur
        } catch (FileNotFoundException ex) {
            DisplayManager.quitGameOnError(ex, "Could not find the world file, it may be corrupted. Try selecting a different world in the options menu.");
        } catch (IOException ex) {
            DisplayManager.quitGameOnError(ex, "The world file has been corrupted. Try selecting a different world in the options menu.");
        }

        //converts the string builder into a string
        String file = b.toString();
        //spilts the sting into a array by white space
        String[] fileNums = file.split("\\s+");
        //the first two numbers are the width and height og the world in tiles
        int width = Integer.parseInt(fileNums[0]);
        int height = Integer.parseInt(fileNums[1]);
        world.setTileWidth(width);
        world.setTileHeight(height);
        //the next two are the players x and y spawn in tiles, is read in and imediatley set in the world class below
        world.setPlayerX(Integer.parseInt(fileNums[2]) * GameVariables.getSTANDARD_TILE_DIAMETER());
        world.setPlayerY(Integer.parseInt(fileNums[3]) * GameVariables.getSTANDARD_TILE_DIAMETER());
        //creates the array of tiles the size of the worlds width and height
        int[][] tiles = new int[width][height];
        //loops through and saves the tile ids from the text file to their corresponding spot in the array
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Integer.parseInt(fileNums[(x + y * width) + 4]);
            }
        }
        //returns the tiles array
        return tiles;
    }
}
