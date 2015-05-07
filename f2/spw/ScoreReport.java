package f2.spw;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class ScoreReport {
	private long score;
	JFrame f;
	JPanel p;
	JLabel l1,l2;
	Thread t;
	int i = 0;
	
	public ScoreReport(GameReporter reporter){
		score = reporter.getScore();
		f = new JFrame("score report");
		p = new JPanel();
		l1 = new JLabel("YOUR SCORE : " + score);
		//l2 = new JLabel("" + i);
		
		l1.setForeground(Color.white);
		//l2.setForeground(Color.white);

		p.setBackground(Color.BLACK);
		p.add(l1);
		//p.add(l2);
		//f.getContentPane().add(l2);
		f.setSize(400, 200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		/*for(i = 0; i <= score; i++){
			l2.setText("" + i);
			try{Thread.sleep(1);}catch(Exception e){}
			System.out.println(i);
		}*/
		
		
	}
	
	

}
