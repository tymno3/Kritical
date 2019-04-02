package com.gamge.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.gamge.game.gamestate.GameState;
import com.gamge.game.gamestate.GameStateManager;
import com.gamge.game.gamestate.Level1State;
import com.gamge.game.gamestate.Level2State;
import com.gamge.game.gamestate.MenuState;
import com.gamge.game.main.GamePanel;
import com.group.gane.objects.Block;
import com.group.gane.objects.MovingBlock;
import com.group.gane.physics.Collision;

public class Player extends GameStateManager {
	public static boolean win = false;
	public static int level = 1;
	public static boolean death = false;
	private boolean dash = false;
	private double x, y;
	private int width, height;
	private boolean fastfall = false;
	private boolean right = false, left = false, down = false, up = false, superdash = false;
	private boolean jumping = false;
	private boolean falling = false;
	private double jumpSpeed = 7;
	private double currentJumpSpeed = jumpSpeed;
	public static double currentFallSpeed = .7;
	private double moveSpeed = 6;
	private boolean topCollision = false;

	public int iX, iY;
	long startTime;
	long elapsedTime;
	private boolean reset = false;

	public Player(int width, int height) {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}

	public void tick(Block[][] b, ArrayList<MovingBlock> movingBlocks, Block[][] d) {

		if (!right || !left) {
			x = GamePanel.WIDTH / 2;
			y = GamePanel.HEIGHT / 2;
		}
		iX = (int) x;
		iY = (int) y;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if (b[i][j].getID() != 0 && b[i][j].getID() == 1) {
					if (Collision.playerBlock(
							new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
							|| Collision.playerBlock(
									new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
									b[i][j])) {
						right = false;

					}

					if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
							b[i][j])
							|| Collision.playerBlock(
									new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])) {
						left = false;

					}

					if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
							b[i][j])
							|| Collision.playerBlock(
									new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
									b[i][j])) {
						falling = true;

					}

					if (Collision.playerBlock(
							new Point(iX + (int) GameState.xOffset, iY + height + (int) GameState.yOffset), b[i][j])
							|| Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset,
									iY + (int) GameState.yOffset + height), b[i][j])) {
						falling = false;
						topCollision = true;
						y = b[i][j].getY() - height - GameState.yOffset;

					} else {
						if (!topCollision && !jumping) {
							falling = true;
						}
					}
				}
			}
		}
		// Useless Shit
		for (int i = 0; i < movingBlocks.size(); i++) {
			if (movingBlocks.get(i).getID() != 0) {
				for (int j = 0; j < b[0].length; j++) {
					if (movingBlocks.get(i).getID() != 0) {
						if (Collision
								.playerMovingBlock(new Point(iX + width + 10 + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + 10), movingBlocks.get(i))
								|| Collision.playerMovingBlock(new Point(iX + width + 1 + (int) GameState.xOffset,
										iY + (int) GameState.yOffset), movingBlocks.get(i))) {
							right = false;

						}
						if (Collision.playerMovingBlock(
								new Point(iX - 5 + (int) GameState.xOffset, iY + (int) GameState.yOffset),
								movingBlocks.get(i))
								|| Collision.playerMovingBlock(
										new Point(iX - 5 + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										movingBlocks.get(i))) {
							left = false;

						}
						if (Collision.playerMovingBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
								movingBlocks.get(i))
								|| Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + 15), movingBlocks.get(i))) {
							jumping = false;

						}

						if (Collision.playerMovingBlock(
								new Point(iX + (int) GameState.xOffset, iY + height + (int) GameState.yOffset),
								movingBlocks.get(i))
								|| Collision.playerMovingBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										movingBlocks.get(i))) {
							falling = false;
							topCollision = true;
							y = movingBlocks.get(i).getY() - height - GameState.yOffset;
							GameState.xOffset += movingBlocks.get(i).getMove();

						} else {
							if (!topCollision && !jumping) {
								falling = true;
							}
						}
					}
				}
			}
			// Death Block
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < d.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 2) {
						if (Collision
								.deathBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + 10), b[i][j])
								|| Collision.deathBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							right = false;
							death = true;

						}
						if (Collision.deathBlock(new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
								b[i][j])
								|| Collision.deathBlock(
										new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							left = false;
							death = true;
						}
						if (Collision.deathBlock(new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
								b[i][j])
								|| Collision.deathBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							jumping = false;
							death = true;
						}

						if (Collision.deathBlock(
								new Point(iX + (int) GameState.xOffset, iY + height + (int) GameState.yOffset), b[i][j])
								|| Collision.deathBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							falling = false;
							topCollision = true;
							y = b[i][j].getY() - height - GameState.yOffset;
							death = true;

						} else {
							if (!topCollision && !jumping) {
								falling = true;
							}
						}
					}
				}
			}
			// level 1
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < b.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 4) {
						if (Collision.levelOneBlock(
								new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelOneBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 1;
							System.out.println("levels has changed to level 1");
						}
						if (Collision.levelOneBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelOneBlock(
										new Point(iX + (int) GameState.xOffset - 5, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 1;
							System.out.println("levels has changed to level 1");
						}
						if (Collision.levelOneBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelOneBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 1;
							System.out.println("levels has changed to level 1");
						}
						if (Collision
								.levelOneBlock(new Point(iX + (int) GameState.xOffset,
										iY + 2 + height + (int) GameState.yOffset), b[i][j])
								|| Collision.levelOneBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							Level1State.levels = 1;
							System.out.println("levels has changed to level 1");
						}
					}
				}

			}
			// level 2
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < b.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 5) {
						if (Collision.levelTwoBlock(
								new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelTwoBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 2;
							System.out.println("levels has changed to level 2");
						}
						if (Collision.levelTwoBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelTwoBlock(
										new Point(iX + (int) GameState.xOffset - 5, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 2;
							System.out.println("levels has changed to level 2");
						}
						if (Collision.levelTwoBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelTwoBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 2;
							System.out.println("levels has changed to level 2");
						}
						if (Collision
								.levelTwoBlock(new Point(iX + (int) GameState.xOffset,
										iY + 2 + height + (int) GameState.yOffset), b[i][j])
								|| Collision.levelTwoBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							Level1State.levels = 2;
							System.out.println("levels has changed to level 2");
						}
					}
				}

			}
			// level 3
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < b.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 6) {
						if (Collision.levelThreeBlock(
								new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelThreeBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 3;
							System.out.println("levels has changed to level 3");
						}
						if (Collision.levelThreeBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelThreeBlock(
										new Point(iX + (int) GameState.xOffset - 5, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 3;
							System.out.println("levels has changed to level 3");
						}
						if (Collision.levelThreeBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelThreeBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 3;
							System.out.println("levels has changed to level 3");
						}
						if (Collision
								.levelThreeBlock(new Point(iX + (int) GameState.xOffset,
										iY + 2 + height + (int) GameState.yOffset), b[i][j])
								|| Collision.levelThreeBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							Level1State.levels = 3;
							System.out.println("levels has changed to level 3");
						}
					}
				}

			}
			// Level 4
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < b.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 7) {
						if (Collision.levelFourBlock(
								new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFourBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 4;
							System.out.println("levels has changed to level 4");
						}
						if (Collision.levelFourBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFourBlock(
										new Point(iX + (int) GameState.xOffset - 5, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 4;
							System.out.println("levels has changed to level 4");
						}
						if (Collision.levelFourBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFourBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 4;
							System.out.println("levels has changed to level 4");
						}
						if (Collision
								.levelFourBlock(new Point(iX + (int) GameState.xOffset,
										iY + 2 + height + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFourBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							Level1State.levels = 4;
							System.out.println("levels has changed to level 4");
						}
					}
				}

			}
			// Level 5
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < b.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 8) {
						if (Collision.levelFiveBlock(
								new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFiveBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 5;
							System.out.println("levels has changed to level 5");
						}
						if (Collision.levelFiveBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFiveBlock(
										new Point(iX + (int) GameState.xOffset - 5, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 5;
							System.out.println("levels has changed to level 5");
						}
						if (Collision.levelFiveBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFiveBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 5;
							System.out.println("levels has changed to level 5");
						}
						if (Collision
								.levelFiveBlock(new Point(iX + (int) GameState.xOffset,
										iY + 2 + height + (int) GameState.yOffset), b[i][j])
								|| Collision.levelFiveBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							Level1State.levels = 5;
							System.out.println("levels has changed to level 5");
						}
					}
				}

			}
			// Level 6
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < b.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 9) {
						if (Collision.levelSixBlock(
								new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelSixBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 6;
							System.out.println("levels has changed to level 6");
						}
						if (Collision.levelSixBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelSixBlock(
										new Point(iX + (int) GameState.xOffset - 5, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 6;
							System.out.println("levels has changed to level 6");
						}
						if (Collision.levelSixBlock(
								new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.levelSixBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							Level1State.levels = 6;
							System.out.println("levels has changed to level 6");
						}
						if (Collision
								.levelSixBlock(new Point(iX + (int) GameState.xOffset,
										iY + 2 + height + (int) GameState.yOffset), b[i][j])
								|| Collision.levelSixBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							Level1State.levels = 6;
							System.out.println("levels has changed to level 6");
						}
					}
				}

			}
			// win block
			iX = (int) x;
			iY = (int) y;
			for (i = 0; i < d.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (b[i][j].getID() != 0 && b[i][j].getID() == 3) {
						if (Collision.winBlock(
								new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i][j])
								|| Collision.winBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {
							win = true;
						}
						if (Collision.winBlock(new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
								b[i][j])
								|| Collision.winBlock(
										new Point(iX + (int) GameState.xOffset - 5, iY + (int) GameState.yOffset),
										b[i][j])) {

							win = true;

						}
						if (Collision.winBlock(new Point(iX + (int) GameState.xOffset, iY + (int) GameState.yOffset),
								b[i][j])
								|| Collision.winBlock(
										new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset),
										b[i][j])) {

							win = true;

						}

						if (Collision
								.winBlock(new Point(iX + (int) GameState.xOffset,
										iY + 2 + height + (int) GameState.yOffset), b[i][j])
								|| Collision.winBlock(new Point(iX + width + (int) GameState.xOffset,
										iY + (int) GameState.yOffset + height), b[i][j])) {
							win = true;

						} else {
							if (!topCollision && !jumping) {
								falling = true;
							}
						}
					}
				}
				iX = GamePanel.WIDTH / 2;
				iY = GamePanel.HEIGHT / 2;
			}
			topCollision = false;
			if (right) {
				GameState.xOffset += moveSpeed;
			}
			if (left) {
				GameState.xOffset -= moveSpeed;
			}
			if (dash) {
				if (right) {
					GameState.xOffset += 5;
				}
				if (left && dash) {
					GameState.xOffset -= 5;
				}
			}

			if (jumping) {
				GameState.yOffset -= currentJumpSpeed;
				currentJumpSpeed -= .2;
				if (currentJumpSpeed <= 0) {
					currentJumpSpeed = jumpSpeed;
					jumping = false;
					falling = true;
				}
			}

			if (falling) {
				GameState.yOffset += currentFallSpeed;
				currentFallSpeed += .7;
			}
			if (Level1State.levels != 8) {
				if (currentFallSpeed >= 100)
					death = true;
			}
		}
		if (reset) {
			death = true;
		}
		if (!falling) {
			currentFallSpeed = .1;

		}

	}

	public boolean getDeath() {
		return death;
	}

	public boolean getWin() {
		return win;
	}

	public int getLevel() {
		return Level1State.levels;
	}

	public boolean getReset() {
		return reset;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) x, (int) y, width, height);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_RIGHT)
			right = true;
		if (k == KeyEvent.VK_LEFT)
			left = true;
		if (k == KeyEvent.VK_DOWN)
			down = true;
		if (k == KeyEvent.VK_UP || k == KeyEvent.VK_SPACE && !jumping && !falling)
			jumping = true;
		if (k == KeyEvent.VK_R)
			reset = true;
		if (k == KeyEvent.VK_F)
			dash = true;
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_RIGHT)
			right = false;
		if (k == KeyEvent.VK_LEFT)
			left = false;
		if (k == KeyEvent.VK_F)
			dash = false;
		if (k == KeyEvent.VK_DOWN)
			down = false;

	}

	public void restart() {

	}

}
