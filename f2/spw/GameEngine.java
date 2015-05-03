package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Lasor> lasors = new ArrayList<Lasor>();
	private SpaceShip v;	
	private SpaceShip v2;
	private Timer timer, timediff;	
	private long score = 0;
	private double difficulty = 0.05;
	private int level = 1;
	
	private int canshoot1 = 20;
	private int canshoot2 = 20;
	
	public GameEngine(GamePanel gp, SpaceShip v, SpaceShip v2) {
		this.gp = gp;
		this.v = v;		
		this.v2 = v2;
		gp.sprites.add(v);
		gp.sprites.add(v2);
		
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
	private void generateLasor(int type){
		Lasor l;
		if(type == 1){
			l = v.attack();
			gp.sprites.add(l);
			lasors.add(l);
		}
		if(type == 2){
			l = v2.attack();
			gp.sprites.add(l);
			lasors.add(l);
		}
		
	}
	private void process(){
		canshoot1++;
		canshoot2++;
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
		Rectangle2D.Double v2r = v2.getRectangle();
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
					gp.updateGameUI(this);
					return;
				}
			}
		}
		for(Enemy e : enemies){
			er = e.getRectangle();
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
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(v2r)){
				v2.crash();
				enemies.remove(e);
				gp.sprites.remove(e);
				if(v2.gethp() == 0){
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
		case KeyEvent.VK_NUMPAD4:
			v.moveX(-1);
			break;
		case KeyEvent.VK_NUMPAD6:
			v.moveX(1);
			break;
		case KeyEvent.VK_NUMPAD5:
			v.moveY(1);
			break;
		case KeyEvent.VK_NUMPAD8:
			v.moveY(-1);
			break;
		case KeyEvent.VK_X:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_CONTROL:
			if(canshoot1 > 20){
				generateLasor(1);
				canshoot1 = 0;
			}
			break;
		case KeyEvent.VK_A:
			v2.moveX(-1);
			break;
		case KeyEvent.VK_D:
			v2.moveX(1);
			break;
		case KeyEvent.VK_S:
			v2.moveY(1);
			break;
		case KeyEvent.VK_W:
			v2.moveY(-1);
			break;
		case KeyEvent.VK_SPACE:
			if(canshoot2 > 20){
				generateLasor(2);
				canshoot2 = 0;
			}
			break;	
		}
	}

	public long getScore(){
		return score;
	}
	public int gethearthV1(){
		return v.gethp();
	}
	public int gethearthV2(){
		return v2.gethp();
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
