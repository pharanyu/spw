package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(615, 940);
		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(240, 760, 100, 120, 1);
		SpaceShip v2 = new SpaceShip(140, 760, 100, 120, 2);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v, v2);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();
	}
}
