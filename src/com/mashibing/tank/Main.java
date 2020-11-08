package com.mashibing.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame ft = new TankFrame();
		int initTankNo = Integer.parseInt(PropertyMgr.get("initTankNo"));
		
		for(int i=0; i<initTankNo; i++) {
			ft.tanks.add(new Tank(50+i*50, 200, Direction.DOWN, Group.BAD, ft));
		}
		
		while(true) {
			Thread.sleep(50);
			ft.repaint();
		}
	}
}
