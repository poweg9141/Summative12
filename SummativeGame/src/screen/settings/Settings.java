/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.settings;

import javax.swing.JFrame;
import screen.DisplayManager;
import screen.Window;

/**
 *
 * @author Graham
 */
public class Settings {
    
    private Window window;
    private JFrame frame;
    
    public Settings(DisplayManager manager, String title, int width, int height){
        window = new Window(title, width, height);
        frame = window.getFrame();
        initialize();
    }
    
    private void initialize(){
        
    }
    
}
