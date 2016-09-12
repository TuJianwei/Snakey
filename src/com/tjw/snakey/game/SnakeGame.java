package com.tjw.snakey.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.tjw.snakey.ShowPanel.GamePanel;
import com.tjw.snakey.controller.Controller;
import com.tjw.snakey.entities.Food;
import com.tjw.snakey.entities.Ground;
import com.tjw.snakey.entities.Snake;
import com.tjw.snakey.util.Global;

/**
*@author     tjw
*@date       2016-9-9
*@version    1.0
*/
public class SnakeGame {

	public static void main(String[] args) {
		
		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(
				snake, food, ground, gamePanel);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE
				, Global.HEIGHT * Global.CELL_SIZE);
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 16
				, Global.HEIGHT * Global.CELL_SIZE + 38);
		frame.add(gamePanel, BorderLayout.CENTER);
		
		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		
		frame.addKeyListener(controller);
		
		frame.setVisible(true);
		controller.newGame();
	}
}
