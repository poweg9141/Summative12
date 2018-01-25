package coreEngine;

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
 * @author Graham, Michael
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
    
    //stores the created player, camera, enemy handler, and world, for use within the class and to pass to other classes
    private Player player;
    private Camera camera;
    private EnemyHandler enemies;
    private World world;
    
    //stores the time the game started and ended at in nano seconds
    private long gameStartTime, gameEndTime;

    /**
     * Constructor to create the game     
     * @param manager the display manager the game is created from
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
        //creates a new camera at the starting position 0, 0
        camera = new Camera(this, 0, 0);
        //creates the world using the file name from the GameVariables class
        world = new World(GameVariables.getLevelFile());
        //creates a new enemy handler
        enemies = new EnemyHandler(this);
    }

    //method to update the game every frame before rendering
    private void update() {
        //updates the input, which is in charge of getting keyboard input and moving the player
        input.update();
        //updates the world, which moves all the tiles the correct displacement so it looks like the player is moving throughout the world
        world.update();
        //updates the player, which checks if its dead and decides which image to use
        player.tick();
        //updates the enemies positions
        enemies.update();

        //if the enemies the player has caught equals the enemies rendered, stop the game since the player has won
        if(enemies.getCaughtEnemies() >= GameVariables.getRunnersToRender()){
            stop(true);
        }
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
        //renders the world first so it is in the back
        world.render(g);
        //renders the enemies
        enemies.render(g);
        //renders the player last so it is on top of everything
        player.render(g);
        // places the flashlight filter overtop everything, if night was selected
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

        //stores the time allowed to render one frame to the screen
        double frameTime = 1000000000 / MAX_FPS;
        //used to store, in decimal percent value, how much of the alloted time has passed
        double delta = 0;
        //variables to store the time now, the last time
        long now;
        long lastTime = System.nanoTime();
        //variable to store the old time, used in time calculations
        long oldTime = System.nanoTime();
        //sets the start time of the game before the game starts rendering
        gameStartTime = System.nanoTime();
        
        //while loop to run as long as the game is running
        while (running) {
            //updates the time in the now variable
            now = System.nanoTime();
            //updates the delta variable
            delta += (now - lastTime) / frameTime;
            //updates the last time to now, after the lastTime has been used in calculations
            lastTime = now;
            //if 100% or more of the timme alloted to render has passed, render the next frame
            if(delta >= 1){
                //update the time in seconds, the last frame took to render
                //NOTE: it should always be 1/60th of a second but this calculation is done just in case
                gameTimeSeconds = (now - oldTime) / 1000000000;
                //sets the fps to the maximum amount of fps in the game, in case it needs to be acessed from outside the class
                fps = MAX_FPS;
                //calls the update method, to update all variables before rendering
                update();
                //calls the render method to render everything to the screen
                render();
                //subtracts one from delta, used to reset the delta variable
                //NOTE: if exactly 100% of the time is passed, delta will be 0, 
                //if not delta will be the decimal percent of extra time taken, and will count toward the render time of the next frame
                //in an effort to bring the gamer back on track if rendering falls behind
                delta--;
            }
        }        
        //calls the stop method to close the thread now that the game has been closed, defaults to a loss
        stop(false);
    }

    //initializes all the game code before the game loop
    private void initialize() {
        //creates a new display running on the created thread
        display = new Window(title, width, height);
        //stores the JFrame created by the display
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
        //loads in the sprite sheet of the player
        BufferedImage playerIcon = ImageLoader.loadImage("player_sheet", ImageLoader.IMAGE_PNG_FORMAT_ID);
        //creates the player sprite sheet
        SpriteSheet playerSheet = new SpriteSheet(playerIcon, 2, 64);
        //creates the player using the sprite sheet, and the designated x and y spawn from the world file       
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
                //does nothing at the moment
            }
        });

        //creates all the buffered images for the tile ids
        GameVariables.initializeTiles();
        //sets all tiles in the tile list to the default tile to begin
        Tile.initializeTiles();
        //calls the method where all tiles that are to be created will be
        createTiles();

        //loads in the buffered images of the runners the player must find, and the hunters that will hunt them
        BufferedImage runnerIcon = ImageLoader.loadImage("npcs/runner", ImageLoader.IMAGE_PNG_FORMAT_ID);
        BufferedImage hunterIcon = ImageLoader.loadImage("npcs/hunter", ImageLoader.IMAGE_PNG_FORMAT_ID);
        //creates the runners using the buffered image loaded in above an the specified number of them from the GameVariables class
        enemies.createRunningEnemies(runnerIcon, GameVariables.getRunnersToRender());
        //creates the hunters using the buffered image loaded in above an the specified number of them from the GameVariables class
        enemies.createHuntingEnemies(hunterIcon, GameVariables.getHuntersToRender());
    }

    //method runs before the game closes
    private void closeGame() {
        //stores the time the game ended in nano seconds
        gameEndTime = System.nanoTime();
        //trys to add the new score to the high scores list, passing in the players name and game run time
        scores.addScore(GameVariables.getPlayerName(), getGameRunTime()); 
        //saves the scores to the text file
        scores.saveScores();
    }

    //used to create all tiles in the game
    private void createTiles() {
        //creates a default tile
        new FloorTile(this, GameVariables.getDefaultTileId());
        //creates a new wall tile using the stone texture
        new WallTile(this, GameVariables.getStoneTileId());
        //creates a new floor tile using the rubble texture
        new FloorTile(this, GameVariables.getRUBBLE_TILE_ID());
        //creates a new floor tile using the grass texture
        new FloorTile(this, GameVariables.getGrassTileId());
        //creates a new wall tile using the mossy stone texture
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
    
    //getters and setters below
    
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
    
    //calculates and returns to the nearest second, how long the game ran for
    public int getGameRunTime(){
        //gets the difference between the game end and start times
        long difference = gameEndTime - gameStartTime;
        //converts that difference to a time in seconds by dividing by 1x10^9 rounding up, and casting to an int
        int diffSec = (int) Math.ceil(difference / 1000000000);
        //returnsthe game run time in seconds
        return diffSec;
    }
}
