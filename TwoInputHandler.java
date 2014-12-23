import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TwoInputHandler extends GameMode {
	Board board;
	Paddle p1, p2;
	private int incr = 10;
	
	public TwoInputHandler(Board b) {
		board = b;
		this.p1 = b.getPaddle1();
		this.p2 = b.getPaddle2();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {		//Right paddle.
			p2.setIncr(-incr);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setIncr(incr);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W){		//Left paddle.
			p1.setIncr(-incr);
		} 
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			p1.setIncr(incr);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			board.getBall().paddleSound.stop();
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setIncr(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
			p1.setIncr(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
