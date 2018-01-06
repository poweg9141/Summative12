package loaders;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Graham
 *
 */
public class ImageLoader {

	//integers used to identify the image format of the image being loaded
	public static final int IMAGE_JPEG_FORMAT_ID = 1281;
	public static final int IMAGE_JPG_FORMAT_ID = 1282;
	public static final int IMAGE_PNG_FORMAT_ID = 1283;
	
	/**
	 * loads and returns a buffered image of the image passed in
	 * @param file the directory of the image to be loaded, res/textures directory is assumed, file format (i.e. .jpeg) must not be included
	 * @param format the type of format the image uses, identified by a static call to the ImageLoader class (ex. ImageLoader.IMAGE_JPG_FORMAT_ID)
	 * @return the image as a buffered image
	 */
	public static BufferedImage loadImage(String file, int format){
		//creates the string that will hold the fille path of the passed in  image
		//puts in the textures folder directory and the file name passed in
		String dir = "/textures/" + file;
		//adds the correct file extension based on the passed in format ID
		if(format == IMAGE_JPEG_FORMAT_ID){
			dir += ".jpeg";
		}else if(format == IMAGE_JPG_FORMAT_ID){
			dir += ".jpg";
		}else if(format == IMAGE_PNG_FORMAT_ID){
			dir += ".png";
		}else{
			//if the passed in format ID is not recognized, prints out an error and exits the game
			System.out.println("Error: Image format ID not recognized for image '" + file + "'");
			System.exit(1);
		}
		
		try {
			//returns the loaded image at the specified file path
			return ImageIO.read(ImageLoader.class.getResource(dir));
		} catch (IOException e) {
			//if the image cant load prints out error message with simple troubleshooting suggestions
			System.out.println("Error Loading Image! File must be inside the "
					+ "res/textures directory with the correct file format ID");
			//prints out the stack trace
			e.printStackTrace();
			//exits the program
			System.exit(1);
		}
		//returns null to remove errors, effectively unreachable code
		return null;
	}
}
