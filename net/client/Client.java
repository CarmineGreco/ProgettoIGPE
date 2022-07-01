package game.net.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import game.net.server.Player;
import game.utility.Utility;

public class Client {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	private Player player1;
	private Player player2;
	
	public Client() {
		try {
			socket = new Socket("localhost", 8010);
			out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException e) {
			socket = null;
			in = null;
			out = null;
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		if(out == null)
			return;
		out.println(message);
	}
	
	/*
	 * Utilizzando ObjectOutputStream per inviare direttamente i player dall'Handler, si sono verificati errori di lettura
	 * scelto successivamente di inviare direttamente String e di creare nuovi player
	 */
	public ArrayList<Player> readMessage() {
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			if (in != null && in.ready()) {
				String line1 = in.readLine();
				player1 = Utility.fromString(line1);
				String line2 = in.readLine();
				player2 = Utility.fromString(line2);
				players.add(player1);
				players.add(player2);
			}
			return players;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
}
