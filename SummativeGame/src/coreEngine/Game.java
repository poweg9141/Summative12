package coreEngine;

import screen.Window;
/*
 * @author Graham Power
 */
//class to run the games thread and control game logic
public class Game implements Runnable{

	//private window to display the game
	private Window display;
	//the title of the gmae (to be displayed on the JFrame)
	private String title;
	//the height and width of the game
	private int width, height;
	
	//thread to run the game
	private Thread thread;
	//stores whether the game is running or not
	private boolean running;
	
	/**
	 * Constructor to create the game
	 * @param title the title to be displayed to the games JFrame
	 * @param width the width of the games JFrame
	 * @param height the height of the games JFrame
	 */
	public Game(String title, int width, int height){
		//stores the passed in variables
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	//method to update the game every frame before rendering
	private void update(){
		
	}
	
	//method the render the game to the screen once updated
	private void render(){
		
	}

	//method to run the game
	@Override
	public void run() {		
		initialize();
		
		//game loop to run until game is closed
		while(running){
			update();
			render();
		}
		
		//calls the stop method to close the thread now that the game has been closed
		stop();
	}
	
	//initializes all the game code before the game loop
	private void initialize(){
		//creates a new display running on the created thread
		display = new Window(title, width, height);
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
}
