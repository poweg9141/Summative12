package coreEngine;

import loaders.ScoreLoader;
import screen.DisplayManager;
import utils.Time;
import writers.TextWriter;

/**
 * 
 * @author Graham
 *
 */
public class Main {

	public static void main(String[] args) {
		//creates an object of the display manager
		DisplayManager manager = new DisplayManager();
		//creates a score loader to load and edit the games high scores
		ScoreLoader loader = new ScoreLoader();
		//starts the game launcher
		manager.openLauncher();
	}
}