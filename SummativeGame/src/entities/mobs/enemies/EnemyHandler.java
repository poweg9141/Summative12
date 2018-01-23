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

    public EnemyHandler(Game game) {
        this.game = game;
        enemies = new ArrayList<Enemy>();
        caughtEnemies = 0;
    }

    public void tick() {
        if (enemies.isEmpty()) {
            return;
        }
        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy e = it.next();
            e.tick();
            if (e.isCaught()) {
                if (e.isRunning()) {
                    it.remove();
                    caughtEnemies++;
                } else {
                    e.damagePlayer();
                }
            }
        }
    }

    public void render(Graphics g) {
        for (Enemy e : enemies) {
            e.render(g);
        }
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void removeEnemy(Enemy e) {
        enemies.remove(e);
    }

    public int getCaughtEnemies() {
        return caughtEnemies;
    }

    public void createRunningEnemies(BufferedImage texture, int num) {
        if (num == 0) {
            return;
        }
        int toRender = num;
        Random rand = new Random();
        while (toRender > 0) {
            int x = rand.nextInt(game.getWorld().getTileWidth() - 1);
            int y = rand.nextInt(game.getWorld().getTileHeight() - 1);
            if (!game.getWorld().getTileAtPosition(x, y).isSolid() && x > 2 && y > 2) {
                Enemy e = new Enemy(game, texture, 0,
                        x * GameVariables.getSTANDARD_TILE_DIAMETER(),
                        y * GameVariables.getSTANDARD_TILE_DIAMETER());
                enemies.add(e);
                toRender--;
            }
        }
    }

    public void createHuntingEnemies(BufferedImage texture, int num) {
        if (num == 0) {
            return;
        }
        int toRender = num;
        Random rand = new Random();
        while(toRender > 0){
            int x = rand.nextInt(game.getWorld().getTileWidth() - 1);
            int y = rand.nextInt(game.getWorld().getTileHeight() - 1);
            if (!game.getWorld().getTileAtPosition(x, y).isSolid() && x > 3 && y > 3) {
                    Enemy e = new Enemy(game, texture, GameVariables.getHunterDamage(),
                            x * GameVariables.getSTANDARD_TILE_DIAMETER(),
                            y * GameVariables.getSTANDARD_TILE_DIAMETER());
                    e.setRunning(false);
                    enemies.add(e);
                    toRender--;
                }
        }
    }
}
