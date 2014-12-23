
public class GameEngine {
	public void draw(Board board) {
		while(true) {
			try {
				board.repaint();
				Thread.sleep(10);
			} catch(InterruptedException ie) {}
		}
	}
}
