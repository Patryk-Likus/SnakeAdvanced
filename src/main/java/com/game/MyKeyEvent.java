package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyEvent extends KeyAdapter {
    GamePanel gamePanel;

    public MyKeyEvent(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);


        char k = e.getKeyChar();
        if (k == 'k') {

            gamePanel.score = 0;
            gamePanel.snakeSize = 8;
            gamePanel.timer.stop();
            gamePanel.startGame();


        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (gamePanel.direction != 'R') {
                    gamePanel.direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (gamePanel.direction != 'L') {
                    gamePanel.direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (gamePanel.direction != 'D') {
                    gamePanel.direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (gamePanel.direction != 'U') {
                    gamePanel.direction = 'D';
                }
                break;
        }
    }
}


