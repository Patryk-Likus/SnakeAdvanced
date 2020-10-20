package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import static java.awt.Color.*;

public class GamePanel extends JPanel implements ActionListener {

    private static final int SCREEN_HEIGHT = 750;
    private static final int SCREEN_WIDTH = 750;
    private static final int UNIT = 30;
    private static final int GAME_UNIT = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT;
    private int[] snakeX = new int[GAME_UNIT];
    private int[] snakeY = new int[GAME_UNIT];
    private int snakeSize = 8;
    private int appleX;
    private int appleY;
    private boolean running;
    private Timer timer;
    private Random random;
    char direction = 'R';
    private static final int DELAY = 80;
    private int score = 0;


    public GamePanel() {
        setLayout(null);
        setBackground(BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKey());
        startGame();


    }

    public void startGame() {
        running = true;
        random = new Random();
        newApple();
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT)) * UNIT;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT)) * UNIT;
    }

    public void checkApple() {
        for(int i = snakeSize; i > 0; i--){
            if(snakeX[0] == appleX && snakeY[0] == appleY){
                newApple();
                snakeSize++;
                score += 23;
            }
        }
    }

    public void crash() {
        for (int i = 0; i < snakeSize; i++) {
            if (snakeX[0] == snakeX[i + 1] && snakeY[0] == snakeY[i + 1]) {
                running = false;
            }
        }
        if (snakeX[0] < 0 || snakeX[0] > SCREEN_WIDTH || snakeY[0] < 0 || snakeY[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if(!running){
            timer.stop();
        }

    }

    public void move() {
        for (int i = snakeSize; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        if (running) {
            switch (direction) {
                case 'L':
                    snakeX[0] = snakeX[0] - UNIT;
                    break;
                case 'R':
                    snakeX[0] = snakeX[0] + UNIT;
                    break;
                case 'U':
                    snakeY[0] = snakeY[0] - UNIT;
                    break;
                case 'D':
                    snakeY[0] = snakeY[0] + UNIT;
                    break;
            }
        }
    }

    public void gameOver(Graphics g) {
            g.setColor(RED);
            g.setFont(new Font("Arial",Font.BOLD, 100));
            FontMetrics fontMetrics1 = getFontMetrics(g.getFont());
            g.drawString("Game over", (SCREEN_WIDTH - fontMetrics1.stringWidth("Game over")) / 2, SCREEN_HEIGHT / 2);
            g.setColor(RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics fontMetrics2 = getFontMetrics(g.getFont());
            g.drawString("Score: " + score, (SCREEN_WIDTH - fontMetrics2.stringWidth("Score: " + score)) / 2, (SCREEN_HEIGHT / 2) + 60);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(running) {
            g.setColor(RED);
            g.fillOval(appleX, appleY, UNIT, UNIT);

            for (int i = 0; i < snakeSize; i++) {
                if (i == 0) {
                    g.setColor(GREEN);
                    g.fillRect(snakeX[i], snakeY[i], UNIT, UNIT);
                } else {
                    g.setColor(BLUE);
                    g.fillRect(snakeX[i], snakeY[i], UNIT, UNIT);
                }
            }
            g.setColor(RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics fontMetrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + score, SCREEN_WIDTH - fontMetrics.stringWidth("Score: " + score), 30);
        }
        else
            gameOver(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            crash();
        }
        repaint();
    }

    class MyKey extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
