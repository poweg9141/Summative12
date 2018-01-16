/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.mobs.enemies;

import coreEngine.Game;
import coreEngine.GameVariables;
import entities.Entity;
import entities.mobs.Mob;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author poweg9141
 */
public class Enemy extends Mob {

    private Game game;
    private BufferedImage image;
    private boolean running;

    public Enemy(Game game, BufferedImage image, float x, float y) {
        super(game, x, y, STANDARD_DIAMETER, STANDARD_DIAMETER);
        this.game = game;
        this.image = image;
        running  = true;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x + game.getCamera().getxOff()), (int) (y + game.getCamera().getyOff()), width, height, null);
    }

    @Override
    public void tick() {
        float px = game.getPlayer().getX();
        float py = game.getPlayer().getY();
        double dx = 0;
        double dy = 0;
        if (px < x) {
            dx = -(GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        } else if (px > x) {
            dx = (GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        }
        if (py < y) {
            dy = -(GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        } else if (py > y) {
            dy = (GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        }
        if (running) {
            dx = -dx;
            dy = -dy;
        }
        move((float) dx, (float) dy);
    }
    
    public void setRunning(boolean running){
        this.running = running;
    }
}