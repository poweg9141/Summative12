/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.gameEnd;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
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
 * @author Graham
 */
public class GameWon {
    
    private DisplayManager manager;
    private Window window;
    private JFrame frame;
    
    private JLabel image;
    private JPanel panel;
    
    public GameWon(DisplayManager manager, String title, int width, int height, double scoretime){
        window = new Window(title, width, height);
        this.manager = manager;
        frame = window.getFrame();
        initialize();
    }
    
    private void initialize(){
        BufferedImage lostIcon = ImageLoader.loadImage("endGame/gamewon", ImageLoader.IMAGE_PNG_FORMAT_ID);
        
        image = new JLabel();       
        image.setIcon(new ImageIcon(lostIcon));
        
        panel = new JPanel();
        panel.add(image);
        frame.add(panel);
        frame.validate();
        
        frame.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e){
            }

            @Override
            public void mousePressed(MouseEvent e) {
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
    
    public void frameVisibility(boolean visible){
        frame.setVisible(visible);
    }
}
