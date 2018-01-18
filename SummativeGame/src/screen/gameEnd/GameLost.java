/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.gameEnd;

import javax.swing.JFrame;
import screen.DisplayManager;
import screen.Window;

/**
 *
 * @author poweg9141
 */
public class GameLost {
    
    private DisplayManager manager;
    private Window window;
    private JFrame frame;
    
    public GameLost(DisplayManager manager, String title, int width, int height){
        window = new Window(title, width, height);
        this.manager = manager;
        frame = window.getFrame();
        initialize();
    }
    
    private void initialize(){
        
    }
}
