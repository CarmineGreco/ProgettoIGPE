package game.view;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import game.model.Game;
import game.net.server.CommandsHandler;
import game.net.server.Player;
import game.utility.Utility;

public class Graphic extends JPanel {

	private static final long serialVersionUID = -6636325236697785997L;
	private AllAnimations animation = new AllAnimations();
	private Image bg = null;
	private JProgressBar lifeBarP2;
	private JProgressBar lifeBarP1;
	
	private Player player1;
	private Player player2;

	public Graphic() throws IOException {
		player1 = Game.getInstance().getPlayer1();
		player2 = Game.getInstance().getPlayer2();
		lifeBarP1 = new JProgressBar(0,100);
		lifeBarP1.setValue(100);
		lifeBarP1.setPreferredSize(new Dimension(500,50));
		lifeBarP2 = new JProgressBar(0,100);
		lifeBarP2.setValue(100);
		lifeBarP2.setPreferredSize(new Dimension(500,50));
		this.add(lifeBarP1);
		this.add(lifeBarP2);
		bg = ImageIO.read(getClass().getResourceAsStream("/game/resources/background/game_bg.jpg"));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, null);
		g.drawImage(animation.getCurrentImageP1(), player1.getX(), player1.getY(), 300, 300, null);
		g.drawImage(animation.getCurrentImageP2(), player2.getX(), player2.getY(), 300, 300, null);
	}
	
	public void updateAnimationP1() {
		animation.changeAnimationP1(player1.getType());
	}
	
	public void updateAnimationP2() {
		animation.changeAnimationP2(player1.getType());
	}
	
	public void update() {
		updateAnimationP1();
		updateAnimationP2();
		animation.updateP1();
		animation.updateP2();
		updateStatusBar();
		repaint();
	}

	private void updateStatusBar() {
		lifeBarP1.setValue(player1.getLife());
		lifeBarP2.setValue(player2.getLife());	
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.player1 = players.get(0);
		this.player2 = players.get(1);
	}
}
