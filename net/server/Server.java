package game.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	
	private ServerSocket server;
	
	
	public void startServer() throws IOException {
		
		server = new ServerSocket(8010);
		Socket player1 = server.accept();
		Socket player2 = server.accept();
		CommandsHandler command = new CommandsHandler(player1, player2);
		Thread t = new Thread(command);
		t.start();	
	}
	
	public static void main(String[] args) {
		Server s = new Server();
		try {
			s.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
