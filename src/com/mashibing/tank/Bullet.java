package com.mashibing.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private int x, y;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private static final int SPEED = 10;
	private Direction direc = null;
	private TankFrame tf = null;
	public boolean alive = true;
	public Group group = Group.BAD;
	public Rectangle rect = new Rectangle();
	
	public Bullet(int x, int y, Direction direc, Group group, TankFrame tf) {
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
	
	public void paint(Graphics g) {
		if(!alive) {
			tf.bullets.remove(this);
		}
//		Color c = g.getColor();
//		g.setColor(Color.RED);
//		g.fillOval(x, y, 20, 20);
//		g.setColor(c);
		
		switch (direc) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, WIDTH, HEIGHT, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, WIDTH, HEIGHT, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, WIDTH, HEIGHT, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, WIDTH, HEIGHT, null);
			break;
		}
		move();
	}
	
	public void collidedWith(Tank tank) {
		if(this.group == tank.group) return;
		
		int eX = tank.x + tank.WIDTH/2 - Explode.WIDTH/2;
		int eY = tank.y + tank.HEIGHT/2 - Explode.HEIGHT/2;
		if(rect.intersects(tank.rect)) {
			this.die();
			tank.die();
			tf.explodes.add(new Explode(eX, eY, tf));
		}
	}
	
	private void die() {
		this.alive = false;
	}

	public void move() {
		
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
		
		rect.x = x;
		rect.y = y;
		
		if (x<0 | y<0 | x>TankFrame.GAME_WIDTH | y>TankFrame.GAME_HEIGHT) {
			alive = false;
		}
	}
}
