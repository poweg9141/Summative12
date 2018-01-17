/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.options;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import screen.DisplayManager;
import screen.Window;

/**
 *
 * @author poweg9141
 */
public class Options {
    
    private Window window;
    private DisplayManager manager;
    private JFrame frame;
    
    private JTextField level, night, enemy, difficulty, player;
    private JComboBox levels, nights, enemies, difficulties, players;    
    String[] levelNames = {"one", "two", "three"};
    
    public Options(DisplayManager manager, String title, int width, int height){
        this.manager = manager;
        window = new Window(title, width, height);
        frame = window.getFrame();
    }
    
    private void intiialize(){
        
        frame.setLayout(new GridLayout());
        
        level = new JTextField("Select Level: ");
        frame.add(level);
        
        levels = new JComboBox(levelNames);
        frame.add(levels);
        
        frame.setVisible(true);
    }
    
    public void frameVisibility(boolean visible){
        frame.setVisible(visible);
    }
}
