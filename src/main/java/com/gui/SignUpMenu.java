package com.gui;


import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SignUpMenu extends JPanel {

    RootPanel rootPanel;

    private JTextField nameTxtField;
    private JTextField loginTxtField;
    private JPasswordField passTxtField;
    private JPasswordField passConfTxtField;

    public SignUpMenu(RootPanel gameFrame) {
        this.rootPanel = gameFrame;

        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Chiller", Font.BOLD, 35));
        nameLabel.setForeground(Color.green);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        nameTxtField = new JTextField();
        nameTxtField.setPreferredSize(new Dimension(200, 28));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameTxtField, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setFont(new Font("Chiller", Font.BOLD, 35));
        loginLabel.setForeground(Color.green);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(loginLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        loginTxtField = new JTextField();
        loginTxtField.setPreferredSize(new Dimension(200, 28));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginTxtField, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Chiller", Font.BOLD, 35));
        passLabel.setForeground(Color.green);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        passTxtField = new JPasswordField();
        passTxtField.setPreferredSize(new Dimension(200, 28));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(passTxtField, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel passConfLabel = new JLabel("Confirm Password:");
        passConfLabel.setFont(new Font("Chiller", Font.BOLD, 35));
        passConfLabel.setForeground(Color.green);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(passConfLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        passConfTxtField = new JPasswordField();
        passConfTxtField.setPreferredSize(new Dimension(200, 28));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(passConfTxtField, gbc);


        gbc.insets = new Insets(20, 20, 20, 20);
        ButtonMenu registerButton = new ButtonMenu("Sign Up!");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(registerButton, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);
        ButtonMenu menuButton = new ButtonMenu("Back to Menu");
        menuButton.setFont(new Font("Chiller", Font.BOLD, 32));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(menuButton, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel signUpInfo = new JLabel("Zarejestrowano");
        signUpInfo.setFont(new Font("Chiller", Font.BOLD, 35));
        signUpInfo.setForeground(Color.green);
        signUpInfo.setVisible(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(signUpInfo, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel badSignUpInfo = new JLabel("Wpisano niepoprawne dane");
        badSignUpInfo.setFont(new Font("Chiller", Font.BOLD, 35));
        badSignUpInfo.setForeground(Color.RED);
        badSignUpInfo.setVisible(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(badSignUpInfo, gbc);

        nameTxtField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameTxtField.setBackground(Color.white);
            }
        });

        loginTxtField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginTxtField.setBackground(Color.white);
            }
        });

        passTxtField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passTxtField.setBackground(Color.white);
            }
        });

        passConfTxtField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passConfTxtField.setBackground(Color.white);
            }
        });


        registerButton.addActionListener(e -> {
            System.out.println("name " + nameTxtField.getText());
            System.out.println("login " + loginTxtField.getText());
            System.out.print("pass ");
            System.out.println((passTxtField.getPassword()));
            System.out.print("pass ");
            System.out.println((passConfTxtField.getPassword()));


            if (verifyName(nameTxtField.getText()) & verifyLogin(loginTxtField.getText()) & verifyPass(passTxtField, passConfTxtField)) {

                System.out.println("Zarejestrowano");
                nameTxtField.setBackground(Color.GREEN);
                loginTxtField.setBackground(Color.GREEN);
                passTxtField.setBackground(Color.GREEN);
                passConfTxtField.setBackground(Color.GREEN);

                new AddPlayerToDataBase(nameTxtField.getText(), loginTxtField.getText(), passConfTxtField.getPassword());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < 2; i++){
                          try{
                              Thread.sleep(2000);
                          } catch (Exception ex) {
                              ex.printStackTrace();
                          }
                          if(i == 0){
                              badSignUpInfo.setVisible(false);
                              signUpInfo.setVisible(true);
                          }
                          else
                              rootPanel.switchPanel(rootPanel.getMainMenu());
                        }
                    }
                }).start();
            } else {
                signUpInfo.setVisible(false);
                badSignUpInfo.setVisible(true);
                System.out.println("podano błędne dane");
            }
        });


        menuButton.addActionListener(e -> {
            System.out.println("Wróc do menu");

            rootPanel.switchPanel(rootPanel.getMainMenu());

        });
    }


    private boolean verifyName(String name) {

        if (name.length() >= 3 && name.matches("[A-Za-z]+")) {
            return true;
        } else {
            nameTxtField.setBackground(Color.RED);
            return false;
        }
    }

    private boolean verifyLogin(String login) {

        if (login.length() >= 3 && login.matches("[a-zA-Z0-9]+") && !(new FindPlayer().returnPlayer(login))) {
            return true;
        } else {
            loginTxtField.setBackground(Color.RED);
            return false;
        }
    }

    private boolean verifyPass(JPasswordField pass1, JPasswordField pass2) {

        if (Arrays.equals(pass1.getPassword(), pass2.getPassword())) {
            return verifyPass(pass1);
        } else {
            passConfTxtField.setBackground(Color.RED);
            return false;
        }
    }

    private boolean verifyPass(JPasswordField pass1) {
        if (pass1.getPassword().length >= 4) {
            return true;
        } else {
            passTxtField.setBackground(Color.RED);
            return false;
        }
    }


}
