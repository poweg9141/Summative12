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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Graham
 */
public class EnemyHandler {

    //stores the game the handler is in
    private Game game;

    //a list of all the enemies
    private List<Enemy> enemies;
    //stores the amount of enemies that have been caught
    private int caughtEnemies;

    /**
     * creates an enemy handler
     *
     * @param game the game the handler is in
     */
    public EnemyHandler(Game game) {
        this.game = game;
        enemies = new ArrayList<Enemy>();
        //sets caught enemies to 0 to start
        caughtEnemies = 0;
    }

    //updates all enemies in the game
    public void update() {
        //if the list of enemies is empty, exits the method
        if (enemies.isEmpty()) {
            return;
        }
        //iterates through the list of enemies
        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy e = it.next();
            e.tick();
            //if the enemy is caught
            if (e.isCaught()) {
                //if its a runner, removes it from the list and adds one to caught enemies
                if (e.isRunning()) {
                    it.remove();
                    caughtEnemies++;
                    //if its a hunter, it damages the player
                } else {
                    e.damagePlayer();
                }
            }
        }
    }

    //loops through the enemies and renders them all
    public void render(Graphics g) {
        for (Enemy e : enemies) {
            e.render(g);
        }
    }

    //can add an emey to the list mid game
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    //can remove and enemy from the list mid game
    public void removeEnemy(Enemy e) {
        enemies.remove(e);
    }

    //gets the number of caught enemies
    public int getCaughtEnemies() {
        return caughtEnemies;
    }

    //creates an amount of running enemies
    public void createRunningEnemies(BufferedImage texture, int num) {
        //if none must be created, exit the method
        if (num == 0) {
            return;
        }
        //sets how many must be rendered and creates a random generator
        int toRender = num;
        Random rand = new Random();
        //loops through until all enemies are rendered
        while (toRender > 0) {
            //gets a random x and y tile coordinate
            int x = rand.nextInt(game.getWorld().getTileWidth() - 1);
            int y = rand.nextInt(game.getWorld().getTileHeight() - 1);
            //if the tile position is a floor one, creates the enemy there and subtract one from the enemies to render
            if (!game.getWorld().getTileAtPosition(x, y).isSolid() && x > 2 && y > 2) {
                Enemy e = new Enemy(game, texture, 0,
                        x * GameVariables.getSTANDARD_TILE_DIAMETER(),
                        y * GameVariables.getSTANDARD_TILE_DIAMETER());
                enemies.add(e);
                toRender--;
            }
        }
    }

    //exact same as running enemies mathod except call the setRunning(false) method on the enemy when creating it
    public void createHuntingEnemies(BufferedImage texture, int num) {
        if (num == 0) {
            return;
        }
        int toRender = num;
        Random rand = new Random();
        while (toRender > 0) {
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
