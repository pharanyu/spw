package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class Lasor extends Sprite{
	private int step = 12;
	private boolean alive = true;
	
	
	public Lasor(int x, int y) {
		super(x, y, 3, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		
	}
	
	public void proceed(){
		//System.out.println(step);
		y -= step;
		//step += 0.5;
		if(y == 0){
			alive = false;
		}
		
	}
	
	public boolean isAlive(){
		return alive;
	}
}
