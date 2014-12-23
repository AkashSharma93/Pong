import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public abstract class GameMode implements KeyListener{
	@Override
	public abstract void keyPressed(KeyEvent arg0);
	@Override
	public abstract void keyReleased(KeyEvent e);
	@Override
	public abstract void keyTyped(KeyEvent e);
}
