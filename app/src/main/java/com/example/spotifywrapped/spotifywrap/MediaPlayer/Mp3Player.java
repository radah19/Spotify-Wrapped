package com.example.spotifywrapped.spotifywrap.MediaPlayer;

import android.media.AudioAttributes;
import android.media.MediaPlayer;

import java.io.IOException;

public class Mp3Player {
    public static boolean isStreaming;
    public static String elementPlaying = "";
    public static MediaPlayer mediaPlayer;

    public static void playMp3(String url) throws IOException {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.stop();

        mediaPlayer = new MediaPlayer();

        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
            isStreaming = true;
        } catch (IOException e){
            e.printStackTrace();
        } // try
    }

    public static void stopMp3() throws IOException {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            isStreaming = false;
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

}
