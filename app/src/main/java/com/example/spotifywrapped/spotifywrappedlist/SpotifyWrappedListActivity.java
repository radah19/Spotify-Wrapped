package com.example.spotifywrapped.spotifywrappedlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyAPIManager;
import com.example.spotifywrapped.SpotifyArtist;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.navbar.NavbarClass;
import com.example.spotifywrapped.spotifywrap.SpotifyWrapActivity;

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


    public static List<SpotifyWrappedSummary> ls_summaries;
    public static List<SpotifyWrappedSummary> ls_friendSummaries = new ArrayList<>();
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

        if (ls_friendSummaries.size() == 0) {
            findViewById(R.id.spotifyWrappedListDividerLine).setVisibility(View.GONE);
            findViewById(R.id.yourFriendsAlsoMadeText).setVisibility(View.GONE);
        }

        LinearLayoutManager fllm = new LinearLayoutManager(this);
        fllm.setOrientation(LinearLayoutManager.VERTICAL);
        friendsSpotifyWrappedList.setLayoutManager(fllm);
        friendsSpotifyWrappedList.setAdapter(new SpotifyWrappedListAdapter(friendSpotifyWrappedSummaries));


        addSpotifyWrappedButton.setOnClickListener(v -> {
            Intent generateIntent = new Intent(this, SpotifyWrappedCreation.class);
            startActivity(generateIntent);
            SpotifyWrappedSummary newSummary = SpotifyAPIManager.generateSpotifyWrapped(
                    "My New Spotify Wrapped Wow", "medium_term", new ArrayList<>()
            );
            if(newSummary != null) {
                DatabaseManager.addSpotifyWrapped(newSummary);
                ls_summaries.add(newSummary);
                spotifyWrappedList.getAdapter().notifyItemInserted(ls_summaries.size() - 1);
                Toast.makeText(this, "Spotify Wrap Successfully Generated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Spotify Wrap Generation failed...\nPlease try again later.", Toast.LENGTH_SHORT).show();
            }
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