/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.mobs;

import coreEngine.Game;
import coreEngine.GameVariables;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author pistm9061
 */
public class Flashlight extends Mob{
    
    BufferedImage flashlight;
    private Game game;
    
    public Flashlight(Game game, BufferedImage flashlight, float x, float y){
        super(game, x, y, GameVariables.getFlashlightDiameter(), GameVariables.getFlashlightDiameter());
        this.flashlight = flashlight;
        this.game = game;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(flashlight, (int) game.getPlayer().getX() - 608, (int) game.getPlayer().getY() - 608, width, height, null);
    }  
}
