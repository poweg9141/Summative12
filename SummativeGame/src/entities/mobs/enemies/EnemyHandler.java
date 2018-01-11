/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.mobs.enemies;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author poweg9141
 */
public class EnemyHandler {
    
    private List<Enemy> enemies;
    
    public EnemyHandler(){
        enemies = new ArrayList<Enemy>();
    }
    
    public void tick(){
        if(enemies.isEmpty())
            return;
        for(Enemy e : enemies){
            e.tick();
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
    
    public void removeEnemy(float x, float y){
        for(Enemy e : enemies){
            if(e.getX() == x && e.getY() == y){
                enemies.remove(e);
            }
        }
    }
}
