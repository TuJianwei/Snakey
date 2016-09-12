package com.tjw.snakey.controller;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.tjw.snakey.ShowPanel.GamePanel;
import com.tjw.snakey.entities.Food;
import com.tjw.snakey.entities.Ground;
import com.tjw.snakey.entities.Snake;
import com.tjw.snakey.listener.SnakeListener;
import com.tjw.snakey.util.Global;

/**
*@author  tjw
*@date    2016-9-12
*@version 1.0
*/
public class Controller extends KeyAdapter implements SnakeListener{
	
	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;
	
	
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}


	/**
	 * 处理监听事件
	 * UP 向上
	 * DOWN 向下
	 * RIGHT 向右
	 * LEFT 向左
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
			
		}
	}

	
	public void snakeMoved(Snake snake) {
		// TODO Auto-generated method stub
		if(food.isEatFood(snake)){
			snake.eatFood();
			food.newFood(ground.getPoint());
		}
		if(ground.isEatGround(snake)){
			snake.die();
		}
		if(snake.isEatBody()){
			snake.die();
		}
		
		gamePanel.display(snake, food, ground);
	}
	
	
	public void newGame(){
		snake.start();
		food.newFood(ground.getPoint());
	}

}
