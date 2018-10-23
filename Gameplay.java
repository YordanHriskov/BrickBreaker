package BrickBrakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private double score = 0;
	private int totalBricks = 21;

	private Timer timer;
	private int delay = 8;

	private int playerX = 300;
	private int ballX = 340;
	private int ballY = 540;
	private int ballDirX = -1;
	private int ballDirY = -2;

	private StageGenerator stage;

	public Gameplay() {
		stage = new StageGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics p) {
		// background
		p.setColor(Color.black);
		p.fillRect(1, 1, 692, 592);

		// border
		p.setColor(Color.decode("#FF6666"));
		p.fillRect(0, 0, 3, 592);
		p.fillRect(0, 0, 692, 3);
		p.fillRect(691, 0, 3, 592);

		// paddle
		p.setColor(Color.decode("#CC6600"));
		p.fillRect(playerX, 560, 100, 8);

		// ball
		p.setColor(Color.white);
		p.fillOval(ballX, ballY, 20, 20);

		if (totalBricks <= 0) {
			play = false;
			this.ballDirX = 0;
			this.ballDirY = 0;
			p.setColor(Color.decode("#009900"));
			p.setFont(new Font("serif", Font.BOLD, 50));
			p.drawString("LEVEL PASSED", 150, 300);
			p.setFont(new Font("serif", Font.BOLD, 30));
			p.drawString("You scored " + this.score + " points!", 190, 330);
			p.setColor(Color.decode("#99CCFF"));
			p.setFont(new Font("serif", Font.ITALIC, 30));
			p.drawString("Press SPACE BAR to restart the game!", 100, 380);
		}

		if (ballY > 570) {
			play = false;
			this.ballDirX = 0;
			this.ballDirY = 0;
			p.setColor(Color.decode("#990000"));
			p.setFont(new Font("serif", Font.BOLD, 50));
			p.drawString("GAME OVER", 180, 300);
			p.setFont(new Font("serif", Font.BOLD, 30));
			p.drawString("You scored " + this.score + " points!", 190, 330);
			p.setColor(Color.decode("#99CCFF"));
			p.setFont(new Font("serif", Font.ITALIC, 30));
			p.drawString("Press SPACE BAR to restart the game!", 100, 380);
		}

		// score
		p.setColor(Color.decode("#66B2FF"));
		p.setFont(new Font("serif", Font.BOLD, 15));
		p.drawString("Score: " + this.score, 600, 500);

		//
		stage.paint((Graphics2D) p);

		p.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		timer.start();
		if (play) {
			if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 560, 100, 8))) {
				this.ballDirY = -ballDirY;
			}

			A: for (int i = 0; i < this.stage.stage.length; i++) {
				for (int j = 0; j < this.stage.stage[0].length; j++) {
					if (this.stage.stage[i][j] > 0) {
						int brickX = j * stage.brickWidth + 80;
						int brickY = i * stage.brickHeight + 50;
						int brickHeight = stage.brickHeight;
						int brickWidth = stage.brickWidth;

						Rectangle r = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballR = new Rectangle(ballX, ballY, 20, 20);
						Rectangle brickR = r;

						if (ballR.intersects(brickR)) {
							stage.brickIntersection(0, i, j);
							totalBricks--;
							this.score += 5;

							if (ballX + 19 <= brickR.x || ballX + 1 >= brickR.x + brickR.width) {
								this.ballDirX = -ballDirX;
							} else {
								this.ballDirY = -ballDirY;
							}

							break A;
						}
					}
				}
			}

			this.ballX += ballDirX;
			this.ballY += ballDirY;
			if (this.ballX < 0) {
				this.ballDirX = -ballDirX;
			}
			if (this.ballY < 0) {
				this.ballDirY = -ballDirY;
			}
			if (this.ballX > 670) {
				this.ballDirX = -ballDirX;
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent kr) {
	}

	@Override
	public void keyTyped(KeyEvent kt) {
	}

	@Override
	public void keyPressed(KeyEvent kp) {
		if (kp.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (this.playerX >= 592) {
				this.playerX = 592;
			} else {
				moveRight();
			}
		}
		
		if (kp.getKeyCode() == KeyEvent.VK_LEFT) {
			if (this.playerX < 13) {
				this.playerX = 13;
			} else {
				moveLeft();
			}
		}

		if (kp.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!play) {
				play = true;
				this.ballX = 120;
				this.ballY = 350;
				this.ballDirX = -1;
				this.ballDirY = -2;
				this.playerX = 310;
				this.score = 0;
				this.totalBricks = 21;
				stage = new StageGenerator(3, 7);
				repaint();
			}
		}
	}

	public void moveRight() {
		play = true;
		this.playerX += 10;
	}

	public void moveLeft() {
		play = true;
		this.playerX -= 10;
	}
}
