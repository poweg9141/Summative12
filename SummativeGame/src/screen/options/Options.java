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

    private Selector handler;

    private JButton button;

    private JLabel level, night, enemy, difficulty, player;
    private JComboBox levels, nights, enemies, difficulties, players;
    String[] playerNames = {"bob"};
    String[] levelNames = {"Zero", "One", "Two", "Three", "Four", "Five"};
    String[] difficultyNames = {"Easy", "Medium", "Hard", "Extreme"};
    String[] times = {"Night", "Day"};
    String[] hunters = {"Disabled", "Easy", "Medium", "Hard", "Extreme"};

    public Options(DisplayManager manager, String title, int width, int height) {
        this.manager = manager;
        window = new Window(title, width, height);
        frame = window.getFrame();
        handler = new Selector();
        initialize();
    }

    private void initialize() {

        frame.setLayout(new GridLayout(0, 2));

        player = new JLabel("Player: ");
        frame.add(player);
        players = new JComboBox(playerNames);
        players.addItemListener(handler);
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
        frame.add(enemies);

        button = new JButton("DONE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameVisibility(false);
                manager.openLauncher();
            }
        });
        frame.add(button);

        frame.setVisible(true);
    }

    public void frameVisibility(boolean visible) {
        frame.setVisible(visible);
    }

    private class Selector implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == levels) {
                switch (levels.getSelectedIndex()) {
                    case 0:
                        GameVariables.setLevelFile("LevelZero");
                        break;
                    case 1:
                        GameVariables.setLevelFile("LevelOne");
                        break;
                    case 2:
                        GameVariables.setLevelFile("LevelTwo");
                        break;
                    case 3:
                        GameVariables.setLevelFile("LevelThree");
                        break;
                    case 4:
                        GameVariables.setLevelFile("LevelFour");
                        break;
                    case 5:
                        GameVariables.setLevelFile("LevelFive");
                        break;
                }
            } else if (e.getSource() == players) {
                switch (players.getSelectedIndex()) {
                    case 0:
                        break;
                }
            } else if (e.getSource() == difficulties) {
                switch (difficulties.getSelectedIndex()) {
                    case 0:
                        GameVariables.setRunnersToRender(3);
                        break;
                    case 1:
                        GameVariables.setRunnersToRender(6);
                        break;
                    case 2:
                        GameVariables.setRunnersToRender(8);
                        break;
                    case 3:
                        GameVariables.setRunnersToRender(12);
                        break;
                }
            } else if (e.getSource() == nights) {
                switch (nights.getSelectedIndex()) {
                    case 0:
                        GameVariables.setIsNight(true);
                        break;
                    case 1:
                        GameVariables.setIsNight(false);
                        break;
                }
            } else if (e.getSource() == enemies) {
                switch (enemies.getSelectedIndex()) {
                    case 0:
                        GameVariables.setHuntersToRender(0);
                        GameVariables.setHunterDamage(0);
                        break;
                    case 1:
                        GameVariables.setHuntersToRender(1);
                        GameVariables.setHunterDamage(1);
                        break;
                    case 2:
                        GameVariables.setHuntersToRender(2);
                        GameVariables.setHunterDamage(1);
                        break;
                    case 3:
                        GameVariables.setHuntersToRender(4);                        
                        GameVariables.setHunterDamage(2);
                        break;
                    case 4:
                        GameVariables.setHuntersToRender(6);
                        GameVariables.setHunterDamage(3);
                        break;
                }
            }
        }
    }
}
