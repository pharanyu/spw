package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lasor extends Sprite{
	private int step = 26;
	private boolean alive = true;
	BufferedImage bg;
	
	
	public Lasor(int x, int y) {
		super(x, y, 12, 30);
		
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.YELLOW);
		//g.fillRect(x, y, width, height);
		try{
			bg = ImageIO.read(new File("f2/spw/lasor.gif"));
		}
		catch(IOException e){
			
		}
		g.drawImage(bg, x, y, width, height, null);
		
	}
	
	public void proceed(){
		//System.out.println(y);
		y -= step;
		//step += 0.5;
		if(y <= 0){
			alive = false;
		}
		
	}
	
	public boolean isAlive(){
		return alive;
	}
}
