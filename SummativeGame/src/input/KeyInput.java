package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import coreEngine.Game;
import entities.mobs.Player;

/**
 * takes in inputs from the keyboard
 * @author Graham
 *
 */
public class KeyInput implements KeyListener{
	//stores the input manager it will be passing inputs to
	private InputManager manager;
	//array to store whether each key has been pressed based on its key ID
	public boolean[] keys;
	
	/**
	 * creates an input listener to listen for inputs from a Game
	 * @param game the game it will listen for inputs from
	 * @param player the player, or players it will listen for inputs for
	 */
	public KeyInput(Game game, Player... player){
		//initializes the key id array
		keys = new boolean[256];
		//creates an input manager for every player passed in
		for(int i = 0; i < player.length; i++){
			manager = new InputManager(game, player[i]);
		}
	}
	
	//calls the appropriate methods from the input manager for every it is listening for
	public void update(){
			manager.wasdPressed(keys[KeyEvent.VK_W], keys[KeyEvent.VK_A], keys[KeyEvent.VK_S], keys[KeyEvent.VK_D]);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//sets the correct keyCode in the array to true since it has been pressed
		keys[e.getKeyCode()] = true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		//sets the correct keyCode in the array to false since it has been released
		keys[e.getKeyCode()] = false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		//does nothing at the moment, here because implementing keyListener requires this method
	}

}
