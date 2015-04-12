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
	
	//private BufferedImage bkg = loadimage("space.png");
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
		//big.drawImage(bkg, null, 0, 0);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("HP:%08d score:%08d",reporter.gethearth(),reporter.getScore()), 200, 20);
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
