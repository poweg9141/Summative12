/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.gameEnd;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import loaders.ImageLoader;
import screen.DisplayManager;
import screen.Window;

/**
 *
 * @author poweg9141, Michael
 */
public class GameLost {
    
    //stores the display manager, window and JFrame
    private DisplayManager manager;
    private Window window;
    private JFrame frame;
    
    //creates an image and panel to put the image in
    private JLabel image;
    private JPanel panel;
    
    /**
     * create a game lost screen
     * @param manager the display manager to control the screen
     * @param title the title of the screen
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public GameLost(DisplayManager manager, String title, int width, int height){
        window = new Window(title, width, height);
        this.manager = manager;
        frame = window.getFrame();
        initialize();
    }
    
    private void initialize(){
        //creates a buffered image of the lost png image
        BufferedImage lostIcon = ImageLoader.loadImage("endGame/gameLost", ImageLoader.IMAGE_PNG_FORMAT_ID);
        
        //creates the label and sets it to an image icon
        image = new JLabel();       
        image.setIcon(new ImageIcon(lostIcon));
        
        //adds the image to the panel and the panel to the screen
        panel = new JPanel();
        panel.add(image);
        frame.add(panel);
        //validates everything added to the frame
        frame.validate();
        
        //adds a mouse listener to the frame to listen for clicks
        frame.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e){
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //if the player clicked on a button, executed the correct code for that button
                if(e.getX() >= 110 && e.getX() <= 245 && e.getY() >= 450 && e.getY() <= 550){
                    frameVisibility(false);
                    manager.openGame();
                }else if(e.getX() >= 305 && e.getX() <= 530 && e.getY() >= 450 && e.getY() <= 550){
                    frameVisibility(false);
                    manager.openLauncher();
                }else if(e.getX() >= 590 && e.getX() <= 680 && e.getY() >= 450 && e.getY() <= 550){
                    manager.closeGame();
                }
             }

            @Override
            public void mouseReleased(MouseEvent e) {
             }

            @Override
            public void mouseEntered(MouseEvent e) {
             }

            @Override
            public void mouseExited(MouseEvent e) {
             }
        });
        
        frame.setVisible(true);
    }
    
    //sets the frame visibility to the passed in boolean
    public void frameVisibility(boolean visible){
        frame.setVisible(visible);
    }
}
