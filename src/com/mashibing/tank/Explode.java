package com.mashibing.tank;

import java.awt.Graphics;

public class Explode {
	private int x, y;
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	private TankFrame tf = null;
	public int step = 0;
	
	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], this.x, this.y, null);
		if(step>15) {
			tf.explodes.remove(this);
		}
	}
}
