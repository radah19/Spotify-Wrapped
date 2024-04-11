package com.example.spotifywrapped.spotifywrappedlist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyAPIManager;
import com.example.spotifywrapped.SpotifyWrappedSummary;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyWrappedCreation extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spotify_wrapped_creation);

        AutoCompleteTextView timeRangeDropDown = findViewById(R.id.time_range);
        EditText title = findViewById(R.id.spotify_wrapped_title);
        String[] timeRanges = {"1 Year", "6 Months", "1 Month"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, timeRanges);
        timeRangeDropDown.setAdapter(adapter);

        timeRangeDropDown.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) timeRangeDropDown.showDropDown();
        });
        timeRangeDropDown.setOnClickListener(v -> timeRangeDropDown.showDropDown());

        RecyclerView friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] friendsArray = {"Friend 1", "Friend 2", "Friend 3", "Friend 4", "Friend 5", "Friend 6", "Friend 7", "Friend 8", "Friend 9", "Friend 10"};
        List<String> friendsList = new ArrayList<>(Arrays.asList(friendsArray));
        FriendsAdapter adapter2 = new FriendsAdapter(friendsList);
        friendsRecyclerView.setAdapter(adapter2);

        Button createButton = findViewById(R.id.create_spotify_wrapped_button);
        createButton.setOnClickListener(v -> {
            String timeRange = timeRangeDropDown.getText().toString();
            String actualTimeRange;
            if (timeRange.equals("1 Year")) {
                actualTimeRange = "long_term";
            } else if (timeRange.equals("6 Months")) {
                actualTimeRange = "medium_term";
            } else {
                actualTimeRange = "short_term";
            }

            SpotifyAPIManager apiManager = SpotifyAPIManager.getInstance();
            if (apiManager != null) {
                SpotifyWrappedSummary summary = apiManager.generateSpotifyWrapped(title.getText().toString(), actualTimeRange, friendsList);

                if (summary != null) {
                    Toast.makeText(this, "Spotify Wrapped Created!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to create Spotify Wrapped.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "SpotifyAPIManager not initialized.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

