package BBRMain;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class SplashingBrick {

	private ArrayList<SplashingPiece> pieces;
	private int splashX;
	private int splashY;
	private StageGenerator stage;
	private boolean live;
	private long startTime;

	public SplashingBrick(int splashX, int splashY, StageGenerator stage) {
		this.splashX = splashX;
		this.splashY = splashY;
		this.stage = stage;
		this.live = true;
		this.startTime = System.nanoTime();
		this.pieces = new ArrayList<SplashingPiece>();
		start();
	}

	public void start() {
		int randKaboom = (int) (Math.random() * 20 + 10);

		for (int i = 0; i < randKaboom; i++) {
			this.pieces.add(new SplashingPiece(splashX, splashY, stage));
		}
	}

	public void update() {
		for (SplashingPiece sp : pieces) {
			sp.update();
		}

		if (((System.nanoTime() - startTime) / 1000000 > 2000) && live) {
			this.live = false;
		}
	}

	public void paint(Graphics2D g) {
		for (SplashingPiece sp : pieces) {
			sp.paint(g);
		}
	}

	public boolean getLive() {
		return this.live;
	}
}
