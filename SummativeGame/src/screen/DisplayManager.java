package screen;

import javax.swing.JFrame;

import coreEngine.Game;
import java.util.Timer;
import screen.errors.FatalError;
import screen.gameEnd.GameLost;
import screen.gameEnd.GameWon;
import screen.launcher.Launcher;
import screen.options.Options;
import writers.TextWriter;

/**
 * handles every window in the game and controls switching between them
 *
 * @author Graham
 *
 */
public class DisplayManager {
    //stores an instance of the launcher window

    private Launcher launcher;
    //stores an instance of the game to manage its window
    private Game game;
    //
    private Options options;
    //stores an instance of this manager for use in static classes
    private GameLost lost;
    private GameWon won;
    static DisplayManager thisManager;

    long startTime;
    long endTime;
    /**
     * empty constructor, here in case of later implementation
     */
    public DisplayManager() {
        //initializes the manager
        thisManager = this;
    }

    //used to open the launcher or start it if it has not already been initialized
    public void openLauncher() {
        if (launcher == null) {
            launcher = new Launcher(this, ScreenVariables.getLauncherName(),
                    ScreenVariables.getLauncherWidth(), ScreenVariables.getLauncherHeight());
        } else {
            launcher.frameVisibility(true);
        }
    }

    //used to open the game or start it if it has not already been initialized
    public void openGame() {
        startTime = System.currentTimeMillis();
        if (game == null) {
            game = new Game(this, ScreenVariables.getGameName(),
                    ScreenVariables.getGameWidth(), ScreenVariables.getGameHeight(), startTime);
            game.start();
            //game.run();
        } else {
            game.gameVisibility(true);
        }
    }
    
    public void openWin(){
        endTime = System.currentTimeMillis();
        if(won == null){
            won = new GameWon(this, ScreenVariables.getGameWonName(), 
                    ScreenVariables.getGameWonWidth(), ScreenVariables.getGameWonHeight(), ((endTime-startTime)/1000));
        }else{
            won.frameVisibility(true);
        }
    }
    
    public void openLost(){
        endTime = System.currentTimeMillis();
        if(lost == null){
            lost = new GameLost(this, ScreenVariables.getGameLostName(), 
                    ScreenVariables.getGameLostWidth(), ScreenVariables.getGameLostHeight(), ((endTime-startTime)/1000));
        }else{
            lost.frameVisibility(true);
        }            
    }

    //used to quit the game, ending all processes and closing all windows
    public void quitGame(boolean won) {        
        game = null; 
        if(won){
            openWin();
        }else{
            openLost();
        }   
    }
    
    public void closeGame(){
        System.exit(0);        
    }

    /**
     * quits the game and handles the given exception along with the provided
     * explanation
     *
     * @param e the exception that cause the game to crash
     * @param explaination the explanation of what went wrong as provided by the
     * individual who wrote the code
     */
    public static void quitGameOnError(Exception e, String explaination) {
        //prints the error message and the stack trace to the console for help with debugging code
        System.err.println("The Game Has Encountered A Fatal Error And Must Close.\nSee the full crash report in the games directory under res/files/crashReports");
        System.out.println("Developer Explaination:");
        System.out.println(explaination);
        System.out.println("Java Stack Trace:");
        System.out.println(e);
        //creates a new FatalError window to let the user know the game will crash
        new FatalError(thisManager);
        //calls the text writer and passes the error and explanation so it can be written to an error file
        TextWriter.writeError(e, explaination);
        //exits the program
        System.exit(1);
    }

    //used to open the settings window or start it if it has not already been initialized
    public void openSettings() {
        //does nothing at the moment
        Exception e = new UnsupportedOperationException();
        DisplayManager.quitGameOnError(e, "The method called is not yet supported.");
    }

    //used to open the options window or start it if it has not already been initialized
    public void openOptions() {
        if (options == null) {
            options = new Options(this, ScreenVariables.getOptionsName(), 
                    ScreenVariables.getOptionsWidth(), ScreenVariables.getOptionsHeight());
        } else {
            options.frameVisibility(true);
        }
    }
}