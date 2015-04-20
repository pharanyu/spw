package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 13;
	private int hp = 1000;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}

	public void moveX(int direction){
		x += (step * direction); 
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void moveY(int direction){
		y += (step * direction); 
		if(y < 0)
			y = 0;
		if(y > 600 - height)
			y = 600 - height;
	}
	
	public int gethp(){
		//System.out.println(hp);
		return hp;
	}
	
	public void crash(){
		hp -= 100;
	}
	  
	 

}
