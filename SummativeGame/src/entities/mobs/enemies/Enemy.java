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
    private boolean caught;
    private int damagePerHit;

    public Enemy(Game game, BufferedImage image, int damagePerHit, float x, float y) {
        super(game, x, y, STANDARD_DIAMETER, STANDARD_DIAMETER);
        this.game = game;
        this.image = image;
        bounds.setBounds(16, 16, 32, 32);
        running  = true;
        caught = false;
        this.damagePerHit = damagePerHit;
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
        if (px <= x) {
            dx = -(GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        } else if (px >= x) {
            dx = (GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        }
        if (py <= y) {
            dy = -(GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        } else if (py >= y) {
            dy = (GameVariables.getEntitySpeed() * game.getGameTimeSeconds());
        }
        if (running) {
            dx = -dx;
            dy = -dy;
        }        
        if(!caught){
            setCaught();
        }        
        
        move((float) dx, (float) dy);
    }
    
    private void setCaught(){
        float px = game.getPlayer().getX();
        float py = game.getPlayer().getY();
        float pw = game.getPlayer().getWidth();
        float ph = game.getPlayer().getHeight();
        if(px + pw > x && px < x + width){
            if(py + pw > y && py < y + width){
                caught = true;
            }
        }
    }
    
    public boolean isRunning(){
        return running;
    }
    
    public void setRunning(boolean running){
        this.running = running;
    }
    
    public boolean isCaught(){
        return caught;
    }
    
    public void damagePlayer(){
        game.getPlayer().dealDamae(damagePerHit);
    }
}
