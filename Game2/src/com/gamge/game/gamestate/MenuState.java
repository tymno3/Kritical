package com.gamge.game.gamestate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.games.resources.Images;
import com.gamge.game.main.GamePanel;

public class MenuState extends GameState {
private String[] options= {"Start", "Quit"};
private int currentSelection=0;
private int xCoordinate,yCoordinate;


public void mouseClicked(MouseEvent e) {
     xCoordinate=e.getX();
    yCoordinate=e.getY();
   
}


	public MenuState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	
	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(Images.title[0],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null );
		for(int i=0;i<options.length;i++) {
			if(i==currentSelection||yCoordinate>GamePanel.HEIGHT*i/3+400&&yCoordinate<GamePanel.HEIGHT*i/3+600) {
				g.setColor(Color.RED);
			}
			
			else {
				g.setColor(Color.BLACK);
			}
			g.setFont(new Font("Arial",Font.BOLD,50));
			g.drawString(options[i], GamePanel.WIDTH/2-50, GamePanel.HEIGHT*i/3+500);
		}
		
		
	}
	

	public void keyPressed(int k) {
		if(k==KeyEvent.VK_DOWN) {
			currentSelection++;
			if(currentSelection>=options.length) {
				currentSelection=0;
				
				
			}else if(k==KeyEvent.VK_UP){
				currentSelection--;
				if(currentSelection<0) {
					currentSelection=options.length-1;
				}
			}
		}
		if(k==KeyEvent.VK_ENTER) {
		if(currentSelection==0) {
			gsm.states.push(new Level1State(gsm));
			
		}
		/*else if(currentSelection==1){
			System.out.println("What do you want to be bound to LEFT");
			
			
			}
			*/
		
		
		else {
			System.exit(0);
		}
		}
	}
		public static void loadGame(){
			
			gsm.states.push(new Level1State(gsm));
		}
		
		
	

	
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}


	public void restart() {
		// TODO Auto-generated method stub
		
	}
	
	

}
