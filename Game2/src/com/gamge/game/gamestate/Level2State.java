package com.gamge.game.gamestate;

import java.awt.Graphics;

import com.games.resources.Images;

import com.gamge.game.entities.Player;
import com.gamge.game.main.GamePanel;
import com.gang.mapping.Map;
import com.group.gane.objects.Block;
import com.group.gane.objects.deathBlock;

public class Level2State extends GameState{
	
	
	private Player player;
	private Map map;
	
public Level2State(GameStateManager gsm){
	super(gsm);
}

	
	



public void init() {
	
	map=new Map("/Maps/map2.map");
	player = new Player(30,30);
	
	xOffset=-300;
	yOffset=-400;
	System.out.println("You have spawned in Lvl 2");
	
}
public void checkForWin() {
	if(player.getWin()) {
		gsm.states.push(new Level3State(gsm));
	}
	System.out.println("You have spawned in Lvl 3");
	player.win=false;
}

public void checkForDeath() {
	if(player.getDeath()) {
		gsm.states.push(new Level2State(gsm));
	}
	System.out.println("You have respawned in Lvl 2");
	player.death=false;
}
public void tick() {
	checkForWin();
	checkForDeath();
	player.tick(map.getBlocks(),map.getMovingBlocks(), map.getDeathBlocks());
	map.tick();
}

public void draw(Graphics g) {
	g.drawImage(Images.background[1],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null );
	player.draw(g);
	map.draw(g);
}
public void keyPressed(int k) {
	player.keyPressed(k);
}

public void keyReleased(int k) {
	player.keyReleased(k);
}

public void restart() {
	
}






public static void respawn() {
	//xOffset=-300;
	//yOffset=0;
	
	
}

}
