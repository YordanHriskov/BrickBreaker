package BBRMain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball implements Constants {

	private double ballX;
	private double ballY;
	private double ballXdir;
	private double ballYdir;
	
	private int ballSize;

	public Ball(int ballX, int ballY, int ballXdir, int ballYdir, int ballSize) {
		this.ballX = ballX;
		this.ballY = ballY;
		this.ballXdir = ballXdir;
		this.ballYdir = ballYdir;
		this.ballSize = ballSize;
	}

	public void update() {
		setPosition();
	}

	public void setPosition() {
		this.ballX += ballXdir;
		this.ballY += ballYdir;
		if (this.ballX < 0) {
			this.ballXdir = -ballXdir;
		}
		if (this.ballY < 0) {
			this.ballYdir = -ballYdir;
		}
		if (this.ballX > 670) {
			this.ballXdir = -ballXdir;
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.white);
		g.fillOval((int) ballX, (int) ballY, ballSize, ballSize);

	}

	public Rectangle getBallBox() {
		return new Rectangle((int) ballX, (int) ballY, ballSize, ballSize);
	}

	public void setBallYdir(double dirY) {
		this.ballYdir = dirY;
	}

	public double getBallYdir() {
		return this.ballYdir;
	}

	public void setBallXdir(double dirX) {
		this.ballXdir = dirX;
	}

	public double getBallXdir() {
		return this.ballXdir;
	}

	public double getBallX() {
		return this.ballX;
	}

	public boolean isLooser() {
		boolean looser = false;

		if (this.ballY > WINDOW_HEIGHT - ballSize) {
			looser = true;
		}

		return looser;
	}

}
