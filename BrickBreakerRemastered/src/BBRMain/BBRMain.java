package BBRMain;

import javax.swing.JFrame;

public class BBRMain implements Constants{
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		GamePlay panel = new GamePlay();
		
		frame.setTitle("Brick Breaker Remastered");
		frame.setBounds(WINDOW_CENTER_LEFT, WINDOW_CENTER_TOP, WINDOW_WIDTH, WINDOW_HEIGHT);
//		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);		
		panel.run();
		

	}

}
