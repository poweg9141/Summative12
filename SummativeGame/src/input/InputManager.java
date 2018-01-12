package input;

import coreEngine.Game;
import coreEngine.GameVariables;
import entities.mobs.Flashlight;
import entities.mobs.Player;

/**
 * takes the inputs from the KeyInput class and does the appropriate action based on the input
 * @author Graham
 *
 */
public class InputManager {

	//stores an instance of the game this class is managing inputs for
	private Game game;
	//stores an instance of the player
	private Player player;
        //stores an instance of the flashlight
        private Flashlight flashlight;
	
	/**
	 * creates an input manager to manage inputs for a Game
	 * @param game the game it will manage inputs for
	 * @param player the player it will manage inputs for
         * @param flashlight the flashlight it will manage inputs for
	 */
	public InputManager(Game game, Player player, Flashlight flashlight){
		this.game = game;
		this.player = player;
                this.flashlight = flashlight;
	}
	
	/**
	 * called by the KeyInput class, handles inputs for the players movement
	 * @param w whether the w key has been pressed
	 * @param a whether the a key has been pressed
	 * @param s whether the s key has been pressed
	 * @param d whether the d key has been pressed
	 */
	public void wasdPressed(boolean w, boolean a, boolean s, boolean d){
		//sets the x and y positions to 0
		float x = 0;
		float y = 0;
		
		//if a key has been pressed calculates the new value for the x or y position
		//new values are the direction identifier (positive or negative), multiplied by the players speed, 
		//multiplied by the time between updates
		if(w){
			y += (float)(-(GameVariables.getPlayerSpeed() * game.getGameTimeSeconds()));
		}
		if(s){
			y += (float)(GameVariables.getPlayerSpeed() * game.getGameTimeSeconds());
		}
		if(a){
			x += (float) (-(GameVariables.getPlayerSpeed() * game.getGameTimeSeconds()));
		}
		if(d){
			x += (float)(GameVariables.getPlayerSpeed() * game.getGameTimeSeconds());
		}
		//calls the players move method and passed in the x and y displacements calculated above
		player.move(x, y);
                //calls the flashlights move method and moves so it is still perfectly centered around the player
                flashlight.move(x,y);
	}
}
