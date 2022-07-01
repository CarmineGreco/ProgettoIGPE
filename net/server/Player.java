package game.net.server;

import java.io.Serializable;

import game.utility.Protocol;

public class Player implements Serializable {


	@Override
	public String toString() {
		return "Player [x=" + x + ", y=" + y + ", life=" + life + ", type=" + type + ", speed=" + speed + "]";
	}

	private static final long serialVersionUID = -5367528430138876078L;
	
	private int x;
	private int y;
	private int life = 100;
	private String type = Protocol.STOP;
	
	private int speed = 30;
	
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getLife() {
		return life;
	}
	
	public void setLife(int life) {
		this.life = life;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
