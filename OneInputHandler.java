import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OneInputHandler extends GameMode{
	Board board;
	Paddle p1, p2;
	Ball ball;
	private int incr = 10;
	
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
			p2.setIncr(-incr);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setIncr(incr);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			ball.paddleSound.stop();
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
				if(ball.getY() - ball.getDiameter()/2 < p1.getY()) {
					synchronized(p1) {
						p1.setIncr(-incr);
					}
				}
				else if(ball.getY() + ball.getDiameter()/2 > p1.getY() + p1.getLength()) {
					p1.setIncr(incr);
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
