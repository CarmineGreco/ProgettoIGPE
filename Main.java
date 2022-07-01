package game;

import java.io.IOException;

import javax.swing.JFrame;

import game.controller.Controller;
import game.utility.Protocol;
import game.view.Graphic;

public class Main {

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame("Fight for CFU");
		f.setSize(Protocol.WIDTH,Protocol.HEIGHT);
		Graphic g = new Graphic();
		Controller controller = new Controller(g);
		g.addKeyListener(controller);
		g.setFocusable(true);
		f.add(g);
		GameLoop gl = new GameLoop(controller);
		gl.start();
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
