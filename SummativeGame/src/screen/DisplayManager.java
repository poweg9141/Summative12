package screen;

import javax.swing.JFrame;

import coreEngine.Game;
import screen.launcher.Launcher;

/**
 * handles every window in the game and controls switching between them
 * @author Graham
 *
 */
public class DisplayManager {
	//stores an instance of the launcher window
	private Launcher launcher;
	//stores an instance of the game to manage its window
	private Game game;
	
	/**
	 * empty constructor, here in case of later implementation
	 */
	public DisplayManager(){
		
	}
	
	//used to open the launcher or start it if it has not already been initialized
	public void openLauncher(){
		if(launcher == null){
			launcher = new Launcher(this, ScreenVariables.getLauncherName(), 
					ScreenVariables.getLauncherWidth(), ScreenVariables.getLauncherHeight());
		}else{
			
		}
	}
	
	//used to open the game or start it if it has not already been initialized
	public void openGame(){
		if(game == null){
			game = new Game(this, ScreenVariables.getGameName(), 
					ScreenVariables.getGameWidth(), ScreenVariables.getGameHeight());
			game.start();
		}else{
			
		}
	}
	
	//used to quit the game, ending all processes and closing all windows
	public void quitGame(){
		System.exit(0);
	}
	
	//used to open the settings window or start it if it has not already been initialized
	public void openSettings(){
		//does nothing at the moment
		throw new UnsupportedOperationException();
	}
	
	//used to open the options window or start it if it has not already been initialized
	public void openOptions(){
		//does nothing at the moment
		throw new UnsupportedOperationException();
	}
}