package coreEngine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import entities.mobs.Player;
import input.KeyInput;
import loaders.ImageLoader;
import screen.DisplayManager;
import screen.Window;
/**
 * class to run the games thread and control game logic
 * @author Graham
 *
 */
public class Game implements Runnable{
	
	//the maximum amount of frames per second the game can run at
	private final int MAX_FPS = 60;
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
	
	//stores the player
	Player player;
	
	/**
	 * Constructor to create the game
	 * @param title the title to be displayed to the games JFrame
	 * @param width the width of the games JFrame
	 * @param height the height of the games JFrame
	 */
	public Game(DisplayManager manager, String title, int width, int height){
		//stores the passed in variables
		this.width = width;
		this.height = height;
		this.title = title;
		this.manager = manager;
	}
	
	//method to update the game every frame before rendering
	private void update(){		
		input.update();
	}
	
	//method the render the game to the screen once updated
	private void render(){
		//sets the buffer strategy to the displays buffer strategy
		bs = c.getBufferStrategy();
		//creates a buffer strategy if the display doesnt already have one
		if(bs == null){
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

		//g.fillRect(0, 0, 100, 100);
		player.render(g);
		
		//DRAWING ENDS HERE
		
		//display the buffers to the screen
		bs.show();
		//gets rid of the old graphics object so it doesnt interfere with later drawing
		g.dispose();
	}

	//method to run the game
	@Override
	public void run() {		
		//calls the initialize method
		initialize();
		
		//variables to store the time of the last frame, the time at the current frame, 
		//and the max time it can take to render the frame
		//used in the FPS cap and FPS counter system
		long lastTime = System.nanoTime();
		long currentTime = System.nanoTime();
		double frameTime = 1000000000.0 / MAX_FPS;
		//game loop to run until game is closed
		while(running){
			//while the time it has taken to update is less than the time it can take
			while(currentTime - lastTime < frameTime){
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
			//calculates the fps the game is currently runing at
			fps = (int)(Math.ceil(1.0 / gameTimeSeconds));
			//resets the lastTime variable to the curreent time
			lastTime = currentTime;
		}		
		//calls the stop method to close the thread now that the game has been closed
		stop();
	}
	
	//initializes all the game code before the game loop
	private void initialize(){
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
		
		BufferedImage playerIcon = ImageLoader.loadImage("player", ImageLoader.IMAGE_PNG_FORMAT_ID);
		player = new Player(this, playerIcon, 20, 20);
		
		input = new KeyInput(this, player);
		frame.addKeyListener(input);
	}
	
	//starts the thread
	public synchronized void start(){
		//if the game is already running, exit the method
		if(running) return;
		//sets running to true
		running = true;
		//creates a new thread out of this class
		thread = new Thread(this);
		//starts the thread, calling the run method
		thread.start();
	}
	
	//stops the threat
	public synchronized void stop(){
		//if the game is not running exit the method
		if(!running) return;
		//sets running to false
		running = false;
		try {
			//joins the thread back into the main program, effectively terminating it
			thread.join();
		} catch (InterruptedException e) {
			//if the thread cannot be joined prints out an error, the stack trace, and exits the program
			System.out.println("error closing thread");
			e.printStackTrace();
			System.exit(1);
		}
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
	
	public double getGameTimeSeconds(){
		return gameTimeSeconds;
	}
	
	public int getFPS(){
		return fps;
	}
}