package com.example.spotifywrapped.spotifywrappedlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyAPIManager;
import com.example.spotifywrapped.SpotifyArtist;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.navbar.NavbarClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyWrappedListActivity extends AppCompatActivity {
    public List<SpotifyWrappedSummary> userSpotifyWrappedSummaries, friendSpotifyWrappedSummaries;
    public String orderBy;

    // -------------------- Testing Variables ----------------------
    public static List<String> ls_genres = Arrays.asList("Rock", "Metal", "Trap", "Ice Cream Wave", "Soul Wave", "Solar Punk Wave", "Prog Rock", "Death Metal", "Mark Metal");
    public static List<String> ls_genres2 = Arrays.asList("Hip Hop", "Classical");
    public static List<String> ls_genres3 = Arrays.asList("Hip Hop", "Gym Hop", "Rabbit Hop");

    public static List<SpotifyTrack> ls_tracks = Arrays.asList(
            SpotifyAPIManager.loadSpotifyTrackById("0eqaqo2r4ukxyNsjm3kcyr"),
            SpotifyAPIManager.loadSpotifyTrackById("11dFghVXANMlKmJXsNCbNl")
    );

    public static List<SpotifyTrack> ls_tracks2 = Arrays.asList(
            new SpotifyTrack("3", "The Markler", "Markle", "https://zombo.com",
                    "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228",
                    LocalTime.of(0, 3, 26),
                    LocalDate.now(), 100)
    );

    public static List<SpotifyArtist> ls_artists = Arrays.asList(
            SpotifyAPIManager.loadSpotifyArtistById("0TnOYISbd1XYRBk9myaseg"),
        new SpotifyArtist("0", ls_genres3, "Markler", "https://zombo.com",
                "https://i.kym-cdn.com/photos/images/original/002/578/725/794", 1, 392409230),
        new SpotifyArtist("1", ls_genres2, "Shubh Saran", "https://open.spotify.com/artist/2F9WG9ugzneeesOZfmQ18V",
            "https://i.scdn.co/image/ab6761610000e5eb838ec4e15914cfe0dd427f38", 25, 28489),
        new SpotifyArtist("2", ls_genres, "My Clone", "https://open.spotify.com/artist/2F9WG9ugzneeesOZfmQ18V",
            "https://i.scdn.co/image/ab6761610000e5eb838ec4e15914cfe0dd427f38", 2463, 85)
    );

    public static List<SpotifyArtist> ls_artists2 = Arrays.asList(
        new SpotifyArtist("3", ls_genres, "My Clone", "https://open.spotify.com/artist/2F9WG9ugzneeesOZfmQ18V",
            "https://i.scdn.co/image/ab6761610000e5eb838ec4e15914cfe0dd427f38", 2463, 85)
    );

    public static List<SpotifyWrappedSummary> ls_summaries = new ArrayList<>(Arrays.asList(
            new SpotifyWrappedSummary(0, "Jim123", "My Spotify Wrap 2023", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks, ls_tracks2, ls_artists, ls_artists2, ls_genres,
            LocalDateTime.of(2025, Month.JANUARY, 1, 0, 0),
            LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)),
            new SpotifyWrappedSummary(1, "Jim123", "Spotify Wrap but bad!!", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks2, ls_tracks2, ls_artists2, ls_artists, ls_genres,
            LocalDateTime.of(2021, Month.JANUARY, 1, 0, 0),
            LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)),
            new SpotifyWrappedSummary(2, "Jim123", "I love burger king", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks, ls_tracks, ls_artists, ls_artists2, ls_genres,
            LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0),
            LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0))
    ));

    public static List<SpotifyWrappedSummary> ls_friendSummaries = new ArrayList<>(Arrays.asList(
            new SpotifyWrappedSummary(0, "Jim123", "Spotify Wrap but great!!", LocalDateTime.now(),
                    new ArrayList<String>(), ls_tracks2, ls_tracks2, ls_artists, ls_artists2, ls_genres,
                    LocalDateTime.of(2022, Month.FEBRUARY, 1, 0, 0),
                    LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0))
    ));
    // --------------------------------------------------------------

    //Widgets
    private RecyclerView spotifyWrappedList, friendsSpotifyWrappedList;
    private Button addSpotifyWrappedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_wrapped_list);

        userSpotifyWrappedSummaries = new ArrayList<>();
        userSpotifyWrappedSummaries.addAll(ls_summaries);
        friendSpotifyWrappedSummaries = new ArrayList<>();
        friendSpotifyWrappedSummaries.addAll(ls_friendSummaries);

        initUserSummaries();
        initWidgets();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        spotifyWrappedList.setLayoutManager(llm);
        spotifyWrappedList.setAdapter(new SpotifyWrappedListAdapter(userSpotifyWrappedSummaries));

        if(ls_friendSummaries.size() == 0){
            findViewById(R.id.spotifyWrappedListDividerLine).setVisibility(View.GONE);
            findViewById(R.id.yourFriendsAlsoMadeText).setVisibility(View.GONE);
        }

        LinearLayoutManager fllm = new LinearLayoutManager(this);
        fllm.setOrientation(LinearLayoutManager.VERTICAL);
        friendsSpotifyWrappedList.setLayoutManager(fllm);
        friendsSpotifyWrappedList.setAdapter(new SpotifyWrappedListAdapter(friendSpotifyWrappedSummaries));

        addSpotifyWrappedButton.setOnClickListener(v -> {
            ls_summaries.add(SpotifyAPIManager.generateSpotifyWrapped(
                    "My New Spotify Wrapped Wow", "medium_term", new ArrayList<>()
            ));
            spotifyWrappedList.getAdapter().notifyItemInserted(ls_summaries.size()-1);
        });
    }

    public void initUserSummaries(){
        this.userSpotifyWrappedSummaries = ls_summaries;
    }

    public void initWidgets(){
        spotifyWrappedList = findViewById(R.id.spotifyWrappedList);
        friendsSpotifyWrappedList = findViewById(R.id.friendsSpotifyWrappedList);
        addSpotifyWrappedButton = findViewById(R.id.addSpotifyWrappedButton);
        NavbarClass.initializeNavbar(this);
    }
}