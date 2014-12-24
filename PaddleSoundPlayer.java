import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioSystem;

public class PaddleSoundPlayer extends SoundPlayer{
	private String filename = "Sounds/PaddleSound.wav";
	private URL url;
	private File file;
	
	public PaddleSoundPlayer() {
		try {
			if(makingJar())
				url = getClass().getResource(filename);
			else
				file = new File(prefix + filename);
			
			if(makingJar())
				ais = AudioSystem.getAudioInputStream(url);
			else
				ais = AudioSystem.getAudioInputStream(file);
		
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch(Exception e) { e.printStackTrace(); }
	}
}
