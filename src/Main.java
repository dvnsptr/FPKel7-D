/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 7
 * Members  :
 * 1. 5026231020 - Diva Nesia Putri
 * 2. 5026231162 - I Nyoman Mahadyana Bhaskara
 * 3. 5026231211 - Hafidz Putra Deramawan
 * ------------------------------------------------------
 */
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File file = new File("bgm.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        clip.start();

        SnL g1 = new SnL(100);
        g1.play();

        clip.close();
        audioStream.close();
    }
}