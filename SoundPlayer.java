import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;


public abstract class SoundPlayer {
	AudioInputStream ais;
	Clip clip;
	private boolean jar = false;
	String prefix = "src/";
	
	public void playSound() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		try {
			clip.stop();
			ais.close();
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	public boolean makingJar() {
		return jar;
	}
}
