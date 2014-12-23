import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OneInputHandler implements KeyListener{
	Board board;
	Paddle p1, p2;
	Ball ball;
	
	public OneInputHandler(Board board) {
		this.board = board;
		this.p1 = board.getPaddle1();
		this.p2 = board.getPaddle2();	//Player's paddle.
		this.ball = board.getBall();
		
		Thread ai = new Thread(new AI());
		ai.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {		//Using incr to remove the massive lag caused by calling move() right from here!
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p2.setIncr(-10);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setIncr(10);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) { 
			p2.setIncr(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public class AI implements Runnable {		//AI, duh...
		public void run() {
			while(true) {
				if(ball.getY() < p1.getY()) {
					synchronized(p1) {
						p1.setIncr(-10);
					}
				}
				else if(ball.getY() > p1.getY() + p1.getLength()) {
					p1.setIncr(10);
				}
				else {
					p1.setIncr(0);
				}
				
				try {
					Thread.sleep(1);
				} catch(InterruptedException ie) { System.out.println("Something wrong in AI!"); }
			}
		}
	}
}
