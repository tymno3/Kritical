package com.gang.mapping;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.omg.CORBA.portable.InputStream;

import com.group.gane.objects.Block;
import com.group.gane.objects.MovingBlock;

public class Map {
	private String path;
	private String line;
	private int width, height;
	private Block[][] blocks;
	private Block[][] deathBlocks;
	private Block[][] winBlocks;
	private Block[][] levelOneBlock;
	private Block[][] levelTwoBlock;
	private Block[][] levelThreeBlock;
	private Block[][] levelFourBlock;
	private Block[][] levelFiveBlock;
	private Block[][] levelSixBlock;
	private ArrayList<MovingBlock> movingBlocks;
	public Map(String LoadPath) {
		path=LoadPath;
		loadMap();
	}
	public void loadMap() {
		java.io.InputStream is= this.getClass().getResourceAsStream(path);
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		try {
			width=Integer.parseInt(br.readLine());
			height=Integer.parseInt(br.readLine());
			blocks=new Block[height][width];
			deathBlocks=new Block[height][width];
			winBlocks= new Block[height][width];
			levelOneBlock= new Block[height][width];
			levelTwoBlock= new Block[height][width];
			levelThreeBlock= new Block[height][width];
			levelFourBlock= new Block[height][width];
			levelFiveBlock= new Block[height][width];
			levelSixBlock= new Block[height][width];
			for(int y=0;y<height;y++) {
				line=br.readLine();
			String[]tokens=line.split("\\s+");
			for(int x=0;x<width;x++) {
				blocks[y][x]=new Block(x*Block.blockSize,y*Block.blockSize,Integer.parseInt(tokens[x]));
			}
			}
			
			line=br.readLine();
			line=br.readLine();
			int length= Integer.parseInt(line);
			movingBlocks=new ArrayList<MovingBlock>();
			for(int i=0;i<length;i++) {
				line=br.readLine();
				String[] tokens=line.split("\\s+");
			movingBlocks.add(new MovingBlock(Integer.parseInt(tokens[0])*Block.blockSize,Integer.parseInt(tokens[1])*Block.blockSize,Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3])*Block.blockSize,Integer.parseInt(tokens[4])*Block.blockSize));
			}
		} catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}
	}
	public ArrayList<MovingBlock> getMovingBlocks(){
				return movingBlocks;
			}
	public Block[][] getDeathBlocks(){
		return deathBlocks;
	}
	private Block[][] getWinBlocks(){
		return winBlocks;
	}
	private Block[][] getLevelTwoBlock(){
		return levelTwoBlock;
	}
	private Block[][] getLevelThreeBlock(){
		return levelThreeBlock;
	}
	private Block[][] getLevelFourBlock(){
		return levelFourBlock;
	}
	private Block[][] getLevelFiveBlock(){
		return levelFiveBlock;
	}
	private Block[][] getLevelSixBlock(){
		return levelSixBlock;
	}
	public void tick() {
		for(int i=0;i<movingBlocks.size();i++) {
			movingBlocks.get(i).tick();
		}
	}
	public void draw(Graphics g) {
		for(int i=0;i<blocks.length;i++) {
			for(int j=0;j<blocks[0].length;j++) {
				blocks[i][j].draw(g);
			}
		}
		for(int i=0;i<movingBlocks.size();i++){
			movingBlocks.get(i).draw(g);
		}
		
	}
	public Block[][] getBlocks(){
		return blocks;
	}
}
