/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import coreEngine.Game;
import coreEngine.GameVariables;
import entities.Entity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author pistm9061
 */
public class Flashlight extends Entity{
    
    BufferedImage flashlight;
    private Game game;
    
    public Flashlight(Game game, BufferedImage flashlight, float x, float y, float width, float height){
        super(x, y, GameVariables.getFlashlightDiameter(), GameVariables.getFlashlightDiameter());
        this.flashlight = flashlight;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(flashlight, (int) x, (int) y, width, height, null);
    }
}
