package blockBreakerSRC;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/* Block class that extends rectangle to serve as a basic template for the creation of the blocks */
public class Block extends Rectangle {
	Image pic;
	int dx = 3;
	int dy = -3;
	Rectangle left, right;
	boolean destroyed = false;
	boolean powerup = false;

	/* Block constructor that accepts values for x, y, width, height, and string representation for the picture object */
	public Block(int a, int b, int w, int h, String s) {
		x = a;
		y = b;
		width = w;
		height = h;
		left = new Rectangle(a - 1, b, 1, h);
		right = new Rectangle(a + w + 1, b, 1, h);
		pic = Toolkit.getDefaultToolkit().getImage(s);
	}

	/* draw method that draws the blocks on the screen as long as they are not destroyed by the ball */
	public void draw(Graphics g, Component c) {
		if (!destroyed) // as long as the block isn't destroyed
			g.drawImage(pic, x, y, width, height, c);
	}
}
