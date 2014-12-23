import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TwoInputHandler implements KeyListener {
	Board board;
	Paddle p1, p2;
	
	public TwoInputHandler(Board b) {
		board = b;
		this.p1 = b.getPaddle1();
		this.p2 = b.getPaddle2();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {		//Right paddle.
			p2.setIncr(-10);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setIncr(10);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W){		//Left paddle.
			p1.setIncr(-10);
		} 
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			p1.setIncr(10);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
