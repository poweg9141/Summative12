package coreEngine;

import screen.Window;

//class to run the games thread and control game logic
public class Game {

	//private window to display the game
	private Window display;
	//the height and width of the game
	private int width, height;
	
	/**
	 * Constructor to create the game
	 * @param title the title to be displayed to the games JFrame
	 * @param width the width of the games JFrame
	 * @param height the height of the games JFrame
	 */
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		display = new Window(title, width, height);
	}
}
