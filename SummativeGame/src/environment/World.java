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
    
    private int tileWidth, tileHeight;
    private int playerX, playerY;
    private int[][] world;
    
    public World(String fileName){
        initialize(fileName);
    } 
    
    private void initialize(String fileName){
        world = WorldLoader.loadWorld(this, fileName);
        /*
        System.out.println(tileWidth);
        System.out.println(tileHeight);
        for(int y = 0; y < tileHeight; y++){
            for(int x = 0; x < tileWidth; x++){
                System.out.println(world[x][y]);
            }
        }
        */
    }
    
    public void update(){
        
    }
    
    public void render(Graphics g){        
        for(int y = 0; y < tileWidth; y++){
            for(int x = 0; x < tileHeight; x++){
                Tile.tiles[Tile.returnRenderID(world[x][y])].render(g, x * GameVariables.getSTANDARD_TILE_DIAMETER(), 
                        y * GameVariables.getSTANDARD_TILE_DIAMETER());
            }
        } 
    }
    
    public Tile getTileAtPosition(int x, int y){
        Tile t = Tile.tiles[Tile.returnRenderID(world[x][y])];
        if(t == null)                  
            t = Tile.tiles[Tile.returnRenderID(GameVariables.getDefaultTileId())];
        return t;
    }

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
