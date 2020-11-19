package com.gui;

import javax.swing.*;
import java.awt.*;

public class ButtonMenu extends JButton {

    String text;
    public ButtonMenu(String text) {
        super(text);
        this.text = text;


        setFont(new Font("Chiller", Font.BOLD, 50));
        setForeground(Color.green);
        setBorder(null);
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setForeground(Color.yellow);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {

                new Sounds().playSound("src/sounds/biteSound2.wav");
                setForeground(Color.green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setForeground(Color.green);
            }
        });

    }

//    public void paint(Graphics g){
//        g.drawString(text,0,0);
//        g.setColor(Color.RED);
//
//    }
}
