package GUI;

import javax.swing.*;


public class GameFrame extends JFrame {

    public GameFrame(){
        this.add(new GamePanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(540,50);
        this.setTitle("Snake Advanced");
        this.setVisible(true);
        this.pack();
    }
}
