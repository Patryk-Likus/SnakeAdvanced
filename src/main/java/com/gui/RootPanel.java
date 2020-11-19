package com.gui;

import com.game.GameOver;
import com.game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class RootPanel extends JRootPane {

    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private SignInMenu signInMenu;
    private SignUpMenu signUpMenu;
    private GamePanel gamePanel;
    private ScoreBoardMenu scoreBoardMenu;
    private GameOver gameOver;

    public RootPanel() {
        mainMenu = new MainMenu(this);
        settingsMenu = new SettingsMenu(this);
        signInMenu = new SignInMenu(this);
        signUpMenu = new SignUpMenu(this);
        scoreBoardMenu = new ScoreBoardMenu(this);
        gamePanel = new GamePanel(this);
        gameOver = new GameOver(this);
        contentPane.add(mainMenu);

    }

    public void switchPanel(JPanel panel) {

        contentPane.removeAll();
        if (panel.equals(gamePanel)) {
            contentPane.add(new GamePanel(this)).requestFocus();

        } else if (panel.equals(scoreBoardMenu)) {
            contentPane.add(new ScoreBoardMenu(this)).requestFocus();
        } else {
            contentPane.add(panel);
        }
        contentPane.repaint();
        contentPane.revalidate();
        panel.requestFocus();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public SettingsMenu getSettingsMenu() {
        return settingsMenu;
    }

    public SignInMenu getSignInMenu() {
        return signInMenu;
    }

    public SignUpMenu getSignUpMenu() {
        return signUpMenu;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoardMenu getScoreBoardMenu() {
        return scoreBoardMenu;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1080, 810);
    }
}
