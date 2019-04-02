package com.group.gane.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.games.resources.Images;

import com.gamge.game.gamestate.GameState;

public class deathBlock extends Rectangle{
	public static final long serialVersionUID=1L;
	public static final int deathBlockSize=44;
	private int id;
	public deathBlock(int x, int y,int id)
	{
		setBounds(x,y,deathBlockSize,deathBlockSize);
	this.id=id;
	}
	public void tick() {
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		if(id!=0) {
		g.drawImage(Images.block[id-1],x-(int) GameState.xOffset,y-(int)GameState.yOffset,width,height,null );
		
	}
	}
	public void setID(int id) {
		
		this.id=id;
	}
	public int getID() {
		return id;
		
	}
}
