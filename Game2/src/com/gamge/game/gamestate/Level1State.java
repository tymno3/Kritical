package com.gamge.game.gamestate;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.games.resources.Images;

import com.gamge.game.entities.Player;
import com.gamge.game.main.GamePanel;
import com.gang.mapping.Map;
import com.group.gane.objects.Block;
import com.group.gane.objects.deathBlock;

public class Level1State extends GameState{
	
	public static int levels=0;
	private Player player;
	private Map map;
	public static int numLoops=0;
public Level1State(GameStateManager gsm){
	super(gsm);
}

public void init() {
	map=new Map("/Maps/mapOverworld.map");;
	if(levels==1) {
	map=new Map("/Maps/map1.map");;
	System.out.println("Level is Level 1");
	}
	if(levels==2) {
	map=new Map("/Maps/map3.map");;
	System.out.println("Map has changed to map2 "+levels);
	}
	if(levels==3) {
		map=new Map("/Maps/map4.map");;
		System.out.println("Map has changed to map3");
		}
	if(levels==4) {
		map=new Map("/Maps/map5.map");;
		System.out.println("Map has changed to map4");
		}
	if(levels==5) {
		map=new Map("/Maps/map2.map");;
		System.out.println("Map has changed to map5");
		}
	if(levels==6) {
		map=new Map("/Maps/map6.map");;
		System.out.println("Map has changed to map5");
		}
	player = new Player(30,30);
	xOffset=-300;
	yOffset=-400;
	}

public void checkForWin() {
	if(player.getWin()) {
		levels=0;
		numLoops=0;
		init();
		gsm.states.push(new Level1State(gsm));
	}
	player.win=false;
}
public void checkForLevels() {
	if(player.getLevel()!=0) {
		numLoops++;
		init();
		
	}
}
public void checkForDeath() {
	if(player.getDeath()) {
		player = new Player(30,30);
		gsm.states.push(new Level1State(gsm));
		player.death=false;
		player.currentFallSpeed=0;
		}
	}
	

public void tick() {
	if(numLoops<2)
	checkForLevels();
	checkForWin();
	checkForDeath();
	player.tick(map.getBlocks(),map.getMovingBlocks(), map.getDeathBlocks());
	map.tick();
}

public void draw(Graphics g) {
	g.drawImage(Images.background[0],0,0,GamePanel.WIDTH*2,
			GamePanel.HEIGHT*2,null );
	player.draw(g);
	map.draw(g);
}
public void keyPressed(int k) {
	player.keyPressed(k);
}

public void keyReleased(int k) {
	player.keyReleased(k);
}


}
