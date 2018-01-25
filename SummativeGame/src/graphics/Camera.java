/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import coreEngine.Game;
import coreEngine.GameVariables;
import entities.Entity;
import tiles.Tile;

/**
 *
 * @author Graham
 */
public class Camera {
    //the game the camera is in
    private Game game;
    //stores the total x and y offset of the camera from its original position
    private float xOff, yOff;
    
    /**
     * creates a camera, using the x and y offset the game starts with
     * @param game the game the camera will run in
     * @param xOff original x offset
     * @param yOff original y offset
     */
    public Camera(Game game, float xOff, float yOff){
        this.game = game;
        this.xOff = xOff;
        this.yOff = yOff;
    }
    
    /**
     * moves the camera offset in the direction of the passed in x and y displacements
     * @param xd the x displacement since the last frame
     * @param yd the y displacement since the last frame
     */
    public void move(float xd, float yd){
        xOff += xd;
        yOff += yd;
    }    

    //puts the player at the centre of the screen
    public void centreEntity(Entity e){
        //sets the offset to the players position plus half the screen
        xOff = e.getX() - (game.getWidth() / 2) + (e.getWidth() / 2);
        yOff = e.getY() - (game.getHeight() / 2) + (e.getHeight() / 2);
    }
    
    //puts the player in the centre of the screen, while not letting white space outside the world appear on screen
    public void framEntity(Entity e){
        //sets the offset to the players position plus half the screen
        xOff = e.getX() - (game.getWidth() / 2) + (e.getWidth() / 2);
        yOff = e.getY() - (game.getHeight() / 2) + (e.getHeight() / 2);
        
        //if the edge of the world is showing on either side, adjust the offsets so its not
        if(xOff < 0)
            xOff = 0;
        else if(xOff > game.getWorld().getTileWidth() * GameVariables.getSTANDARD_TILE_DIAMETER() - game.getWidth() &&
                game.getWorld().getTileWidth() * GameVariables.getSTANDARD_TILE_DIAMETER() > game.getWidth())
            xOff = game.getWorld().getTileWidth() * GameVariables.getSTANDARD_TILE_DIAMETER() - game.getWidth();
        if(yOff < 0)
            yOff = 0;
        else if(yOff > game.getWorld().getTileHeight() * GameVariables.getSTANDARD_TILE_DIAMETER() - game.getHeight() &&
                game.getWorld().getTileHeight() * GameVariables.getSTANDARD_TILE_DIAMETER() > game.getHeight())
            yOff = game.getWorld().getTileHeight() * GameVariables.getSTANDARD_TILE_DIAMETER() - game.getHeight();
    }
    
    //puts the tile at the centre of the screen
    public void centreTile(Tile t){
        //sets the offset to the tiles position plus half the screen
        xOff = t.getX() - (game.getWidth() / 2) + (t.getStandardDiameter() / 2);
        yOff = t.getY() - (game.getHeight() / 2) + (t.getStandardDiameter() / 2);
    }
    
    //getters and setters below

    public float getxOff() {
        return -xOff;
    }

    public void setxOff(float xOff) {
        this.xOff = xOff;
    }

    public float getyOff() {
        return -yOff;
    }

    public void setyOff(float yOff) {
        this.yOff = yOff;
    }   
}