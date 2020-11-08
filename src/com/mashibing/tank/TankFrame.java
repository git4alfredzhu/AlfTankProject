package com.mashibing.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankFrame extends Frame {
	
	Tank myTank = new Tank(200, 400, Direction.DOWN, Group.GOOD, this);
	public static final int GAME_WIDTH = 1600, GAME_HEIGHT = 1000;
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	List<Explode> explodes = new ArrayList<>();
	
 	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		setBackground(Color.BLACK);
		
		addKeyListener(new MyKeyListener());
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("paint called");
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("no. of bullets is: "+ bullets.size(), 30, 50);
		g.drawString("no. of tanks is: "+ tanks.size(), 30, 70);
		g.drawString("no. of explodes is: "+ explodes.size(), 30, 90);
		g.setColor(c);
		myTank.paint(g);
		
//		Iterator<Bullet> it = bullets.iterator();
//		while(it.hasNext()) {
//			Bullet b = it.next();
//			b.paint(g);
//			if(!b.alive) it.remove();
//		}
		
		for(int i=0; i<bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
		for(int i=0; i<tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		
		for(int i=0; i<explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		
		for(int i=0; i<bullets.size(); i++) {
			for(int j=0; j<tanks.size(); j++) {
				bullets.get(i).collidedWith(tanks.get(j));
			}
		}
	}
	 
	class MyKeyListener extends KeyAdapter {
		boolean bUP = false;
		boolean bRIGHT = false;
		boolean bDOWN = false;
		boolean bLEFT = false;

		@Override
		public void keyPressed(KeyEvent ke) {
			System.out.println("key pressed");
			
			int key = ke.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				bUP = true;
				break;
			case KeyEvent.VK_RIGHT:
				bRIGHT = true;
				break;
			case KeyEvent.VK_DOWN:
				bDOWN = true;
				break;
			case KeyEvent.VK_LEFT:
				bLEFT = true;
				break;
				
			default:
				break;
			}
			
			setMainTankDirec();
		}
		
		@Override
		public void keyReleased(KeyEvent ke) {
			System.out.println("key released");
			int key = ke.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				bUP = false;
				break;
			case KeyEvent.VK_RIGHT:
				bRIGHT = false;
				break;
			case KeyEvent.VK_DOWN:
				bDOWN = false;
				break;
			case KeyEvent.VK_LEFT:
				bLEFT = false;
				break;
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				
			default:
				break;
			}
			
			setMainTankDirec();
		}
		
		public void setMainTankDirec() {
			if(!bUP && !bRIGHT && !bDOWN && !bLEFT) {
				myTank.setMoving(false);
			} else {
				myTank.setMoving(true);
				if (bUP) {myTank.setDirec(Direction.UP);}
				if (bRIGHT) {myTank.setDirec(Direction.RIGHT);}
				if (bDOWN) {myTank.setDirec(Direction.DOWN);}
				if (bLEFT) {myTank.setDirec(Direction.LEFT);}
			}
		}
	}
}
