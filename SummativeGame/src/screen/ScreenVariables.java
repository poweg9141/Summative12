package screen;

/**
 * class to store all the variables that control displaying screens, such as
 * their diameters
 *
 * @author Graham
 *
 */
public class ScreenVariables {

    //stores the variables for the game window
    private static final String gameName = "Hide n' Seek";
    private static int gameWidth = 700, gameHeight = 500;

    //stores the variables for the launcher window
    private static final String launcherName = "";
    private static final int launcherWidth = 200, launcherHeight = 180;

    //stores the variables for the game options window
    private static final String optionsName = "";
    private static final int optionsWidth = 180, optionsHeight = 300;

    //stores the variables for the settings window
    private static final String settingsName = "Settings";
    private static final int settingsWidth = 800, settingsHeight = 600;

    //stores the variables for the fatal error window
    private static final int fatalErrorWidth = 100, fatalErrorHeight = 100;

    //stores the variables for the game lost window
    private static final String gameLostName = "You Died!";
    private static final int gameLostWidth = 800, gameLostHeight = 600;

    //getters for all the variables
    public static String getGameName() {
        return gameName;
    }

    public static int getGameWidth() {
        return gameWidth;
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static String getLauncherName() {
        return launcherName;
    }

    public static int getLauncherWidth() {
        return launcherWidth;
    }

    public static int getLauncherHeight() {
        return launcherHeight;
    }

    public static String getOptionsName() {
        return optionsName;
    }

    public static int getOptionsWidth() {
        return optionsWidth;
    }

    public static int getOptionsHeight() {
        return optionsHeight;
    }

    public static String getSettingsName() {
        return settingsName;
    }

    public static int getSettingsWidth() {
        return settingsWidth;
    }

    public static int getSettingsHeight() {
        return settingsHeight;
    }

    public static int getFatalErrorWidth() {
        return fatalErrorWidth;
    }

    public static int getFatalErrorHeight() {
        return fatalErrorHeight;
    }

    public static String getGameLostName() {
        return gameLostName;
    }

    public static int getGameLostWidth() {
        return gameLostWidth;
    }

    public static int getGameLostHeight() {
        return gameLostHeight;
    }
}
