package game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.utility.Protocol;
import game.utility.Utility;

public class StartGame {

			
	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame("Fight For CFU");
		f.setContentPane(Utility.getIstance().getMenuBackground());
		f.setSize(Protocol.WIDTH,Protocol.HEIGHT);
		GridLayout g = new GridLayout(3,1);
		JPanel p = new JPanel(g);
		JButton newGame = new JButton("NEW GAME");
		JButton option = new JButton("OPTION");
		JButton exit = new JButton("EXIT");
		p.add(newGame);
		p.add(option);
		p.add(exit);
		p.setSize(300,150);
		p.setOpaque(false);
		f.add(p);
		p.setBackground(null);
		p.setLocation(500, 440);
		
		newGame.setPreferredSize(new Dimension(100,50));
		option.setPreferredSize(new Dimension(100,50));
		exit.setPreferredSize(new Dimension(100,50));
		
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					f.setVisible(false);
					Main.main(args);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		option.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame oF = new JFrame("Fight For CFU");
				oF.setContentPane(Utility.getIstance().getMenuBackground());
				oF.setSize(Protocol.WIDTH,Protocol.HEIGHT);
				GridLayout oG = new GridLayout(1,1);
				JPanel oP = new JPanel(oG);
				JButton back = new JButton("BACK");
				oP.add(back);
				oP.setSize(300,50);
				oP.setOpaque(false);
				oF.add(oP);
				oP.setBackground(null);
				oP.setLocation(500, 440);
				back.setPreferredSize(new Dimension(100,50));
				
				back.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						oF.dispose();
					}
				});
				oF.setResizable(false);
				oF.setVisible(true);
				oF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
