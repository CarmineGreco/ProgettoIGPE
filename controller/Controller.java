package game.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.net.client.Client;
import game.net.server.Player;
import game.utility.Protocol;
import game.view.Graphic;

public class Controller extends KeyAdapter {

	private Graphic g;
	private Client client;
	public Controller(Graphic g) {
		this.g = g;
		client = new Client();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			client.sendMessage(Protocol.WALK);
			break;
		case KeyEvent.VK_A:
			client.sendMessage(Protocol.BACK);
			break;
		case KeyEvent.VK_SPACE:
			client.sendMessage(Protocol.HIT);
			break;
		case KeyEvent.VK_S:
			client.sendMessage(Protocol.SPECIAL);
			break;
		default:
			client.sendMessage(Protocol.STOP);
			break;
		}
	}
	
	public void update() {
		ArrayList<Player> players = client.readMessage();
		if(players != null && !players.isEmpty()) { //riceve aggiornamento players solo dopo il primo movimento
			g.setPlayers(players);
			g.update();
		}
	}
}
