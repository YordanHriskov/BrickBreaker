package BBRMain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle implements Constants {

	private int paddleWidth;
	private int paddleHeight;
	private final int paddleY = WINDOW_HEIGHT - 40;
	private double paddleX;

	public Paddle() {
		this.paddleWidth = 100;
		this.paddleHeight = 10;
		this.paddleX = WINDOW_WIDTH / 2 - paddleWidth / 2;
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.decode("#CC6600"));
		g.fillRect((int) paddleX, paddleY, paddleWidth, paddleHeight);
	}

	public void mouseMovement(int mouseXpos) {
		this.paddleX = mouseXpos;
		if (this.paddleX > WINDOW_WIDTH - paddleWidth) {
			this.paddleX = RIGHT_BORDER_LINE - paddleWidth;
		}
	}

	public Rectangle getPaddleBox() {
		return new Rectangle((int) paddleX, (int) paddleY, paddleWidth, paddleHeight);
	}
	
	public int getWidth() {
		return paddleWidth;
	}
}
