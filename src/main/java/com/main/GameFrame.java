package com.main;

import com.gui.RootPanel;

import javax.swing.*;

public class GameFrame extends JFrame {

	RootPanel rootPanel ;

	public GameFrame()  {
		rootPanel = new RootPanel();
		setTitle("SnakeADV");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(rootPanel);
		setVisible(true);
		pack();

	}

}
