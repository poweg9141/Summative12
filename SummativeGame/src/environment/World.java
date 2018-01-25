/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import coreEngine.GameVariables;
import java.awt.Graphics;
import loaders.WorldLoader;
import tiles.Tile;

/**
 *
 * @author Graham
 */
public class World {
    
    //stores the width and height of the world in tiles
    private int tileWidth, tileHeight;
    //stores the players starting position
    private int playerX, playerY;
    //integer array of the world to store tile ids
    private int[][] world;
    
    //creates a world
    public World(String fileName){
        initialize(fileName);
    } 
    
    //loads the world with the file name passed in
    private void initialize(String fileName){
        world = WorldLoader.loadWorld(this, fileName);
    }
    
    //update method
    public void update(){
        
    }
    
    //loops through and renders every tile in the world by there tile ids
    public void render(Graphics g){        
        for(int y = 0; y < tileHeight; y++){
            for(int x = 0; x < tileWidth; x++){
                Tile.tiles[Tile.returnRenderID(world[x][y])].render(g, x * GameVariables.getSTANDARD_TILE_DIAMETER(), 
                        y * GameVariables.getSTANDARD_TILE_DIAMETER());
            }
        } 
    }
    
    /**
     * gets a tile at an x and y position
     * @param x the x pos of the tile
     * @param y the y pos if the tile
     * @return 
     */
    public Tile getTileAtPosition(int x, int y){
        //creates a grass tile in case there is a problem finding the tile
        Tile t = Tile.tiles[Tile.returnRenderID(GameVariables.getGrassTileId())];
        //if the position is in the world, trys to get the tile at that position
        if(!(x < 0) || !(y < 0) || !(x >= tileWidth) || !(y >= tileHeight))           
            try{
                t = Tile.tiles[Tile.returnRenderID(world[x][y])];
            }catch(ArrayIndexOutOfBoundsException e){
                //if it cant find the tile or an error is thrown, returns a grass tile so the game doent crash
                t = Tile.tiles[Tile.returnRenderID(GameVariables.getGrassTileId())];
            }
        //if t is null creates a new grass tile to return
        if(t == null)                  
            t = Tile.tiles[Tile.returnRenderID(GameVariables.getDefaultTileId())];
        //returns the found tile        
        return t;
    }
    
    //getters and setters below

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int[][] getWorld() {
        return world;
    }

    public void setWorld(int[][] world) {
        this.world = world;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
}
