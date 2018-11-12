package BBRMain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class StageGenerator implements Constants {
	private int stage[][];
	private int brickHeight;
	private int brickWidth;

	public StageGenerator(int rows, int cols) {
		stageInit(rows, cols);
		this.brickWidth = (WINDOW_WIDTH - 2 * HOR_PAD) / cols;
		this.brickHeight = (WINDOW_HEIGHT / 2 - VER_PAD * 2) / cols;
	}

	public void stageInit(int rows, int cols) {
		this.stage = new int[rows][cols];
		for (int i = 0; i < this.stage.length; i++) {
			for (int j = 0; j < this.stage[0].length; j++) {
				int randValue = (int) (Math.random() * 3 + 1);
				stage[i][j] = randValue;
			}
		}

		this.stage[2][2] = 4;
		this.stage[3][6] = 6;

	}

	public void paint(Graphics2D g) {

		for (int rows = 0; rows < this.stage.length; rows++) {
			for (int cols = 0; cols < this.stage[0].length; cols++) {
				if (stage[rows][cols] > 0) {
					if (stage[rows][cols] == 1) {
						g.setColor(Color.decode("#CC99FF"));
					}
					if (stage[rows][cols] == 2) {
						g.setColor(Color.decode("#B266FF"));
					}
					if (stage[rows][cols] == 3) {
						g.setColor(Color.decode("#7F00FF"));
					}
					if (stage[rows][cols] == Boosters.WIDER_PADDLE) {
						g.setColor(Boosters.WIDER_PADDLE_COLOR);
					}
					if (stage[rows][cols] == Boosters.FASTER_BALL) {
						g.setColor(Boosters.FASTER_BALL_COLOR);
					}
					g.fillRect(cols * brickWidth + HOR_PAD, rows * brickHeight + VER_PAD, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(cols * brickWidth + HOR_PAD, rows * brickHeight + VER_PAD, brickWidth, brickHeight);
				}
			}
		}
	}

	public boolean isWinner() {
		boolean winner = false;
		int bricksCount = 0;

		for (int rows = 0; rows < this.stage.length; rows++) {
			for (int cols = 0; cols < this.stage[0].length; cols++) {
				bricksCount += this.stage[rows][cols];
			}
		}

		if (bricksCount == 0) {
			winner = true;
		}

		return winner;
	}

	public int[][] getStage() {
		return this.stage;
	}

	public void setBrick(int value, int rows, int cols) {
		this.stage[rows][cols] = value;
	}

	public int getBrickWidth() {
		return this.brickWidth;
	}

	public int getBrickHeight() {
		return this.brickHeight;
	}

	public void brickHit(int rows, int cols) {
		stage[rows][cols] -= 1;
		if (stage[rows][cols] < 0) {
			stage[rows][cols] = 0;
		}
	}
}
