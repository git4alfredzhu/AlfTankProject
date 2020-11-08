package com.mashibing.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {
	public int x, y;
	public static int WIDTH = ResourceMgr.badTankD.getWidth();
	public static int HEIGHT = ResourceMgr.badTankD.getHeight();
	private Direction direc;
	private boolean moving = true;
	private static final int SPEED = 5;
	private TankFrame tf;
	public boolean alive = true;
	public Group group = Group.BAD;
	public Random random = new Random();
	public Rectangle rect = new Rectangle();
	
	public Tank(int x, int y, Direction direc, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.direc = direc;
		this.group = group;
		this.tf = tf;
		
		rect.x = x;
		rect.y = y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
	}
		
	public boolean getMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Direction getDirec() {
		return direc;
	}

	public void setDirec(Direction direc) {
		this.direc = direc;
	}

	public void paint(Graphics g) {
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);
//		g.fillRect(x, y, 50, 50);
//		g.setColor(c);
//		g.drawImage(ResourceMgr.tankL, x, y, null);
		
		if(!alive) {
			tf.tanks.remove(this);
		}
		
		switch (direc) {
		case LEFT:
			g.drawImage(this.group==Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
			break;
		case UP:
			g.drawImage(this.group==Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group==Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group==Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
			break;
		}

		move();
	}
	
	public void move() {
		if(!moving) return;
		
		switch (direc) {
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		case LEFT:
			x -= SPEED;
			break;
		}
		
		if(this.group == Group.BAD && random.nextInt(100) > 80) {
			fire();
			randomDirection();
		}
		
		boundaryCheck();
		
		rect.x = x;
		rect.y = y;
	}

	private void boundaryCheck() {
		if(this.x < 0) {x = 0;}
		if(this.y < 20) {y = 20;}
		if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {x = TankFrame.GAME_WIDTH - Tank.WIDTH;}
		if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;}
	}

	private void randomDirection() {
		this.direc = Direction.values()[random.nextInt(4)];
		
	}

	public void fire() {
		int bX = this.x + ResourceMgr.badTankL.getWidth()/2 - ResourceMgr.bulletL.getWidth()/2;
		int bY = this.y + ResourceMgr.badTankL.getHeight()/2 - ResourceMgr.bulletL.getHeight()/2;
		tf.bullets.add(new Bullet(bX, bY, this.direc, this.group, tf));
	}

	public void die() {
		this.alive = false;
	}
}
