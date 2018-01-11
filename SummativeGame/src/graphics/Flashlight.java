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
    
    BufferedImage flashlight;
    private Game game;
    
    public Flashlight(Game game, BufferedImage img, float x, float y, float width, float height){
        super(x, y, GameVariables.getFlashlightDiameter(), GameVariables.getFlashlightDiameter());
        this.game = game;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(flashlight, (int) x, (int) y, width, height, null);
    }
}
