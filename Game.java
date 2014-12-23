import java.awt.BorderLayout;
import javax.swing.JFrame;


public class Game {
	static JFrame f;
	
	public static void main(String[] args) {
		GameEngine ge = new GameEngine();
		Board b = new Board();
		
		f = new JFrame();
		f.setUndecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.getContentPane().add(b, BorderLayout.CENTER);
		f.setVisible(true);
	
		try {
			Thread.sleep(100);
		} catch(InterruptedException ie) {}
		b.createObjects();
		f.addKeyListener(new OneInputHandler(b));
		ge.draw(b);
	}
}
