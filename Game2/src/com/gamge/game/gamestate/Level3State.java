package com.gamge.game.gamestate;

import java.awt.Graphics;

import com.games.resources.Images;

import com.gamge.game.entities.Player;
import com.gamge.game.main.GamePanel;
import com.gang.mapping.Map;
import com.group.gane.objects.Block;

public class Level3State extends GameState{
	
	
	public static Player player;
	public static Map map;
	
public Level3State(GameStateManager gsm){
	super(gsm);
}

	
	



public static void inits() {
	player = new Player(30,30);
	map=new Map("/Maps/map3.map");;
	xOffset=-300;
	yOffset=-400;
	ticks();
}
public static void respawn() {
	player = new Player(30,30);
	
	xOffset=-300;
	yOffset=-400;
	ticks();
}
public void init() {
	player = new Player(30,30);
	map=new Map("/Maps/map3.map");;
	xOffset=-300;
	yOffset=-400;
	
}

public static void ticks() {
	
	player.tick(map.getBlocks(),map.getMovingBlocks(), map.getDeathBlocks());
	map.tick();
}
public void tick() {
	
	player.tick(map.getBlocks(),map.getMovingBlocks(), map.getDeathBlocks());
	map.tick();
}

public void draw(Graphics g) {
	g.drawImage(Images.background[0],0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null );
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
	// TODO Auto-generated method stub
	if(yOffset<-800) {
		yOffset=-400;
		xOffset=-300;
	}
	
}

}
