package screen;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * class used to create a simple window
 * @author Graham
 *
 */
public class Window {

	//variable to store the JFrame
	private JFrame frame;
	//canvas to add to the JFrame so things can be drawn
	private Canvas canvas;
	//variables to store the title width and height of the frame
	private String title;
	private int width, height;
	
	/**
	 * a simple window that can be created and called to create and display a new JFrame that is not resizable
	 * @param title the title of the JFrame
	 * @param width the width of the JFrame
	 * @param height the height of the JFrame
	 */
	public Window(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		intitialize();
	}
	
	/**
	 * initializes the JFrame once all parameters have been set
	 */
	private void intitialize(){
		//sets the title of the frame
		frame = new JFrame(title);
		//sets the height and width of the frame
		frame.setSize(width, height);
		//makes it so the program terminates when the x button is clicked
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//disables the ability to change the size of the JFrame
		frame.setResizable(false);
		//changes the original location of the JFrame to the centre of the screen
		frame.setLocationRelativeTo(null);
		//makes the JFrame visible
		frame.setVisible(true);			
	}
	
	public JFrame getFrame(){
		return frame;
	}
}