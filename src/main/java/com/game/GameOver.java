package com.game;

import com.gui.ButtonMenu;
import com.gui.RootPanel;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    RootPanel rootPanel;


    public GameOver(RootPanel gameFrame) {
        this.rootPanel = gameFrame;

        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 20, 20);
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Chiller", Font.BOLD, 230));
        gameOverLabel.setForeground(Color.red);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(gameOverLabel, gbc);

        ButtonMenu playButton = new ButtonMenu("Play again");
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(playButton, gbc);

        ButtonMenu menuButton = new ButtonMenu("Back to Menu");
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(menuButton, gbc);


        System.out.println();


        playButton.addActionListener(e -> {
            System.out.println("graj ponownie");

            rootPanel.switchPanel(rootPanel.getGamePanel());

        });

        menuButton.addActionListener(e -> {
            System.out.println("Wróc do menu");

            rootPanel.switchPanel(rootPanel.getMainMenu());

        });
    }
}
