/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import coreEngine.Game;
import entities.Entity;
import entities.mobs.Player;
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

    public void centreEntity(Entity e){
        xOff = e.getX() - (game.getWidth() / 2) + (e.getWidth() / 2);
        yOff = e.getY() - (game.getHeight() / 2) + (e.getHeight() / 2);
    }
    
    public void framePlayer(Player e, int frameSize){
        if(e.getX() >= game.getWidth() - frameSize && e.getXDisplacement() > 0){
            xOff = e.getX() - (game.getWidth() / 2) + (e.getWidth() / 2) - (game.getWidth() / 2 - frameSize);            
        }            
        else if(e.getX() <= frameSize && e.getXDisplacement() < 0){
            xOff = e.getX() - (game.getWidth() / 2) + (e.getWidth() / 2) + (game.getWidth() / 2 - frameSize);
        }       
        if(e.getY() >= game.getHeight() - frameSize && e.getYDisplacement() > 0){
            yOff = e.getY() - (game.getHeight() / 2) + (e.getHeight() / 2) - (game.getHeight() / 2 - frameSize);
        }           
        else if(e.getY() <= frameSize && e.getYDisplacement() < 0){
           yOff = e.getY() - (game.getHeight() / 2) + (e.getHeight() / 2) + (game.getHeight() / 2 - frameSize);
        }
    }
    
    public void centreTile(Tile t){
        xOff = t.getX() - (game.getWidth() / 2) + (t.getStandardDiameter() / 2);
        yOff = t.getY() - (game.getHeight() / 2) + (t.getStandardDiameter() / 2);
    }

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