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
    public List<SpotifyWrappedSummary> userSpotifyWrappedSummaries;
    public static List<SpotifyWrappedSummary> ls_summaries;
    // --------------------------------------------------------------

    //Widgets
    public RecyclerView spotifyWrappedList;
    private Button addSpotifyWrappedButton, christmasAddSpotifyWrappedButton, halloweenAddSpotifyWrappedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_wrapped_list);

        userSpotifyWrappedSummaries = new ArrayList<>();
        if(ls_summaries != null) {
            userSpotifyWrappedSummaries.addAll(ls_summaries);
        }

        initUserSummaries();
        initWidgets();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        spotifyWrappedList.setLayoutManager(llm);
        spotifyWrappedList.setAdapter(new SpotifyWrappedListAdapter(userSpotifyWrappedSummaries, this));

        addSpotifyWrappedButton.setOnClickListener(v -> {
            Intent generateIntent = new Intent(this, SpotifyWrappedCreation.class);
            startActivity(generateIntent);
        });

        if(LocalDateTime.now().getMonth() == Month.DECEMBER) {
            christmasAddSpotifyWrappedButton.setVisibility(View.VISIBLE);
            christmasAddSpotifyWrappedButton.setOnClickListener(v -> {
                Intent generateIntent = new Intent(this, SpotifyWrappedCreationChristmasActivity.class);
                startActivity(generateIntent);
            });
        }

        if(LocalDateTime.now().getMonth() == Month.OCTOBER) {
            halloweenAddSpotifyWrappedButton.setVisibility(View.VISIBLE);
            halloweenAddSpotifyWrappedButton.setOnClickListener(v -> {
                Intent generateIntent = new Intent(this, SpotifyWrappedCreationHalloweenActivity.class);
                startActivity(generateIntent);
            });
        }
    }

    public void initUserSummaries(){
        this.userSpotifyWrappedSummaries = ls_summaries;
    }

    public void initWidgets(){
        spotifyWrappedList = findViewById(R.id.spotifyWrappedList);
        addSpotifyWrappedButton = findViewById(R.id.addSpotifyWrappedButton);
        christmasAddSpotifyWrappedButton = findViewById(R.id.christmasAddSpotifyWrappedButton);
        halloweenAddSpotifyWrappedButton = findViewById(R.id.halloweenAddSpotifyWrappedButton);
        NavbarClass.initializeNavbar(this);
    }
}