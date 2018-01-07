package loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;

import coreEngine.GameVariables;
import screen.DisplayManager;
import writers.TextWriter;

/**
 * 
 * @author Graham Power
 *class used to load from and save high scores to the text file
 */
public class ScoreLoader {

	//the file path the scores text file should be located
	public static final String SCORE_TEXT_FILE_PATH = "res/files/scores/scores.txt";
	
	//arrays to store the ordered names and corresponding points of the high scores
	private String[] names;
	private int[] points;
	
	//creates a score loader to load in, add new, and order scores
	public ScoreLoader(){
		//creates a new array to store the five high score lines that will be read in from the file
		String[] scores = new String[5];
		//initializes the arrays above
		points = new int[5];
		names = new String[5];
		//calls the method to read in the scores from the text file
		readScores(scores);
	}
	
	/**
	 * separates the names and points from the array read in from the file into their corresponding individual arrays
	 * @param scores
	 */
	private void orderScores(String[] scores){
		//loops the through the passed in array and splits it by spaces
		for(int i= 0; i < scores.length; i++){
			String[] split = scores[i].split(" ");
			//puts the name and points in the appropriate array
			names[i] = split[0];
			points[i] = Integer.parseInt(split[1]);
		}
	}
	
	/**
	 * reads in the scores from the text file and passes them to the original array
	 * @param scores the string of un-separated score info from the text file
	 */
	private void readScores(String[] scores){
		//creates a new file reader and file
		FileReader fr = null;
		File file = new File(SCORE_TEXT_FILE_PATH);
		try {
			//initializes the file reader
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			//if the file is not found, create a new file where it should be, 
			//and populate the array with a default name and 0 score			
			for(int i = 0; i < scores.length; i++){
				scores[i] = GameVariables.getDefaultHighScoreName() + " 0";
			}
			createNewFile(scores);
			//leaves the method since the array is not populated
			return;
		}
		//creates a new buffered reader using the file reader
		BufferedReader reader = new BufferedReader(fr);
		//creates a string to store the read in line
		String line;
		try {
			//counter to keep track of how many lines have been read in
			int i = 0;
			while((line = reader.readLine()) != null){
				//loops while there is a next line, saves the string to the apporiate spot in the scores array
				scores[i] = line;
				//increments the counter
				i++;
			}
		} catch (IOException e) {
			//deals with the exception if the line cannot be read
			DisplayManager.quitGameOnError(e, "Unable to readthe scores file, it may have been corrupted. "
					+ "Deleting the file may fix this problem although the list of high scores will be erased");
		}
		//orders the scores into their separate arrays
		orderScores(scores);
	}
	
	/**
	 * checks if a score is high enough to qualify as a high score, and if it does adds it to the list, and removes the lowest score
	 * @param name the name of the person who got the high score
	 * @param score the score they got
	 */
	public void addScore(String name, int score){
		//copys the arrays over to a new array with one extra spot
		String[] tempNames = Arrays.copyOf(names, names.length + 1);
		int[] tempScores = Arrays.copyOf(points, points.length + 1);

		//spltis the new name by its spaces and puts it back together without them, to ensure names have no spaces
		String[] newName = name.split(" ");
		name = "";
		for(int i = 0; i < newName.length; i++){
			name += newName[i];
		}
		//adds the new name and score to the empty spot in the new array
		tempNames[5] = name;
		tempScores[5] = score;
		
		//to keep track of wich position we are sorting
        for (int pos = 0; pos < tempScores.length; pos++) {
            //adds a varibale to store the smallest found number
            int smallest = pos;
            // go through the rest looking for a smaller number
            for (int i = pos + 1; i < tempScores.length; i++) {
                //have we found the smallest number so far
                if (tempScores[i] < tempScores[pos] && tempScores[i] < tempScores[smallest]) {
                    //mark it
                    smallest = i;
                }
            }
            //swap with the smallest number in the array
            swap(tempScores, smallest, pos);
            swap(tempNames, smallest, pos);
        }
        //copys the larger array back to the old array backwards, to ensure the smallest score gets left out
        for(int i = 1; i < tempScores.length; i++){
        	points[i - 1] = tempScores[i];
        	names[i - 1] = tempNames[i];
        }
	}
	
	//swaps two number in an array
    private void swap(int[] ar, int p1, int p2) {
        int temp = ar[p1];
        ar[p1] = ar[p2];
        ar[p2] = temp;
    }
    
    //swaps two strings in an array
    private void swap(String[] ar, int p1, int p2){
    	String temp = ar[p1];
        ar[p1] = ar[p2];
        ar[p2] = temp;
    }		
	
    /**
     * creates a new file and stores the list of scores in them
     * @param scores the array of names and scores to store in the file
     */
	private void createNewFile(String[] scores){
		//creates a new formatter
		Formatter f;
		try {
			//initializes the formatter with the file path the text file should be stored in
			f = new Formatter(SCORE_TEXT_FILE_PATH);
		} catch (FileNotFoundException e) {
			DisplayManager.quitGameOnError(e, "Unable to create the new file for the high scores list. "
					+ "There may already be a corrupted or uneditable file in the directory. "
					+ "Try deleting the scores file in the game directory at res/files/scores");
		}
		//orders the new array of scores
		orderScores(scores);
	}

	public String[] getNames() {
		return names;
	}

	public int[] getPoints() {
		return points;
	}
}
