package com.group.gane.physics;

import java.awt.Point;

import com.group.gane.objects.Block;
import com.group.gane.objects.MovingBlock;

public class Collision {
	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
	public static boolean spikeBlock(Point p, Block b){
		return b.contains(p);	
	}
	public static boolean winBlock(Point p, Block w){
		return w.contains(p);
	}
	public static boolean levelOneBlock(Point p, Block w){
		return w.contains(p);
	}
	public static boolean levelTwoBlock(Point p, Block w){
		return w.contains(p);
	}
	public static boolean levelThreeBlock(Point p, Block w){
		return w.contains(p);
	}
	public static boolean levelFourBlock(Point p, Block w){
		return w.contains(p);
	}
	public static boolean levelFiveBlock(Point p, Block w){
		return w.contains(p);
	}
	public static boolean levelSixBlock(Point p, Block w){
		return w.contains(p);
	}
	public static boolean playerMovingBlock(Point p, MovingBlock b) {
		return b.contains(p);
	}
	public static boolean deathBlock(Point p, Block d) {
		return d.contains(p);
	}
}
