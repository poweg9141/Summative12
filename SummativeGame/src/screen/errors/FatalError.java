package screen.errors;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import screen.DisplayManager;
import screen.Window;

/**
 * a window to display the fatal error message to the user if the game has to crash
 * @author Graham
 *
 */
public class FatalError {
	
	//stores the display manager so it can access methods to control all other windows
	private DisplayManager manager;

	/**
	 * creates the fatal error window 
	 * @param manager the display manager that the window is created from
	 */
	public FatalError(DisplayManager manager){
		//stores the manager and calls the initialize method
		this.manager = manager;
		initialize();
	}
	
	private void initialize(){
		//creates a message dialog that tells the user the game must close and where to find the crash report
		JOptionPane.showMessageDialog(null, "The Game Has Encountered A Fatal Error And Must Close."
				+ "\nSee the full crash report in the games directory under res/files/crashReports");
	}
}
