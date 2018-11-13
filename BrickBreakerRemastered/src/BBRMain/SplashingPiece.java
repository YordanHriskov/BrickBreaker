package BBRMain;

import java.awt.Color;
import java.awt.Graphics2D;

public class SplashingPiece {

	private int pieceX;
	private int pieceY;
	private double pieceXdir;
	private double pieceYdir;

	private double gravity;
	private int pieceSize;

	private Color color;
	private StageGenerator stage;

	public SplashingPiece(int brickX, int brickY, StageGenerator stage) {
		this.stage = stage;
		this.pieceX = brickX + stage.getBrickWidth() / 2;
		this.pieceY = brickY + stage.getBrickHeight() / 2;
		this.pieceXdir = (Math.random() * 30 - 15);
		this.pieceYdir = (Math.random() * 30 - 15);
		this.pieceSize = (int) (Math.random() * 15 + 2);
		this.color = new Color(204, 153, 255);
		this.gravity = 0.8;
	}

	public void update() {
		this.pieceX += this.pieceXdir;
		this.pieceY += this.pieceYdir;
		this.pieceYdir += this.gravity;
	}

	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int) pieceX, (int) pieceY, pieceSize, pieceSize);
	}
}
