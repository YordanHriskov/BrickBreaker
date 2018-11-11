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

	private Color boosterColor;
	public static final Color WIDER_PADDLE_COLOR = new Color(255, 153, 51);
	public static final Color FASTER_BALL_COLOR = new Color(102, 178, 255);

	public Boosters(int boosterX, int boosterY, int boosterType, int boosterWidth, int boosterHeight) {
		this.boosterX = boosterX;
		this.boosterY = boosterY;
		this.boosterType = boosterType;
		this.boosterWidth = boosterWidth;
		this.boosterHeight = boosterHeight;

		if (this.boosterType < 5) {
			this.boosterType = 4;
		}
		if (this.boosterType > 6) {
			this.boosterType = 6;
		}

		if (this.boosterType == WIDER_PADDLE) {
			this.boosterColor = WIDER_PADDLE_COLOR;
		}

		if (this.boosterType == FASTER_BALL) {
			this.boosterColor = FASTER_BALL_COLOR;
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

	// get and set for boosterX **********************
	public int getBoosterX() {
		return this.boosterX;
	}

	public void setBoosterX(int boosterX) {
		this.boosterX = boosterX;
	}

	// get and set for boosterY **********************
	public int getBoosterY() {
		return this.boosterY;
	}

	public void setBoosterY(int boosterY) {
		this.boosterY = boosterY;
	}

	// get and set for boosterYdir *******************
	public int getBoosterYdir() {
		return this.boosterYdir;
	}

	public void setBoosterYdir(int boosterYdir) {
		this.boosterYdir = boosterYdir;
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
