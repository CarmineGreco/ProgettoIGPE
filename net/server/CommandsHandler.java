package game.net.server;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import game.model.Game;
import game.utility.Protocol;
import game.utility.Utility;

public class CommandsHandler implements Runnable {

	private BufferedReader in1;
	private BufferedReader in2;
	private PrintWriter out1;
	private PrintWriter out2;

	
	public CommandsHandler(Socket player1, Socket player2) {
		try {
			out1 = new PrintWriter(new BufferedOutputStream(player1.getOutputStream()), true);
			in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
			out2 = new PrintWriter(new BufferedOutputStream(player2.getOutputStream()), true);
			in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				read();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void read() throws IOException {
		readMessage(in1);
		readMessage(in2);
	}
	
	private void writeMessage(Player player1, Player player2) throws IOException {
		if(out1 != null) {
			out1.println(Utility.toString(player1));
			out1.println(Utility.toString(player2));
		}
		if(out2 != null){
			out2.println(Utility.toString(player1));
			out2.println(Utility.toString(player2));
		}
	}

	private void readMessage(BufferedReader in) throws IOException {
		if(in != null && in.ready()) {
			if(in == in1) {
				String line = (String) in.readLine();
				switch(line) {
				case Protocol.WALK:
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
					Game.getInstance().moveP1(Protocol.WALK);
					Game.getInstance().getPlayer1().setType(line);
					break;
				case Protocol.BACK:
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
					Game.getInstance().moveP1(Protocol.WALK);
					break;
				case Protocol.HIT:
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
					Game.getInstance().getPlayer1().setType(Protocol.HIT);
					Game.getInstance().setValueBar(true);
					break;
				case Protocol.SPECIAL:
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
					Game.getInstance().getPlayer1().setType(Protocol.SPECIAL);
					Game.getInstance().setValueBar(true);
					break;
				case Protocol.STOP:
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					break;
				default:
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
					break;
				}			
			}
			else if(in == in2){
				String line = (String) in.readLine();

				switch(line) {
				case Protocol.WALK:
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					Game.getInstance().moveP2(Protocol.WALK);
					Game.getInstance().getPlayer2().setType(line);
					break;
				case Protocol.BACK:
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					Game.getInstance().moveP2(Protocol.BACK);
					break;
				case Protocol.HIT:
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					Game.getInstance().getPlayer2().setType(Protocol.HIT);
					Game.getInstance().setValueBar(false);
					break;
				case Protocol.SPECIAL:
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					Game.getInstance().getPlayer2().setType(Protocol.SPECIAL);
					Game.getInstance().setValueBar(false);
					break;
				case Protocol.STOP:
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
				default:
					Game.getInstance().getPlayer1().setType(Protocol.STOP);
					Game.getInstance().getPlayer2().setType(Protocol.STOP);
					break;
				}
			}
			writeMessage(Game.getInstance().getPlayer1(), Game.getInstance().getPlayer2());
		}
	}
}
