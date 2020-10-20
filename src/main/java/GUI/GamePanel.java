package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

public class GamePanel extends JPanel implements ActionListener {

    private static final int SCREEN_HEIGHT = 900;
    private static final int SCREEN_WIDTH = 900;
    private static final int UNIT = 30;
    private static final int GAME_UNIT = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT;
    private int[] snakeX = new int[GAME_UNIT];
    private int[] snakeY = new int[GAME_UNIT];
    private int appleX;
    private int appleY;
    private boolean play;
    private Timer timer;
    private Random random;
    char direction = 'R';
    private static final int DELAY = 80;


    public GamePanel() {
        setLayout(null);
        setBackground(BLACK);
        //this.addKeyListener(new MyKey());
        startGame();

    }

    public void startGame() {
        play = true;
        random = new Random();
        newApple();
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT)) * UNIT;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT)) * UNIT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < SCREEN_WIDTH / UNIT; i++) {
            g.drawLine(UNIT * i, 0, UNIT * i, SCREEN_HEIGHT);
        }
        for (int i = 0; i < SCREEN_HEIGHT / UNIT; i++) {
            g.drawLine(0, UNIT * i, SCREEN_WIDTH, UNIT * i);
        }
        g.setColor(RED);
        g.fillOval(appleX, appleY, UNIT, UNIT);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_HEIGHT, SCREEN_WIDTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
