package com.game;

import com.gui.RootPanel;
import com.gui.Sounds;
import com.jdbc.FindPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public class GamePanel extends JPanel implements ActionListener {


    private static final int SCREEN_HEIGHT = 810;
    private static final int SCREEN_WIDTH = 1080;
    private static final int UNIT = 30;
    private static final int GAME_UNIT = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT;
    private int[] snakeX = new int[GAME_UNIT];
    private int[] snakeY = new int[GAME_UNIT];
    int snakeSize = 8;
    private int appleX;
    private int appleY;
    private int badAppleX;
    private int badAppleY;
    private boolean running;
    Timer timer;
    private Random random;
    char direction = 'R';
    private static final int DELAY = 80;
    int score = 0;
    RootPanel rootPanel;
    static boolean isWallHack;
    static boolean isEclipseEffect;
    private int appleEaten = 0;
    private int timeGame = 0;
    public static String login = "";
    private boolean isAppleVisible = true;
    private static boolean isBadAppleVisible = true;

    public GamePanel(RootPanel gameFrame) {
        this.rootPanel = gameFrame;
        setLayout(null);
        setBackground(BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyEvent(this));
        startGame();

    }

    public void startGame() {
        running = true;
        random = new Random();
        snakeX = new int[GAME_UNIT];
        snakeY = new int[GAME_UNIT];
        newApple();
        drawBadApple();
        direction = 'R';
        timer = new Timer(DELAY, this);
        timer.start();
        stoper();
        requestFocus();

    }

    public void wallHack(boolean value) {
        isWallHack = value;
    }

    public void eclipseEffectSettingsMenu(boolean value) {
        isEclipseEffect = value;
    }

    public void newApple() {

        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT)) * UNIT;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT)) * UNIT;
    }

    public void checkApple() {
        if (isAppleVisible) {
            for (int i = snakeSize; i > 0; i--) {
                if (snakeX[0] == appleX && snakeY[0] == appleY) {
                    new Sounds().playSound("src/sounds/apple.wav");
                    newApple();
                    snakeSize++;
                    score += 23;
                    appleEaten++;
                }
            }
        }
    }


    public void drawBadApple() {

        badAppleX = random.nextInt((int) (SCREEN_WIDTH / UNIT)) * UNIT;
        badAppleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT)) * UNIT;
    }

    public void badAppleVisibleMode() {

        new Thread(() -> {
            try {
                isBadAppleVisible = false;
                drawBadApple();
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isBadAppleVisible = true;
        }).start();
    }


    public void stoper() {

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeGame++;
            }
        }).start();
    }

    public void checkBadApple() {
        if (isBadAppleVisible && isEclipseEffect) {
            for (int i = snakeSize; i > 0; i--) {
                if (snakeX[0] == badAppleX && snakeY[0] == badAppleY) {
                    new Sounds().playSound("src/sounds/badapple.wav");
                    changeVisibleApple();
                    badAppleVisibleMode();
                    score = 0;
                }
            }
        }
    }
    public void changeVisibleApple(){
        isAppleVisible = false;
        newApple();
        eclipseEffectMode();
    }

    public void crash() {
        if (!isWallHack) {
            for (int i = 0; i < snakeSize; i++) {
                if (snakeX[0] == snakeX[i + 1] && snakeY[0] == snakeY[i + 1]) {
                    running = false;
                }
            }
            if (snakeX[0] < 0 || snakeX[0] > SCREEN_WIDTH || snakeY[0] < 0 || snakeY[0] > SCREEN_HEIGHT) {
                running = false;

            }
            if (!running) {
                timer.stop();

            }
        }
    }

    public void wallHackMode() {
        if (isWallHack) {
            for (int i = 0; i < snakeSize; i++) {
                if (snakeX[0] == snakeX[i + 1] && snakeY[0] == snakeY[i + 1]) {
                    running = false;
                }
            }
            if (snakeX[0] < 0) {
                snakeX[0] = SCREEN_WIDTH;
            }
            if (snakeX[0] > SCREEN_WIDTH) {
                snakeX[0] = 0;
            }
            if (snakeY[0] < 0) {
                snakeY[0] = SCREEN_HEIGHT;
            }
            if (snakeY[0] > SCREEN_HEIGHT) {
                snakeY[0] = 0;
            }
            if (!running) {
                timer.stop();
            }

        }
    }

    public void eclipseEffectMode() {

        if (isEclipseEffect) {
            for (int i = 0; i < snakeSize; i++) {
                if (((appleX + 100) > snakeX[0]) && ((appleX - 100) < snakeX[0]) && ((appleY + 100) > snakeY[0]) && ((appleY - 100) < snakeY[0])) {
                    isAppleVisible = true;
                }
            }
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
                    snakeX[0] -= UNIT;
                    break;
                case 'R':
                    snakeX[0] += UNIT;
                    break;
                case 'U':
                    snakeY[0] -= UNIT;
                    break;
                case 'D':
                    snakeY[0] += UNIT;
                    break;
            }
        }
    }

    public void gameOver() {
        rootPanel.switchPanel(rootPanel.getGameOver());
        new Sounds().playSound("src/sounds/crash.wav");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            final BufferedImage imgSnakeBody = ImageIO.read(new File("src/images/SnakeBody.jpeg"));
            final BufferedImage imgSnakeHeadR = ImageIO.read(new File("src/images/SnakeHeadR.jpeg"));
            final BufferedImage imgSnakeHeadL = ImageIO.read(new File("src/images/SnakeHeadL.jpeg"));
            final BufferedImage imgSnakeHeadU = ImageIO.read(new File("src/images/SnakeHeadU.jpeg"));
            final BufferedImage imgSnakeHeadD = ImageIO.read(new File("src/images/SnakeHeadD.jpeg"));
            final BufferedImage imgApple = ImageIO.read(new File("src/images/Apple.png"));
            final BufferedImage imgAppleBad = ImageIO.read(new File("src/images/Applebad.png"));


            if (running) {

                if (isAppleVisible)
                    g.drawImage(imgApple, appleX, appleY, this);
                if (isBadAppleVisible && isEclipseEffect)
                    g.drawImage(imgAppleBad, badAppleX, badAppleY, this);

                for (int i = 0; i < snakeSize; i++) {
                    if (i == 0) {
                        if (direction == 'R')
                            g.drawImage(imgSnakeHeadR, snakeX[i], snakeY[i], this);
                        else if (direction == 'L')
                            g.drawImage(imgSnakeHeadL, snakeX[i], snakeY[i], this);
                        else if (direction == 'U')
                            g.drawImage(imgSnakeHeadU, snakeX[i], snakeY[i], this);
                        else if (direction == 'D')
                            g.drawImage(imgSnakeHeadD, snakeX[i], snakeY[i], this);
                        else
                            System.out.println("ERROR");
                    } else {

                        g.drawImage(imgSnakeBody, snakeX[i], snakeY[i], this);
                    }
                }
                g.setColor(RED);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                FontMetrics fontMetrics = getFontMetrics(g.getFont());
                g.drawString("Score: " + score, SCREEN_WIDTH - fontMetrics.stringWidth("Score: " + score), 30);

                g.setColor(RED);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                FontMetrics fontMetrics1 = getFontMetrics(g.getFont());
                g.drawString("Apple: " + appleEaten, (SCREEN_WIDTH - fontMetrics1.stringWidth("Apple: " + appleEaten)) / 30, 30);

                g.setColor(RED);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                FontMetrics fontMetrics2 = getFontMetrics(g.getFont());
                g.drawString("Time: " + timeGame, (SCREEN_WIDTH - fontMetrics2.stringWidth("Time: " + timeGame)) / 5, 30);

                g.setColor(RED);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                FontMetrics fontMetrics3 = getFontMetrics(g.getFont());
                g.drawString("Player: " + login, SCREEN_WIDTH / 2, 30);

            } else {
                gameOver();
                new FindPlayer().updatePlayerScore(login, score);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkBadApple();
            crash();
            wallHackMode();
            eclipseEffectMode();
        }
        repaint();
    }


    public int getScore() {
        return score;
    }

}