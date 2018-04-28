package blockBreakerSRC;

/* Animate Class that implements Runnable to run the actual thread that runs the program */
public class AnimateGame implements Runnable {

	GamePanel bp; // call to BlockBreakerPanel

	public AnimateGame(GamePanel b) {
		bp = b;
	}

	@Override
	public void run() {
		while (true) {
			bp.update(); // call to update
			try {
				Thread.sleep(10); // sleep thread for 10ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
