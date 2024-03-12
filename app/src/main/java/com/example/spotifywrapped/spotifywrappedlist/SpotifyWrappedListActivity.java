package com.example.spotifywrapped.spotifywrappedlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.SpotifyWrappedSummary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyWrappedListActivity extends AppCompatActivity {
    public List<SpotifyWrappedSummary> userSpotifyWrappedSummaries;
    public String orderBy;

    // -------------------- Testing Variables ----------------------
    public static List<String> ls_genres = Arrays.asList("Rock", "Metal");
    public static List<SpotifyTrack> ls_tracks = Arrays.asList(
            new SpotifyTrack(ls_genres, "Markle", "The Markler", "https://zombo.com", 4035, LocalDateTime.now(),
                    "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228")
    );
    public static List<SpotifyWrappedSummary> ls_summaries = Arrays.asList(
            new SpotifyWrappedSummary("Jim123", new ArrayList<String>(), ls_tracks, "sfgdfgdj", LocalDateTime.now())
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
    }
}