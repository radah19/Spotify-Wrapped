package com.example.spotifywrapped;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Macros {
    public static final String SHARED_PREFS = "sharedPrefs";

    public static List<String> ls_genres = Arrays.asList("Rock", "Metal");

    public static List<SpotifyTrack> ls_tracks = Arrays.asList(
            new SpotifyTrack(ls_genres, "Markle", "The Markler", "https://zombo.com", 4035, LocalDateTime.now(),
                    "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228")
    );

    public static List<SpotifyWrappedSummary> ls_summaries = Arrays.asList(
            new SpotifyWrappedSummary("Jim123", new ArrayList<String>(), ls_tracks, "sfgdfgdj", LocalDateTime.now())
    );
}
