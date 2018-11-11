package BBRMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HUD {

	private int score;
	GamePlay gamePlay;

	public HUD() {
		start();
	}

	public void start() {
		this.score = 0;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.decode("#66B2FF"));
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Score: " + getScore(), 600, 500);

	}

	public int getScore() {
		return this.score;
	}

	public void addScore(int points) {
		this.score += points;
	}
}
