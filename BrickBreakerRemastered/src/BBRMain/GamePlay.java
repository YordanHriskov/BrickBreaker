package BBRMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements Constants {
	private static final long serialVersionUID = 1L;

	protected boolean playing = true;
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMotionListener mouseListener;
	private int mouseX;

	private Ball ball;
	private Paddle paddle;
	private StageGenerator stage;
	private HUD hud;
	private ArrayList<Boosters> boosters;

	public GamePlay() {
		play();
	}

	public void play() {

		this.mouseX = 0;
		this.stage = new StageGenerator(4, 7);
		this.ball = new Ball();
		this.paddle = new Paddle();
		this.hud = new HUD();
		this.mouseListener = new MyMouseMotionListener();
		addMouseMotionListener(mouseListener);
		this.boosters = new ArrayList<Boosters>();

		playing = true;
		image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (playing) {
			// update
			update();

			// draw
			draw();

			// display
			repaint();

			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void checkIntersections() {
		Rectangle ballR = ball.getBallBox();
		Rectangle paddleR = paddle.getPaddleBox();

		if (ballR.intersects(paddleR)) {
			this.ball.setBallYdir(-ball.getBallYdir());
			if (ball.getBallX() < mouseX + paddle.getWidth() / 4) {
				ball.setBallXdir(ball.getBallXdir() - 0.5);
			}

			if (ball.getBallX() < mouseX + paddle.getWidth() && ball.getBallX() > mouseX + paddle.getWidth() / 4) {
				ball.setBallXdir(ball.getBallXdir() + 0.5);
			}
		}

		A: for (int rows = 0; rows < stage.getStage().length; rows++) {
			for (int cols = 0; cols < stage.getStage()[0].length; cols++) {

				int currentBrick = stage.getStage()[rows][cols];

				if (currentBrick > 0) {
					int brickX = cols * stage.getBrickWidth() + Constants.HOR_PAD;
					int brickY = rows * stage.getBrickHeight() + Constants.VER_PAD;
					int brickWidth = stage.getBrickWidth();
					int brickHeight = stage.getBrickHeight();

					Rectangle brickR = new Rectangle(brickX, brickY, brickWidth, brickHeight);

					if (ballR.intersects(brickR)) {

						if (stage.getStage()[rows][cols] > 3) {
							this.boosters.add(new Boosters(brickX, brickY, currentBrick, brickWidth, brickHeight));
							this.stage.setBrick(3, rows, cols);
						} else {
							this.stage.brickHit(rows, cols);
						}

						this.stage.brickHit(rows, cols);
						this.hud.addScore(5);
						if (ball.getBallX() + 19 <= brickR.x || ball.getBallX() + 1 >= brickR.x + brickR.width) {
							this.ball.setBallXdir(-ball.getBallXdir());
						} else {
							this.ball.setBallYdir(-ball.getBallYdir());
						}

						break A;
					}
				}
			}
		}
	}

	public void update() {
		checkIntersections();
		this.ball.update();

		for (Boosters bo : this.boosters) {
			bo.update();
		}
	}

	public void draw() {

		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, WINDOW_WIDTH, WINDOW_HEIGHT);

		// border
		g.setColor(Color.decode("#FF6666"));
		g.fillRect(0, 0, BORDER_LINE_SIZE, WINDOW_HEIGHT);
		g.fillRect(0, 0, WINDOW_WIDTH, BORDER_LINE_SIZE);
		g.fillRect(RIGHT_BORDER_LINE, 0, BORDER_LINE_SIZE, WINDOW_HEIGHT);

		this.stage.paint(g);
		this.ball.paint(g);
		this.paddle.paint(g);
		paintBoosters();

		if (playing) {
			this.hud.paint(g);
		}

		if (this.stage.isWinner() == true) {
			playing = false;
			paintWinner();
		}

		if (this.ball.isLooser() == true) {
			playing = false;
			paintLooser();
		}

	}

	public void paintWinner() {
		g.setColor(Color.decode("#009900"));
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("LEVEL PASSED", 150, END_GAME_LABEL);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You scored " + this.hud.getScore() + " points!", 190, SCORE_LABEL);
		g.setColor(Color.decode("#99CCFF"));
		g.setFont(new Font("serif", Font.ITALIC, 30));
		g.drawString("Press SPACE BAR to restart the game!", 100, RESTART_LABEL);
	}

	public void paintLooser() {
		g.setColor(Color.decode("#990000"));
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("GAME OVER", 180, END_GAME_LABEL);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You scored " + this.hud.getScore() + " points!", 190, SCORE_LABEL);
		g.setColor(Color.decode("#99CCFF"));
		g.setFont(new Font("serif", Font.ITALIC, 30));
		g.drawString("Press SPACE BAR to restart the game!", 100, RESTART_LABEL);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
		g2.dispose();
	}

	public void paintBoosters() {
		for (Boosters bo : boosters) {
			bo.paint(g);
		}
	}

	private class MyMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			paddle.mouseMovement(e.getX());
		}

	}

}
