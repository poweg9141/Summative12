package coreEngine;

import loaders.ScoreLoader;
import screen.DisplayManager;

/**
 * 
 * @author Graham
 *
 */
public class Main {

	public static void main(String[] args) {
		//creates an object of the display manager
		DisplayManager manager = new DisplayManager();
		//starts the game launcher
		manager.openLauncher();
	}
}