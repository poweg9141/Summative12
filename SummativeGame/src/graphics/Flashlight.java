/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import coreEngine.Game;
import coreEngine.GameVariables;
import entities.Entity;
import entities.mobs.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import loaders.ImageLoader;

/**
 *
 * @author pistm9061
 */
public class Flashlight extends Entity{
    
    BufferedImage light;
    private Game game;
    
    public Flashlight(Game game, float x, float y, float width, float height, String dir, int imageType){
        super(x, y, GameVariables.getFlashlightDiameter(), GameVariables.getFlashlightDiameter());
        light = ImageLoader.loadImage(dir, imageType);
        this.game = game;
    }

    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(light, (int)x, (int)y, width, height, null);
    }
}
