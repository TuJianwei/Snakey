package com.tjw.snakey.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.tjw.snakey.listener.SnakeListener;
import com.tjw.snakey.util.Global;

/**
*@author  tjw
*@date    2016-9-8
*@version 1.0
*/
public class Snake {
	
	private int oldDirection, newDirection;
	
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	private Point oldTail;  //折移动去掉的尾巴
	
	private LinkedList<Point> body = new LinkedList<Point>();
	
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();

	private boolean live;
	
	public Snake(){
		init();
	}
	
	public void init(){
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		for(int i = 0; i < 3; i++){
			body.add(new Point(x--, y));
		}
		oldDirection = RIGHT;
		newDirection = RIGHT;
		
		live = true;
	}
	
	public void die(){
		live = false;
	}
	
	public void move(){
		
		oldTail = body.removeLast();               //去尾
		
		if(!(oldDirection + newDirection == 0)){   //如果转动方向与当前移动方向相反，则为无效移动
			oldDirection = newDirection;
		}
		
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch(oldDirection){
		case UP:
			y--;
			if(y < 0){
				y = Global.HEIGHT - 1;
			}
			break;
		case DOWN:
			y++;
			if(y > Global.HEIGHT - 1){
				y = 0;
			}
			break;
		case LEFT:
			x--;
			if(x < 0){
				x = Global.WIDTH - 1;
			}
			break;
		case RIGHT:
			x++;
			if(x > Global.WIDTH - 1){
				x = 0;
			}
			break;
		}
		
		Point newHead = new Point(x, y);
		body.addFirst(newHead);          //加头
	}
	
	public void changeDirection(int direction){    //去除相反方向
		if(!(direction + newDirection == 0)){
		newDirection = direction;
		}
	}
	
	public void eatFood(){
		body.addLast(oldTail);
	}
	
	public boolean isEatBody(){
		for(int i = 1; i < body.size(); i++){
			if(body.get(i).equals(this.getHead())){
				return true;
			}
		}
		return false;
	}
	
	public void drawSnake(Graphics g){
		//填充一个矩形x,y,width,height
		g.setColor(Color.red);
		for(Point p : body){
			g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE, Global.CELL_SIZE, 
					Global.CELL_SIZE, true);
		}
	}
	
	public Point getHead(){
		return body.getFirst();
	}
	
	private class SnakeDriver implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(live){
				move();
				for(SnakeListener l : listeners){
					l.snakeMoved(Snake.this);
				}
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public void start(){
		new Thread(new SnakeDriver()).start();
	}

	public void addSnakeListener(SnakeListener l){
		if(l != null){
			this.listeners.add(l);
		}
	}
}
