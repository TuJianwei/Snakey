package com.tjw.snakey.entities;

import java.awt.Graphics;
import java.awt.Point;

import com.tjw.snakey.util.Global;

/**
*@author   tjw
*@date     2016-9-8
*@version  1.0
*/
public class Food extends Point{
	
	public void newFood(Point p){
		this.setLocation(p);
	}
	
	public boolean isEatFood(Snake snake){
		
		return this.equals(snake.getHead());
	}
	
	public void drawFood(Graphics g){
		g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE,
				Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}
