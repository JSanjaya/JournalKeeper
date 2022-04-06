package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// Plays a beep sound when the user enters the wrong password

public class PlaySound {
// Code taken from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html

    // EFFECTS: Establishes a pathway to sound file, then plays that file
    public void playSound() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("./resources/Wrong.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


}


