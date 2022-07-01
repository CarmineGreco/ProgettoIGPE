package game.utility;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.net.server.Player;


public class Utility {
	
	private static Utility instance = null;
	
	private Utility() {
	}
	
	public static Utility getIstance() {
		if(instance == null)
			instance = new Utility();
		return instance;
	}
	
	public JLabel getMenuBackground() {
		JLabel l = null;
		try {
			l = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/game/resources/background/menu_bg.jpeg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public static String toString(Player player) {
		StringBuilder sb = new StringBuilder();
		sb.append(player.getX())
		.append("|")
		.append(player.getY())
		.append("|")
		.append(player.getLife())
		.append("|")
		.append(player.getType())
		.append("|")
		.append(player.getSpeed());
		
		return sb.toString();		
	}
	
	public static Player fromString(String s) {
		String[] split = s.split("\\|");
		Player player = new Player(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		player.setLife(Integer.parseInt(split[2]));
		player.setType(split[3]);

		return player;
	}
}
