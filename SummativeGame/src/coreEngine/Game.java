package coreEngine;

import entities.mobs.enemies.Enemy;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import entities.mobs.Player;
import entities.mobs.Flashlight;
import entities.mobs.enemies.EnemyHandler;
import environment.World;
import graphics.Camera;
import graphics.SpriteSheet;
import input.KeyInput;
import java.util.logging.Level;
import java.util.logging.Logger;
import loaders.ImageLoader;
import loaders.ScoreLoader;
import screen.DisplayManager;
import screen.Window;
import tiles.Tile;
import tiles.types.FloorTile;
import tiles.types.WallTile;

/**
 * class to run the games thread and control game logic
 *
 * @author Graham
 *
 */
public class Game implements Runnable {

    //the maximum amount of frames per second the game can run at
    private final int MAX_FPS = 120;
    //stores the amount of seconds that have passed since the last frame
    private double gameTimeSeconds;
    //stores the frames per second the game is running at
    private int fps;

    //private window to display the game
    private Window display;
    //the JFrame the game will run out of
    private JFrame frame;
    //the title of the gmae (to be displayed on the JFrame)
    private String title;
    //the height and width of the game
    private int width, height;

    //thread to run the game
    private Thread thread;
    //stores whether the game is running or not
    private boolean running;

    //creates a bufferStrategy, canvas, and graphics object to render to the screen
    private BufferStrategy bs;
    private Graphics g;
    private Canvas c;

    //stores the display manager so that different windows can be opened at any time
    private DisplayManager manager;
    //the key listener of the game
    private KeyInput input;
    //used to manage the high scores for the game
    private ScoreLoader scores;

    //stores the flashlight
    Flashlight flashlight;
    
    //start time
    long startTime;
    //end time
    long endTime;
    
    private Player player;
    private Camera camera;
    private EnemyHandler enemies;
    private World world;
    
    private long gameStartTime, gameEndTime;

    /**
     * Constructor to create the game
     *
     * @param title the title to be displayed to the games JFrame
     * @param width the width of the games JFrame
     * @param height the height of the games JFrame
     * @param startTime starts the time
     */
    public Game(DisplayManager manager, String title, int width, int height, long startTime) {
        //stores the passed in variables
        this.width = width;
        this.height = height;
        this.title = title;
        this.manager = manager;
        this.startTime = startTime;
        //initializes the score loader
        scores = new ScoreLoader();
        camera = new Camera(this, 0, 0);
        world = new World(GameVariables.getLevelFile());
        enemies = new EnemyHandler(this);
    }

    //method to update the game every frame before rendering
    private void update() {
        input.update();
        world.update();
        player.tick();
        enemies.tick();

        if(enemies.getCaughtEnemies() >= GameVariables.getRunnersToRender()){
            stop(true);
        }
        // tile.setImage(Tile.tiles[Tile.returnRenderID(GameVariables.getRUBBLE_TILE_ID())].getImage());
    }

    //method the render the game to the screen once updated
    private void render() {
        //sets the buffer strategy to the displays buffer strategy
        bs = c.getBufferStrategy();
        //creates a buffer strategy if the display doesnt already have one
        if (bs == null) {
            //creates a buffer strategy with three buffers
            c.createBufferStrategy(3);
            //leaves the method and doesnt render anything this frame
            return;
        }
        //allows the graphics object to draw to the buffer strategy
        g = bs.getDrawGraphics();

        //clears the screen for new rendering
        g.clearRect(0, 0, width, height);

        //DRAWING BEGINS HERE	
        world.render(g);
        enemies.render(g);

        player.render(g);
        // places the flashlight filter overtop everything
        if (GameVariables.isIsNight()) {
            flashlight.render(g);
        }
        //DRAWING ENDS HERE

        //display the buffers to the screen
        bs.show();
        //gets rid of the old graphics object so it doesn't interfere with later drawing
        g.dispose();
    }

    //method to run the game
    @Override
    public void run() {
        //calls the initialize method
        initialize();

        double frameTime = 1000000000 / MAX_FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long oldTime = System.nanoTime();
        
        gameStartTime = System.nanoTime();
        
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / frameTime;
            lastTime = now;
            if(delta >= 1){
                gameTimeSeconds = (now - oldTime) / 1000000000;
                fps = MAX_FPS;
                update();
                render();
                delta--;
            }
        }        
        //calls the stop method to close the thread now that the game has been closed
        stop(false);
    }
    
    //OLD TIMER
    /*
     //variables to store the time of the last frame, the time at the current frame, 
        //and the max time it can take to render the frame
        //used in the FPS cap and FPS counter system
        long lastTime = System.nanoTime();
        long currentTime = System.nanoTime();
        double frameTime = 1000000000.0 / MAX_FPS;
    
    
    //while the time it has taken to update is less than the time it can take
            while (currentTime - lastTime < frameTime) {
                //updates the game variables and the current time
                update();
                currentTime = System.nanoTime();
                //calculates the time the frame took in nano seconds
                long nanoTime = currentTime - lastTime;
                //calculates the time the frame took in seconds
                gameTimeSeconds = nanoTime / 1000000000.0;
            }
            //when the max time has been taken, it renders to the screen
            render();
            player.setDamagedBefore(false);
            //calculates the FPS the game is currently running at
            fps = (int) (Math.ceil(1.0 / gameTimeSeconds));
            // set player speed
            
            //resets the lastTime variable to the current time
            lastTime = currentTime;
    */

    //initializes all the game code before the game loop
    private void initialize() {
        //creates a new display running on the created thread
        display = new Window(title, width, height);
        frame = display.getFrame();

        //initialize the canvas object		
        c = new Canvas();
        //sets the size of the canvas in a way that ensures it can never change
        c.setPreferredSize(new Dimension(width, height));
        c.setMaximumSize(new Dimension(width, height));
        c.setMinimumSize(new Dimension(width, height));
        c.setFocusable(false);

        //adds the canvas to the JFrame
        frame.add(c);
        //packs the JFrame so everything is displayed properly
        frame.pack();

        //creates the player
        BufferedImage playerIcon = ImageLoader.loadImage("player_sheet", ImageLoader.IMAGE_PNG_FORMAT_ID);
        SpriteSheet playerSheet = new SpriteSheet(playerIcon, 2, 64);
        player = new Player(this, playerSheet, world.getPlayerX(), world.getPlayerY());

        // creates the flashlight
        BufferedImage flashlightIcon = ImageLoader.loadImage("flashlight", ImageLoader.IMAGE_PNG_FORMAT_ID);
        flashlight = new Flashlight(this, flashlightIcon, player.getX() - 608, player.getY() - 608);
     
        //creates and adds the key listener
        input = new KeyInput(this, flashlight, player);
        frame.addKeyListener(input);

        //adds a listener to the frame to execute code if the x button on the frame is pressed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //calls the method to run all code that needs to execute before the game closes
                closeGame();
            }
        });

        //creates all the buffered images for the tile ids
        GameVariables.initializeTiles();
        //sets all tiles in the tile list to the default tile to begin
        Tile.initializeTiles();
        //calls the method where all tiles that are to be created will be
        createTiles();

        BufferedImage runnerIcon = ImageLoader.loadImage("npcs/runner", ImageLoader.IMAGE_PNG_FORMAT_ID);
        BufferedImage hunterIcon = ImageLoader.loadImage("npcs/hunter", ImageLoader.IMAGE_PNG_FORMAT_ID);
        enemies.createRunningEnemies(runnerIcon, GameVariables.getRunnersToRender());
        enemies.createHuntingEnemies(hunterIcon, GameVariables.getHuntersToRender());
    }

    //method runs before the game closes
    private void closeGame() {
        gameEndTime = System.nanoTime();
        //saves the scores to the text file     
        if(GameVariables.getPlayerName() != null){
            scores.addScore(GameVariables.getPlayerName(), getGameRunTime());
        }else{
            scores.addScore(GameVariables.getDefaultHighScoreName(), getGameRunTime());
        }       
        scores.saveScores();
    }

    //used to create all tiles in the game
    private void createTiles() {
        //creates a default tile
        new FloorTile(this, GameVariables.getDefaultTileId());
        //creates a new wall tile using the stone texture
        new WallTile(this, GameVariables.getStoneTileId());
        new FloorTile(this, GameVariables.getRUBBLE_TILE_ID());
        new FloorTile(this, GameVariables.getGrassTileId());
        new WallTile(this, GameVariables.getMossystoneTileId());
    }

    //starts the thread
    public synchronized void start() {
        //if the game is already running, exit the method
        if (running) {
            return;
        }
        //sets running to true
        running = true;
        //creates a new thread out of this class
        thread = new Thread(this);
        //starts the thread, calling the run method
        thread.start();
    }

    //stops the threat
    public synchronized void stop(boolean won) {
        //if the game is not running exit the method
        if (!running) {
            return;
        }
        //sets running to false
        running = false;
        //calls the method to run all code that must execute before the game closes
        closeGame();
        try {
            frame.setVisible(false);
            frame.dispose();
            manager.quitGame(won);
            //joins the thread back into the main program, effectively terminating it
            thread.join();            
        } catch (InterruptedException e) {
            //if the thread cannot be joined prints out an error, the stack trace, and exits the program
            DisplayManager.quitGameOnError(e, "Error closing the games thread, the thread may have already been closed.");
        }
        
    }
    
    public void gameVisibility(boolean visible){
        frame.setVisible(visible);
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getGameTimeSeconds() {
        return gameTimeSeconds;
    }

    public int getFPS() {
        return fps;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public EnemyHandler getEnemyHandler() {
        return enemies;
    }
    
    public int getGameRunTime(){
        long difference = gameEndTime - gameStartTime;
        int diffSec = (int) Math.ceil(difference / 1000000000);
        return diffSec;
    }
}
