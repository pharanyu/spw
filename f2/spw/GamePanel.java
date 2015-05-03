package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	BufferedImage bg;
	//private BufferedImage bkg = loadimage("space.png");
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(600, 900, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
		//big.drawImage(bkg, null, 0, 0);
		try{
			bg = ImageIO.read(new File("f2/spw/space.png"));
		}
		catch(IOException e){
			
		}
	}

	public void updateGameUI(GameReporter reporter){
		int c=0;
		big.clearRect(0, 0, 600, 900);
		big.drawImage(bg, 0, 0, 600, 900, null);
		big.setColor(Color.WHITE);		
		big.drawString(String.format("HP"), 360, 20);
		big.drawString(String.format("LEVEL : %d",reporter.getlevel()), 20, 20);
		for(int i = 100; i <= reporter.gethearthV1(); i +=100){
			big.fillRect(380 + c, 7, 10, 17);
			c += 10; 
		}
		big.drawString(String.format("score:%08d",reporter.getScore()), 500, 20);
		
		big.drawString(String.format("HP2"), 350, 45);
		c = 0;
		for(int i = 100; i <= reporter.gethearthV2(); i +=100){
			big.fillRect(380 + c, 30, 10, 17);
			c += 10; 
		}
		/*big.drawString(String.format("score:%08d",reporter.getScore()), 500, 30);*/
		for(Sprite s : sprites){
			s.draw(big);
		}
	
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
	
	/*BufferedImage loadimage(String filename){
		BufferedImage img = null;
		try{
			img = ImageIO.read(new File(filename));
		}catch(IOException e){
			
		}
		return img;
	}*/
}
