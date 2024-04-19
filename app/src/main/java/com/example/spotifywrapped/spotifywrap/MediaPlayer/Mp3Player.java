package com.example.spotifywrapped.spotifywrap.MediaPlayer;

import android.media.AudioAttributes;
import android.media.MediaPlayer;

import java.io.IOException;

public class Mp3Player {
    public static boolean isStreaming;

    public static void playMp3(String url) throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
            Mp3Player.isStreaming = true;
        } catch (IOException e){
            e.printStackTrace();
        } // try
    }

}
