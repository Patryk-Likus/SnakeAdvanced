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
