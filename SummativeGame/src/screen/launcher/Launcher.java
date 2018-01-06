package screen.launcher;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import screen.DisplayManager;
import screen.Window;

/**
 * the class that creates the frame and handles the launcher window
 * @author Graham
 *
 */
public class Launcher{
	
	//string to store the title, width, height of the JFrame
	private String title;
	private int width, height;
	//Window object used to create a simple JFrame
	private Window window;
	//Display manager so that different windows can be opened at any time
	private DisplayManager manager;
	//stores the JFrame
	private JFrame frame;
	
	//the four buttons that will appear on the Games Launcher
	private JButton start, options, setting, quit;

	/**
	 * create the launcher with the settings and play options that appear before the game starts
	 * @param title the title of the JFrame the window will have
	 * @param width the width of the JFrame
	 * @param height the height of the JFrame
	 */
	public Launcher(DisplayManager manager, String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		this.manager = manager;
		//creates a window with the passed in parameters
		window = new Window(title, width, height);
		//calls the initialize method
		initialize();
	}
	
	//initializes all the buttons
	private void initialize(){
		//gets the JFrame from the window
		frame = window.getFrame();
		//sets the frames layout
		frame.setLayout(new FlowLayout());
		
		//creates the start button
		start = new JButton("START");
		start.setToolTipText("Start the game with all the applied settings and options.");
		start.setBackground(Color.GREEN);
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frameVisibility(false);
				manager.openGame();
			}
		});
		frame.add(start);		

		//creates the options button
		options = new JButton("GAME OPTIONS");
		options.setToolTipText("Edit options for in game play like map, day/night, etc.");
		frame.add(options);
		
		//creates the settings button
		setting = new JButton("SETTINGS");
		setting.setToolTipText("Change things like volume, FPS, etc.");
		frame.add(setting);
				
		//creates the quit button
		quit = new JButton("QUIT GAME");
		quit.setToolTipText("Quit the game.");
		//adds the action listener for the quit button
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//quits the game when the button is pressed
				manager.quitGame();
			}
		});
		frame.add(quit);
		
		//sets the frame to visible again to ensure it appears on the screen
		frame.setVisible(true);
	}
	
	private void frameVisibility(boolean visible){
		frame.setVisible(visible);
	}
}
