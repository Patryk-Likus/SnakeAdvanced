package com.gui;

import com.game.GamePanel;
import com.jdbc.FindPlayer;

import javax.swing.*;
import java.awt.*;

public class SignInMenu extends JPanel {

    RootPanel rootPanel;
    GamePanel gamePanel;

    public SignInMenu(RootPanel gameFrame) {
        this.rootPanel = gameFrame;
        gamePanel = new GamePanel(this.rootPanel);

        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(new Font("Chiller", Font.BOLD, 35));
        loginLabel.setForeground(Color.green);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(loginLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        JTextField loginTxtField = new JTextField();
        loginTxtField.setPreferredSize(new Dimension(200, 28));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(loginTxtField, gbc);


        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Chiller", Font.BOLD, 35));
        passLabel.setForeground(Color.green);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        JPasswordField passTxtField = new JPasswordField();
        passTxtField.setPreferredSize(new Dimension(200, 28));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passTxtField, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);
        ButtonMenu loginButton = new ButtonMenu("Login");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(loginButton, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);
        ButtonMenu guestButton = new ButtonMenu("Play as Guest");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(guestButton, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);
        ButtonMenu menuButton = new ButtonMenu("Back to Menu");
        menuButton.setFont(new Font("Chiller", Font.BOLD, 32));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(menuButton, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);
        JLabel loginInfo = new JLabel("Zalogowano");
        loginInfo.setFont(new Font("Chiller", Font.BOLD, 50));
        loginInfo.setForeground(Color.green);
        loginInfo.setVisible(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(loginInfo, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);
        JLabel badLoginInfo = new JLabel("Niepoprawny login lub haslo");
        badLoginInfo.setFont(new Font("Chiller", Font.BOLD, 50));
        badLoginInfo.setForeground(Color.RED);
        badLoginInfo.setVisible(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(badLoginInfo, gbc);


        loginButton.addActionListener(e -> {

            if (new FindPlayer().signInPlayer(loginTxtField.getText(), passTxtField.getPassword())) {
                gamePanel.login = loginTxtField.getText();
                System.out.println(loginTxtField.getText() + " " + passTxtField.getPassword());
                System.out.println("Zalogowano");
                loginTxtField.setBackground(Color.GREEN);
                passTxtField.setBackground(Color.GREEN);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2; i++) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            if (i == 0) {
                                badLoginInfo.setVisible(false);
                                loginInfo.setVisible(true);
                            }
                            else
                                rootPanel.switchPanel(rootPanel.getMainMenu());
                        }
                    }
                }).start();
            } else {
                loginTxtField.setBackground(Color.RED);
                passTxtField.setBackground(Color.RED);
                System.out.println("Niepoprawny login lub haslo");
                loginInfo.setVisible(false);
                badLoginInfo.setVisible(true);
            }
        });

        guestButton.addActionListener(e ->
        {
            System.out.println("Tutaj zagrasz jako gość");
            rootPanel.switchPanel(rootPanel.getGamePanel());
        });


        menuButton.addActionListener(e ->
        {
            System.out.println("Wróc do menu");
            rootPanel.switchPanel(rootPanel.getMainMenu());
        });

    }

}
