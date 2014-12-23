import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioSystem;

public class PaddleSoundPlayer extends SoundPlayer{
	private String filename = "src/Sounds/PaddleSound.wav";
	
	public PaddleSoundPlayer() {
		try {
//			URL file2 = getClass().getResource(filename);
			File file = new File(filename);
			if(file.exists()) {
				ais = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(ais);
			}
		} catch(Exception e) { e.printStackTrace(); }
	}
}
