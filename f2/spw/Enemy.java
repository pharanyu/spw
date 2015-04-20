package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	BufferedImage bg;
	private int step = 12;
	private boolean alive = true;
	
	public Enemy(int x, int y) {
		super(x, y, 20, 50);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE){
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
		/*else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}*/
		//g.setColor(Color.RED);
		//g.fillRect(x, y, width, height);
		try{
			bg = ImageIO.read(new File("f2/spw/enemy.gif"));
		}
		catch(IOException e){
			
		}
		g.drawImage(bg, x, y, width, height, null);
		
		
	}

	public void proceed(){
		//System.out.println(step);
		y += step;
		//step += 0.5;
		if(y > Y_TO_DIE){
			alive = false;
		}
		
	}
	
	public boolean isAlive(){
		return alive;
	}
	public int getstep(){
		return step;
	}
	public void setstep(int step){
		this.step = step;
	}
	public void increasstep(){
		step += 100;
	}
}