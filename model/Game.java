package game.model;

import javax.swing.JOptionPane;

import game.net.server.Player;
import game.utility.Protocol;

public class Game {
	
	private Player player1 = new Player(-30,421);
	
	private Player player2 = new Player(1000,421);
	
	private static Game instance = null;
	
	private Game() {	
		
	}
	
	public static Game getInstance() {
		if(instance == null)
			instance = new Game();
		return instance;
	}
	
	public void moveP1(String direction) {
		if(direction.equals(Protocol.WALK)) {
			player1.setType(direction);
			int pos = player1.getX();
			pos+=player1.getSpeed();
			if(pos >= 1050)
				pos = 1050;
			player1.setX(pos);
		}
		else if(direction.equals(Protocol.BACK)) {
			player1.setType(direction);
			int pos = player1.getX();
			pos-=player1.getSpeed();
			if(pos <= -30) {
				pos = -30;
			}
			player1.setX(pos);
		}
		return;
	}
	
	public void moveP2(String direction) {
		if(direction.equals(Protocol.WALK)) {
			player2.setType(direction);
			int pos = player2.getX();
			pos+=player2.getSpeed();
			if(pos >= 1000 )
				pos = 1000;
			player2.setX(pos);
		}
		else if(direction.equals(Protocol.BACK)) {
			player2.setType(direction);
			int pos = player2.getX();
			pos-=player2.getSpeed();
			if(pos <= -30)
				pos = -30;
			player2.setX(pos);
		}
		return;
	}
	
	public int getCurrentLifeP1() {
		return player1.getLife();
	}
	
	public int getCurrentLifeP2() {
		return player2.getLife();
	}
	
	private boolean attackCollision(String type) {
		if((player2.getX() - player1.getX() <= 90 && type == Protocol.HIT) || (player2.getX() - player1.getX() <= 90 && type == Protocol.SPECIAL)) {
			return true;
		}
		return false;
	}
	
	public void setValueBar(boolean p) {

		if(p) {
			if(attackCollision(player1.getType())) {
				if(player1.getType() == Protocol.HIT) {
					int remainLife = getCurrentLifeP2();
					remainLife-=5;
					player2.setLife(remainLife);
					if(player2.getLife() <= 0) {
						player2.setLife(0);
						player2.setType(Protocol.FALL);
						JOptionPane.showMessageDialog(null, "Player 1 wins");
						System.exit(0);
					}
					else
						player2.setType(Protocol.HURT);
				}
				else {
					int remainLife = getCurrentLifeP1();
					remainLife-=15;
					player2.setLife(remainLife);
					if(player2.getLife() <= 0) {
						player2.setLife(0);
						player2.setType(Protocol.FALL);
						JOptionPane.showMessageDialog(null, "Player 1 wins");
						System.exit(0);
					}
					else
						player2.setType(Protocol.HURT);
				}
			}
		}
		else {
			if(attackCollision(player2.getType())) {
				if(player2.getType() == Protocol.HIT) {
					int remainLife = player1.getLife();
					remainLife-=5;
					player1.setLife(remainLife);
					if(player1.getLife() <= 0) {
						player1.setLife(0);
						player1.setType(Protocol.FALL);
						JOptionPane.showMessageDialog(null, "Player 2 wins");
						System.exit(0);
						
					}
					else
						player1.setType(Protocol.HURT);
				}
				else {
					int remainLife = player1.getLife();
					remainLife-=15;
					player1.setLife(remainLife);
					if(player1.getLife() <= 0) {
						if(player1.getLife() <= 0) {
							player2.setLife(0);
							player1.setType(Protocol.FALL);
							JOptionPane.showMessageDialog(null, "Player 2 wins");
							System.exit(0);
						}
						else
							player1.setType(Protocol.HURT);
					}
				}
			}
		}
	}
	
	public void setPlayer1(Player player) {
		this.player1 = player;
	}
	
	public void setPlayer2(Player player) {
		this.player2 = player;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
}