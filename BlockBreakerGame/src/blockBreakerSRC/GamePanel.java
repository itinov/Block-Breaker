package blockBreakerSRC;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements KeyListener {

	/* ArrayList class is used to be able to create a row of blocks, creating more balls, and the spawning of random powerups from the blocks that are destroyed */
	ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Block> ball = new ArrayList<Block>();
	ArrayList<Block> powerup = new ArrayList<Block>();

	Block paddle; // paddle class
	Thread thread; // creating a thread to run the application
	AnimateGame animate; // allows to animate the movement of the ball, and the falling of the powerups
	int size = 25;

	/* BlockBreakerPanel Constructor with no parameter input */
	public GamePanel() { 
		paddle = new Block(175, 480, 150, 25, "paddle.png"); // initalize the paddle at the bottom
		
		/* Using for-loops below to create different colored blocks: blue, red, green and yellow */
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 0, 60, 25, "blue.png"));
		}
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 25, 60, 25, "red.png"));
		}
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 50, 60, 25, "green.png"));
		}
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 75, 60, 25, "yellow.png"));
		}
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 100, 60, 25, "blue.png"));
		}
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 125, 60, 25, "red.png"));
		}
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 150, 60, 25, "yellow.png"));
		}
		for (int i = 0; i < 21; i++) {
			blocks.add(new Block((i * 60 + 2), 175, 60, 25, "green.png"));
		}

		Random random = new Random(); // initializing the random class
		
		/* Set custom power-ups */
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;

		ball.add(new Block(237, 437, 25, 25, "ball.png")); // adding the ball right on top of the paddle
		addKeyListener(this);
		setFocusable(true);

	}

	/* paintComponent method that uses Graphics to draw the blocks and the paddle */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Block b : blocks)
			b.draw(g, this);
		for (Block b : ball)
			b.draw(g, this);
		for (Block p : powerup)
			p.draw(g, this);
		paddle.draw(g, this);
	}

	/* update method used to update the screen everytime a block is destroyed by a ball */
	public void update() {
		for (Block p : powerup) {
			p.y += 1;
			if (p.intersects(paddle) && !p.destroyed) {
				p.destroyed = true;
				ball.add(new Block(paddle.x + 75, 437, 25, 25, "ball.png"));
			}
		}
		for (Block ba : ball) {
			ba.x += ba.dx;
			if (ba.x > (getWidth() - size) && ba.dx > 0 || ba.x < 0)
				ba.dx *= -1;
			if (ba.y < 0 || ba.intersects(paddle))
				ba.dy *= -1;
			ba.y += ba.dy;
			for (Block b : blocks) {
				if ((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed) {
					ba.dx *= -1;
					b.destroyed = true;
					// add a powerup
					if (b.powerup)
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				} else if (ba.intersects(b) && !b.destroyed) {
					b.destroyed = true;
					ba.dy *= -1;
					// add a powerup
					if (b.powerup)
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
			}
		}
		repaint();
	}

	/* keyPressed method from keyListener that allows for movement of the paddle */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			animate = new AnimateGame(this);
			thread = new Thread(animate);
			thread.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) { // moving the paddle left, plus added bounds so that the paddle is limited to the side of the screen
			paddle.x -= 75;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) { // same as movement to the right
			paddle.x += 75;
		}
	}
	
	// ignore
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	// ignore
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}




