package BBRMain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Boosters implements Constants {

	private int boosterX;
	private int boosterY;
	private int boosterYdir;
	private int boosterType;
	private int boosterWidth;
	private int boosterHeight;

	private boolean onScreen;
	private boolean usedBooster;

	private Color boosterColor;

	public Boosters(int boosterX, int boosterY, int boosterType, int boosterWidth, int boosterHeight) {
		this.boosterX = boosterX;
		this.boosterY = boosterY;
		this.boosterType = boosterType;
		this.boosterWidth = boosterWidth;
		this.boosterHeight = boosterHeight;
		this.usedBooster = false;

		if (this.boosterType == 4) {
			this.boosterType = 4;
		}
		if (this.boosterType == 5) {
			this.boosterType = 5;
		}

		if (this.boosterType == WIDER_PADDLE) {
			this.boosterColor = WIDER_PADDLE_COLOR;
		}

		if (this.boosterType == SMALLER_PADDLE) {
			this.boosterColor = SMALLER_PADDLE_COLOR;
		}

		boosterYdir = (int) (Math.random() * 10 + 1);
	}

	public void paint(Graphics2D g) {
		g.setColor(boosterColor);
		g.fillRect(boosterX, boosterY, boosterWidth, boosterHeight);
	}

	public void update() {
		this.boosterY += this.boosterYdir;
		if (boosterY > WINDOW_HEIGHT) {
			this.onScreen = false;
		}
	}

	public Rectangle getBoosterBox() {
		return new Rectangle(boosterX, boosterY, boosterWidth, boosterHeight);
	}
	
	public boolean getUsedBooster() {
		return this.usedBooster;
	}
	
	public void setUsedBooster(boolean used) {
		this.usedBooster = used;
	}

	// get and set for boosterType *******************
	public int getBoosterType() {
		return this.boosterType;
	}

	public void setBoosterType(int boosterType) {
		this.boosterType = boosterType;
	}

	// get and set for onScreen *********************
	public boolean getOnScreen() {
		return this.onScreen;
	}

	public void setOnScreen(boolean onScreen) {
		this.onScreen = onScreen;
	}
}
