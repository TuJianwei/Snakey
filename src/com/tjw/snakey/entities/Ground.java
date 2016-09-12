package com.tjw.snakey.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import com.tjw.snakey.util.Global;

/**
*@author   tjw
*@date     2016-9-8
*@version  1.0
*/
public class Ground {
	
	private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];
	
	public Ground(){
		for(int x = 0; x < Global.WIDTH; x++){
			rocks[x][0] = 1;
			rocks[x][Global.HEIGHT - 1] = 1;
		}
		for(int y = 1; y < Global.HEIGHT - 1; y++){
			rocks[0][y] = 1;
			rocks[Global.WIDTH - 1][y] = 1;
		}
	}

	public boolean isEatGround(Snake snake){
		for(int x = 0; x < Global.WIDTH; x++){
			for(int y = 0; y < Global.HEIGHT; y++){
				if(rocks[x][y] == 1 && 
						x == snake.getHead().x && y == snake.getHead().y)
					return true;
			}
		}
		return false;
	}
	
	public Point getPoint(){
		Random random = new Random();
		int x = 0;
		int y = 0;
		do{
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
		}while(rocks[x][y] == 1);
		
		return new Point(x, y);
	}
	
	public void drawGround(Graphics g){
		g.setColor(Color.DARK_GRAY);
		for(int p = 0; p < Global.WIDTH; p++){
			for(int q = 0; q < Global.HEIGHT; q++){
				if(rocks[p][q] == 1){
					g.fill3DRect(p * Global.CELL_SIZE, q * Global.CELL_SIZE,
							Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
}
