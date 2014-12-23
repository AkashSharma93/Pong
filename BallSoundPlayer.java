import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class BallSoundPlayer extends SoundPlayer{
	private String filename1 = "src/Sounds/BallSound.wav";		//Add src/... to make it run in eclipse
	private String filename2 = "src/Sounds/BallMissSound.wav";
//	private URL url1, url2;
	
	Clip clip2;
	AudioInputStream ais2;
	
	Ball ball;
	
	public BallSoundPlayer(Ball ball) {
		this.ball = ball;
		try {
//			url1 = getClass().getResource(filename1);		//Used to make JAR.
			File file = new File(filename1);
			if(file.exists()) {
				ais = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(ais);
			}
			
//			url2 = getClass().getResource(filename2);
			file = new File(filename2);
			if(file.exists()) {
				ais2 = AudioSystem.getAudioInputStream(file);
				clip2 = AudioSystem.getClip();
				clip2.open(ais2);
			}
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	public void playSound() {
		if(ball.missed()) {
			clip2.setFramePosition(0);
			clip2.start();
		}
		else {
			super.playSound();
		}
	}
	
	public void stop() {
		clip2.stop();
		super.stop();
	}
}
