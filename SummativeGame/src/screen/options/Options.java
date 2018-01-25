/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.options;

import coreEngine.GameVariables;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import screen.DisplayManager;
import screen.Window;

/**
 *
 * @author poweg9141, Michael
 */
public class Options {

    //stores the window, display manager and the frame
    private Window window;
    private DisplayManager manager;
    private JFrame frame;

    //stores the selector used to select things
    private Selector handler;

    //stroes the done JButton
    private JButton button;

    //creates all the labels and combo boxes for all the options with the array of selections
    private JLabel level, night, enemy, difficulty, player;
    private JComboBox levels, nights, enemies, difficulties;
    private JTextField players;
    String[] playerNames = {"bob"};
    String[] levelNames = {"Zero", "One", "Two", "Three", "Four", "Five"};
    String[] difficultyNames = {"Easy", "Medium", "Hard", "Extreme"};
    String[] times = {"Night", "Day"};
    String[] hunters = {"Disabled", "Easy", "Medium", "Hard", "Extreme"};

    /**
     * creates an options window
     * @param manager the manager the window will be controlled from
     * @param title the title of the window
     * @param width the width of the window
     * @param height the height of the window
     */
    public Options(DisplayManager manager, String title, int width, int height) {
        this.manager = manager;
        window = new Window(title, width, height);
        frame = window.getFrame();
        handler = new Selector();
        initialize();
    }

    //initializes the frame
    private void initialize() {

        //sets if the a new grid layout with 2 columns
        frame.setLayout(new GridLayout(0, 2));

        //adds all the text fields and combo boxes in the correct order the the frame
        //as well as setting the combo boxes item listener to the handler class
        player = new JLabel("Player: ");
        frame.add(player);
        players = new JTextField();
        frame.add(players);

        level = new JLabel("Select Level: ");
        frame.add(level);

        levels = new JComboBox(levelNames);
        levels.addItemListener(handler);
        frame.add(levels);

        difficulty = new JLabel("Difficulty: ");
        frame.add(difficulty);

        difficulties = new JComboBox(difficultyNames);
        difficulties.addItemListener(handler);
        frame.add(difficulties);

        night = new JLabel("Time Of Day: ");
        frame.add(night);

        nights = new JComboBox(times);
        nights.addItemListener(handler);
        frame.add(nights);

        enemy = new JLabel("Hunters: ");        
        frame.add(enemy);

        enemies = new JComboBox(hunters);
        enemies.addItemListener(handler);
        frame.add(enemies);

        button = new JButton("DONE");
        //adds the action listener to the jbutton
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stores the string of the name from the name text field box, removeing whitespace
                String name = players.getText().replaceAll(" ", "");
                //if the name string is not empty, sets the players name to the one they passed in
                if(name != null && !"".equals(name)){
                    GameVariables.setPlayerName(name);
                }
                //makes this frame invisible
                frameVisibility(false);
                //opens the launcher again
                manager.openLauncher();
            }
        });
        frame.add(button);

        frame.setVisible(true);
    }

    public void frameVisibility(boolean visible) {
        frame.setVisible(visible);
    }

    //sets the apropriate variables based on what they selected, and prints their selection to the screen
    private class Selector implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == levels) {
                switch (levels.getSelectedIndex()) {
                    case 0:
                        GameVariables.setLevelFile("LevelZero");
                        System.out.println("level 0");
                        break;
                    case 1:
                        GameVariables.setLevelFile("LevelOne");
                        System.out.println("level 1");
                        break;
                    case 2:
                        GameVariables.setLevelFile("LevelTwo");
                        System.out.println("level 2");
                        break;
                    case 3:
                        GameVariables.setLevelFile("LevelThree");
                        System.out.println("level 3");
                        break;
                    case 4:
                        GameVariables.setLevelFile("LevelFour");
                        System.out.println("level 4");
                        break;
                    case 5:
                        GameVariables.setLevelFile("LevelFive");
                        System.out.println("level 5");
                        break;
                }
            }else if (e.getSource() == difficulties) {
                switch (difficulties.getSelectedIndex()) {
                    case 0:
                        GameVariables.setRunnersToRender(3);
                        System.out.println("difficulty easy");
                        break;
                    case 1:
                        GameVariables.setRunnersToRender(6);
                        System.out.println("difficulty medium");
                        break;
                    case 2:
                        GameVariables.setRunnersToRender(8);
                        System.out.println("difficulty hard");
                        break;
                    case 3:
                        GameVariables.setRunnersToRender(12);
                        System.out.println("difficulty extreme");
                        break;
                }
            } else if (e.getSource() == nights) {
                switch (nights.getSelectedIndex()) {
                    case 0:
                        GameVariables.setIsNight(true);
                        System.out.println("night");
                        break;
                    case 1:
                        GameVariables.setIsNight(false);
                        System.out.println("day");
                        break;
                }
            } else if (e.getSource() == enemies) {
                switch (enemies.getSelectedIndex()) {
                    case 0:
                        GameVariables.setHuntersToRender(0);
                        GameVariables.setHunterDamage(0);
                        System.out.println("hunter disabled");
                        break;
                    case 1:
                        GameVariables.setHuntersToRender(1);
                        GameVariables.setHunterDamage(1);
                        System.out.println("hunter easy");
                        break;
                    case 2:
                        GameVariables.setHuntersToRender(2);
                        GameVariables.setHunterDamage(1);
                        System.out.println("hunter medium");
                        break;
                    case 3:
                        GameVariables.setHuntersToRender(4);                        
                        GameVariables.setHunterDamage(2);
                        System.out.println("hunter hard");
                        break;
                    case 4:
                        GameVariables.setHuntersToRender(6);
                        GameVariables.setHunterDamage(3);
                        System.out.println("hunter extreme");
                        break;
                }
            }
        }
    }
}
