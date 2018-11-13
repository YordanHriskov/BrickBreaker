package BBRMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle implements Constants {

	private int paddleWidth;
	private int paddleHeight;
	private int originalWidth;
	private long widthTimer;
	private boolean timeWidth;
	
	private double paddleX;
	private final int paddleY = WINDOW_HEIGHT - 40;

	public Paddle(int width, int height) {
		this.paddleWidth = width;
		this.originalWidth = width;
		this.paddleHeight = height;
		this.timeWidth = false;
		
		this.paddleX = WINDOW_WIDTH / 2 - paddleWidth / 2;
	}

	public void update() {
		
		if((System.nanoTime() - widthTimer) / 1000 > 5000000 && timeWidth == true) {
			this.paddleWidth = originalWidth;
			timeWidth = false;
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.decode("#CC6600"));
		g.fillRect((int) paddleX, paddleY, paddleWidth, paddleHeight);
		
		if(timeWidth) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("serif", Font.ITALIC, 11));
			g.drawString("Shrinking in: " + (5 - (System.nanoTime() - widthTimer) / 1000000000), (int)paddleX, paddleY + 9);
		}
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
	
	public void setWidth(int pWidth) {
		this.timeWidth = true;
		this.paddleWidth = pWidth;
		setWidthTimer();
	}
	
	public void setWidthTimer() {
		this.widthTimer = System.nanoTime();
	}
}
