package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Thread;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Lasor> lasors = new ArrayList<Lasor>();
	private SpaceShip v;	
	
	private Timer timer, timediff;	
	private long score = 0;
	private double difficulty = 0.1;
	private int level = 1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(35, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
		timediff = new Timer(10000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				difficulty += 0.02;
				level++;
				//System.out.println(difficulty + "   "+level);
			}
		});
		timediff.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
		timediff.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*590), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	private void generateLasor(){
		Lasor l = v.attack();
		gp.sprites.add(l);
		lasors.add(l);
	}
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		Iterator<Lasor> l_iter = lasors.iterator();
		while(l_iter.hasNext()){
			Lasor l = l_iter.next();
			l.proceed();
			if(!l.isAlive()){
				l_iter.remove();
				gp.sprites.remove(l);
			}
		}
		
		//gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double lr;
		for(Enemy e : enemies){
			er = e.getRectangle();
			for(Lasor lasor : lasors){
				lr = lasor.getRectangle();
				if(er.intersects(lr)){
					enemies.remove(e);
					gp.sprites.remove(e);
					lasors.remove(lasor);
					gp.sprites.remove(lasor);
					return;
				}
			}
			if(er.intersects(vr)){
				v.crash();
				enemies.remove(e);
				gp.sprites.remove(e);
				if(v.gethp() == 0){
					die();
				}
				gp.updateGameUI(this);
				return;
			}
		}
		
		gp.updateGameUI(this);
	}
	
	public void die(){
		timer.stop();
		timediff.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.moveX(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.moveX(1);
			break;
		case KeyEvent.VK_DOWN:
			v.moveY(1);
			break;
		case KeyEvent.VK_UP:
			v.moveY(-1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			generateLasor();
			break;
		}
	}

	public long getScore(){
		return score;
	}
	public int gethearth(){
		return v.gethp();
	}
	
	public int getlevel(){
		return level;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
