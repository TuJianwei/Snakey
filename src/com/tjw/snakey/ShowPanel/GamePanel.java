package com.tjw.snakey.ShowPanel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.tjw.snakey.entities.Food;
import com.tjw.snakey.entities.Ground;
import com.tjw.snakey.entities.Snake;
import com.tjw.snakey.util.Global;

/**
*@author   tjw
*@date     2016-9-8
*@version  1.0
*/
public class GamePanel extends JPanel{
	
	private Snake snake;
	private Food food;
	private Ground ground;

	public void display(Snake snake, Food food, Ground ground){
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//÷ÿ–¬œ‘ æ
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE, 
				Global.HEIGHT * Global.CELL_SIZE);
		if(ground != null && snake != null && food != null){
		this.ground.drawGround(g);
		this.snake.drawSnake(g);
		this.food.drawFood(g);
		}
	}
	
	
}
