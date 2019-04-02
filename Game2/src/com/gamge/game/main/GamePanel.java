package com.gamge.game.main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.games.resources.Images;
import com.gamge.game.gamestate.GameStateManager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
public class GamePanel extends JPanel implements Runnable, KeyListener{
private static final long serialVersionUID=1L;
public static final int WIDTH=1200;
public static final int HEIGHT=900;
private Thread thread;
private boolean isRunning=false;
private int FPS=80;
private long targetTime= 1000/FPS;
private GameStateManager gsm;

	public GamePanel() {
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
	
	addKeyListener(this);
	setFocusable(true);
	new Images();
	start();
}
	private void start() {
		isRunning=true;
		thread=new Thread(this);
		thread.start();
	}
	public void run() {
		long start, elapsed, wait;
		gsm = new GameStateManager();
		while(isRunning)
		{
			
			start=System.nanoTime();
			tick();
			repaint();
			elapsed=System.nanoTime()-start;
			wait=targetTime-elapsed/1000000;
			if(wait <0) {
				wait=5;
			}
			try {
				Thread.sleep(wait);
			}catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		
	}
	public void tick() {
		gsm.tick();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		gsm.draw(g);
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		gsm.keyPressed(e.getKeyCode());
		
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		gsm.keyReleased(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
