package blockBreakerSRC;

import javax.swing.JFrame;

/* 
 * Main Class that starts the Swing Interface for the Block Breaker Game 
 * 
 * Author: Ivan Tinov
 * 
 */

public class StartGame {

	/* Main Method */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Block Breaker"); // start JFrame and set title

		GamePanel panel = new GamePanel(); // initialize the BlockBreakerPanel Class
		frame.getContentPane().add(panel); // add panel

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
		frame.setSize(1280, 720); 
		frame.setResizable(false); // default res, unresizable
	}

}
