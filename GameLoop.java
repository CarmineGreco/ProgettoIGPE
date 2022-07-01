package game;

import game.controller.Controller;

public class GameLoop extends Thread {

	public Controller controller;
	public GameLoop(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		super.run();
		int count = 0;
		while(true) {
			try {
				controller.update();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
