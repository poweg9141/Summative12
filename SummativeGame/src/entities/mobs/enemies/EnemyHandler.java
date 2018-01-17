/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.mobs.enemies;

import coreEngine.Game;
import coreEngine.GameVariables;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author poweg9141
 */
public class EnemyHandler {
    
    private Game game;
    
    private List<Enemy> enemies;
    private int caughtEnemies;
    
    public EnemyHandler(Game game){
        this.game = game;
        enemies = new ArrayList<Enemy>();        
        caughtEnemies = 0;
    }
    
    public void tick(){
        if(enemies.isEmpty())
            return;
        Iterator<Enemy> it = enemies.iterator();
        while(it.hasNext()){
            Enemy e = it.next();
            e.tick();
            if(e.isCaught()){
                it.remove();
                caughtEnemies++;
            }
        }
    }
    
    public void render(Graphics g){
        for(Enemy e : enemies){
            e.render(g);
        }
    }
    
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    
    public void removeEnemy(Enemy e){
            enemies.remove(e);       
    }        
    
    public int getCaughtEnemies(){
        return caughtEnemies;
    }
    
    public void createRunningEnemies(BufferedImage texture, int num){
        int toRender = num;
        for(int y = game.getWorld().getTileHeight() - 1; y > 0; y--){
            for(int x = game.getWorld().getTileWidth() - 1; x > 0; x--){
                if(toRender == 0){
                    return;
                }
                if(!game.getWorld().getTileAtPosition(x, y).isSolid()){
                    Enemy e = new Enemy(game, texture, 
                            x * GameVariables.getSTANDARD_TILE_DIAMETER(),
                            y * GameVariables.getSTANDARD_TILE_DIAMETER());
                    enemies.add(e);
                    toRender--;
                }
            }
        }
    }
    
    public void createHuntingEnemies(BufferedImage texture, int num){
        int toRender = num;
        for(int y = game.getWorld().getTileHeight() - 1; y > 0; y--){
            for(int x = game.getWorld().getTileWidth() - 1; x > 0; x--){
                if(toRender == 0){
                    return;
                }
                if(!game.getWorld().getTileAtPosition(x, y).isSolid()){
                    Enemy e = new Enemy(game, texture, 
                            x * GameVariables.getSTANDARD_TILE_DIAMETER(),
                            y * GameVariables.getSTANDARD_TILE_DIAMETER());
                    e.setRunning(false);
                    enemies.add(e);                    
                    toRender--;
                }
            }
        }
    }
}