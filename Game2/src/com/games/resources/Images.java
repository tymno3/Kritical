package com.games.resources;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Images {
	public static BufferedImage[] block, title, background;
public Images() {
	block=new BufferedImage[100];
	title=new BufferedImage[5];
	background=new BufferedImage[3];
	
	try {
	block[0]=ImageIO.read(getClass().getResource("/Blocks/square_preview.png"));
	block[1]=ImageIO.read(getClass().getResource("/Blocks/deathBlock.jpg"));
	block[2]=ImageIO.read(getClass().getResource("/Blocks/WinBlock.jpg"));
	block[3]=ImageIO.read(getClass().getResource("/Blocks/Orange.png"));
	block[4]=ImageIO.read(getClass().getResource("/Blocks/Orange.png"));
	block[5]=ImageIO.read(getClass().getResource("/Blocks/Orange.png"));
	block[6]=ImageIO.read(getClass().getResource("/Blocks/Orange.png"));
	block[7]=ImageIO.read(getClass().getResource("/Blocks/Orange.png"));
	block[8]=ImageIO.read(getClass().getResource("/Blocks/Orange.png"));
	block[9]=ImageIO.read(getClass().getResource("/Blocks/Orange.png"));
	title[0]=ImageIO.read(getClass().getResource("/Blocks/TitleScreen copy.png"));
	title[1]=ImageIO.read(getClass().getResource("/Blocks/ChooseLeft_preview.png"));
	title[2]=ImageIO.read(getClass().getResource("/Blocks/GameOver.png"));
	background[0]=ImageIO.read(getClass().getResource("/Blocks/BGPixelComplete.png"));
	background[1]=ImageIO.read(getClass().getResource("/Blocks/wall.png"));
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	
}
}
