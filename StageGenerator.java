package BrickBrakerGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class StageGenerator {
	public int stage[][];
	public int brickHeight;
	public int brickWidth;

	public StageGenerator(int rows, int cols) {
		stage = new int[rows][cols];
		for (int i = 0; i < this.stage.length; i++) {
			for (int j = 0; j < this.stage[0].length; j++) {
				stage[i][j] = 1;
			}
		}

		this.brickHeight = 100 / rows;
		this.brickWidth = 540 / cols;
	}

	public void paint(Graphics2D p) {
		for (int i = 0; i < stage.length; i++) {
			for (int j = 0; j < stage[0].length; j++) {
				if (stage[i][j] > 0) {
					p.setColor(Color.decode("#B266FF"));
					p.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					p.setStroke(new BasicStroke(3));
					p.setColor(Color.black);
					p.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}

	public void brickIntersection(int value, int rows, int cols) {
		stage[rows][cols] = value;
	}
}
