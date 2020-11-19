package com.gui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    private static float volume = 0.5f;


    public synchronized void playSound(String filePath) {
        System.out.println(volume + " głośność");
        new Thread(() -> {

            try {

                System.out.println(filePath + " Sound played");
                File sound = new File(filePath);
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(sound);
                Clip clip = AudioSystem.getClip();
                clip.open(inputStream);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = gainControl.getMaximum() - gainControl.getMinimum();
                float gain = (range * volume) + gainControl.getMinimum();
                if (filePath.equals("src/sounds/menu.wav")) {
                    gainControl.setValue(-15.0f);
                    clip.loop(5);

                } else
                    gainControl.setValue(gain);
                clip.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void setVolume(float volume1) {
        volume = volume1 / 10;
    }

}



