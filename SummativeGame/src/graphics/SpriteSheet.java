package graphics;

import java.awt.image.BufferedImage;

/*
 * @author Graham Power
 */
public class SpriteSheet {
	
	//buffered image to store the original sheet
	private BufferedImage sheet;
	//array of buffered images to store all the individual sprites
	private BufferedImage[] images;
	
	//stores the number of rows/columns in the sheet and the size of each image in the sheet
	private int numRows, step;
	
	/**
	 * create a sprite sheet out of a single square image file
	 * @param sheet the bufferedImage to make the sheet out of
	 * @param numRows the number of rows and columns in the sheet (sheet must be square with power of 2 dimensions)
	 * @param step the size of each individual image in the sprite sheet
	 */
	public SpriteSheet(BufferedImage sheet, int numRows, int step){
		//stores all the passed in variables
		this.sheet = sheet;
		this.numRows = numRows;
		this.step = step;
		//creates the buffered image array of the right size
		images = new BufferedImage[numRows * numRows];
		//calls the method to arrange the sprites into the array
		arrangeSheet();
	}
	
	//arranges a sprite sheet into an array of individual sprites
	private void arrangeSheet(){
		//loops through for the length of the array
		for(int i = 0; i < images.length; i++){
			//calculates the row and column of the sprite sheet the desired image is located
			int row = i / numRows;
			int col = i % numRows;
			//calculates the x and y pixel the desired image starts at
			int xPos = col * step;
			int yPos = row * step;
			//saves the image from the sprite sheet to the corresponding index of the sprite sheet array
			images[i] = sheet.getSubimage(xPos, yPos, step, step);
		}
	}
	
	/**
	 * gets the sprite from the sprite sheet at the desired index (starts at 0)
	 * @param index the index the desired sprite is located
	 * @return a buffered image of the desired sprite
	 */
	public BufferedImage getSpriteAtIndex(int index){
		return images[index];
	}
}
