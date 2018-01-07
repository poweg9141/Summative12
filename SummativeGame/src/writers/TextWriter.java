package writers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import loaders.ScoreLoader;
import screen.DisplayManager;
import utils.Time;

/**
 * class will hold static method that write various things to text files
 * @author Graham
 *
 */
public class TextWriter {
	
	//stores the directory and the extension of the crash report files that will be created if the game crashed
	private final static String ERROR_FILE_PATH = "res/files/crash-reports/";
	private final static String ERROR_FILE_EXTENSION = ".txt";

	/**
	 * writes the high scores from the game back to the text file, to be used when the game has been closed
	 * @param names an array of the names of the people with high sores, must be in order with their scores in the scores array
	 * @param points an array of the points of the people with the high scores, must be in order with their names in the names array
	 */
	public static void writeScores(String[] names, int[] points){		
		try {
			//trys to create a file and print reader using the sore loaders file path
			//NOTE: because of the functionality of the score loader, if it was called at any point, 
			//the existence of a file at this location is guaranteed
			FileWriter f = new FileWriter(ScoreLoader.SCORE_TEXT_FILE_PATH);
			PrintWriter w = new PrintWriter(f);
			
			//writes the names and points of all five high scores
			for(int i = 0; i < points.length; i++){
				w.println(names[i] + " " + points[i]);
			}			
			//closes the file reader
			w.close();
		} catch (IOException e) {
			//if there is any problem with the file reader it closes the game
			DisplayManager.quitGameOnError(e, "The scores file could not be created or written to, it may be corrupted or unreadable");
		}			
	}
	
	/**
	 * creates and writes an error to a file in the crash reports directory
	 * @param e the error to be written
	 * @param explaination the explanation, as provided in the code by the individual coding it
	 */
	public static void writeError(Exception e, String explaination){
		//creates a string and print writer to convert the error into a string
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		//converts the error into a string
		e.printStackTrace(pw);
		String stackTrace = sw.toString();
		//stores the date, from the Time class, into an array separated by spaces
		String[] fullDateStamp = Time.getCurrentTimeStamp().split(" ");
		//creates a string to store the reformatted date stamp
		String dateStamp = "";
		//loops through the date stamp array and combines it into a full string with each index separated by a '-'
		for(int i = 0; i < fullDateStamp.length; i++){
			dateStamp += fullDateStamp[i];
			if(i < fullDateStamp.length - 1){
				dateStamp += "-";
			}
		}
		
		//creates the crash reports file directory by combining the path with 
		//the files name (the name plus the reformatted date stamp) and the file extension
		String crashDir = ERROR_FILE_PATH + "crash-report-" + dateStamp + ERROR_FILE_EXTENSION;
		//replaces all : in the directory with ; since siles with : in the directory name cannot be created
		crashDir = crashDir.replaceAll(":", ";");
		
		try {
			//creates a file and print reader to write to a text file with the crashDir name
			FileWriter f = new FileWriter(crashDir);
			PrintWriter w = new PrintWriter(f);
			
			//writes the explanation followed by the stack trace, in format
			w.println("Developer Explaination:");
			w.println(explaination);
			w.println(" ");
			w.println("Java StackTrace:");
			w.println(stackTrace);
			//closes the writer
			w.close();
		} catch (IOException e1) {
			//if there is a problem it just closes the game, since you cannot handle an error in your error handling code
			System.exit(1);			
		}
	}
}
