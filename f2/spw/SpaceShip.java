package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;



public class SpaceShip extends Sprite{
	BufferedImage bg;
	int step = 23;
	private int hp = 1000;
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, width, height);
		try{
			bg = ImageIO.read(new File("f2/spw/spaceship.gif"));
		}
		catch(IOException e){
			
		}
		g.drawImage(bg, x, y, width, height, null);
		
		
	}

	public void moveX(int direction){
		x += (step * direction); 
		if(x < 0)
			x = 0;
		if(x > 600 - width)
			x = 600 - width;
	}
	public void moveY(int direction){
		y += (step * direction); 
		if(y < 0)
			y = 0;
		if(y > 900 - height)
			y = 900 - height;
	}
	
	public int gethp(){
		//System.out.println(hp);
		return hp;
	}
	
	public void crash(){
		hp -= 100;
	}

	public Lasor attack(){
		Lasor lasor = new Lasor(x+width/2-9, y-30);
		return lasor;
	}
	 

}
