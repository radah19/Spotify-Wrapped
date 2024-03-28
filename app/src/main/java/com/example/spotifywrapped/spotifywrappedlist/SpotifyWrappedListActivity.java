package com.example.spotifywrapped.spotifywrappedlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyArtist;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.navbar.NavbarClass;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyWrappedListActivity extends AppCompatActivity {
    public List<SpotifyWrappedSummary> userSpotifyWrappedSummaries;
    public String orderBy;

    // -------------------- Testing Variables ----------------------
    public static List<String> ls_genres = Arrays.asList("Rock", "Metal", "Trap", "Ice Cream Wave");
    public static List<String> ls_genres2 = Arrays.asList("Hip Hop", "Classical");
    public static List<String> ls_genres3 = Arrays.asList("Hip Hop", "Gym Hop", "Rabbit Hop");

    static String hmaryaLink = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fis5-ssl.mzstatic.com%2Fimage%2Fthumb%2FMusic124%2Fv4%2F22%2Ffb%2Fee%2F22fbeefc-820b-ce52-dd96-c54a943d2091%2Fartwork.jpg%2F1200x1200bf-60.jpg&f=1&nofb=1&ipt=a2f44d309809848402616749596f7e903ac709dc1d9b8f398a7da17a36c5886e&ipo=images";
    public static List<SpotifyTrack> ls_tracks = Arrays.asList(
            new SpotifyTrack(0, "Shubh Saran", "The Sacred",
                    "https://open.spotify.com/track/0eqaqo2r4ukxyNsjm3kcyr", hmaryaLink,
                    LocalTime.of(0, 2, 57),
                    LocalDateTime.of(2017, Month.MAY, 7, 0, 0),
                    28),
            new SpotifyTrack(1, "The Markler", "Markle", "https://zombo.com",
                    "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228",
                    LocalTime.of(0, 3, 26),
                    LocalDateTime.now(), 100),
            new SpotifyTrack(2, "Yuno Miles", "Blizzard", "https://zombo.com",
                                hmaryaLink, LocalTime.of(6, 11, 45),
                                LocalDateTime.now(), 63)
    );

    public static List<SpotifyTrack> ls_tracks2 = Arrays.asList(
            new SpotifyTrack(3, "The Markler", "Markle", "https://zombo.com",
                    "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228",
                    LocalTime.of(0, 3, 26),
                    LocalDateTime.now(), 100)
    );

    public static List<SpotifyArtist> ls_artists = Arrays.asList(
        new SpotifyArtist(0, ls_genres3, "Markler", "https://zombo.com",
                "https://i.kym-cdn.com/photos/images/original/002/578/725/794", 1, 392409230),
        new SpotifyArtist(1, ls_genres2, "Shubh Saran", "https://open.spotify.com/artist/2F9WG9ugzneeesOZfmQ18V",
            "https://i.scdn.co/image/ab6761610000e5eb838ec4e15914cfe0dd427f38", 25, 28489),
        new SpotifyArtist(2, ls_genres, "My Clone", "https://open.spotify.com/artist/2F9WG9ugzneeesOZfmQ18V",
            "https://i.scdn.co/image/ab6761610000e5eb838ec4e15914cfe0dd427f38", 2463, 85)
    );

    public static List<SpotifyArtist> ls_artists2 = Arrays.asList(
        new SpotifyArtist(3, ls_genres, "My Clone", "https://open.spotify.com/artist/2F9WG9ugzneeesOZfmQ18V",
            "https://i.scdn.co/image/ab6761610000e5eb838ec4e15914cfe0dd427f38", 2463, 85)
    );

    public static List<SpotifyWrappedSummary> ls_summaries = Arrays.asList(
            new SpotifyWrappedSummary(0, "Jim123", "My Spotify Wrap 2023", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks, ls_tracks2, ls_artists, ls_genres),
            new SpotifyWrappedSummary(1, "Jim123", "Spotify Wrap but bad!!", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks2, ls_tracks2, ls_artists2, ls_genres),
            new SpotifyWrappedSummary(2, "Jim123", "I love burger king", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks, ls_tracks, ls_artists, ls_genres)
    );

    public static List<SpotifyWrappedSummary> ls_friendSummaries = Arrays.asList(
            new SpotifyWrappedSummary(3, "Jim123", "Spotify Wrap but great!!", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks2, ls_tracks2, ls_artists, ls_genres)
    );
    // --------------------------------------------------------------

    //Widgets
    private RecyclerView spotifyWrappedList;
    private Button addSpotifyWrappedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_wrapped_list);

        userSpotifyWrappedSummaries = new ArrayList<>();
        userSpotifyWrappedSummaries.addAll(ls_summaries);

        initUserSummaries();
        initWidgets();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        spotifyWrappedList.setLayoutManager(llm);
        spotifyWrappedList.setAdapter(new SpotifyWrappedListAdapter(userSpotifyWrappedSummaries));
    }

    public void initUserSummaries(){
        this.userSpotifyWrappedSummaries = ls_summaries;
    }

    public void initWidgets(){
        spotifyWrappedList = findViewById(R.id.spotifyWrappedList);
        addSpotifyWrappedButton = findViewById(R.id.addSpotifyWrappedButton);
        NavbarClass.initializeNavbar(this);
    }
}