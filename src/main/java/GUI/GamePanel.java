package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private static final int SCREEN_HEIGHT = 900;
    private static final int SCREEN_WIDTH = 900;
    private  static final int UNIT = 30;
    private static final int GAME_UNIT = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT;
    private int[] snakeX = new int[GAME_UNIT];
    private int[] snakeY = new int[GAME_UNIT];
    private int appleX;
    private int appleY;
    private boolean play;
    private Timer timer;
    private Random random;
    char direction = 'R';




    public GamePanel(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREEN_HEIGHT,SCREEN_WIDTH);
    }
}
