package com.example.spotifywrapped.spotifywrappedlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrapped.DatabaseManager;
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
        setContentView(R.layout.activity_spotify_wrapped_creation);

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
            if(title.getText().length() > 0){
            String timeRange = timeRangeDropDown.getText().toString();
            String actualTimeRange;

            if (timeRange.equals("1 Year")) {
                actualTimeRange = "long_term";
            } else if (timeRange.equals("1 Month")) {
                actualTimeRange = "short_term";
            } else {
                actualTimeRange = "medium_term";
            }

            SpotifyAPIManager apiManager = SpotifyAPIManager.getInstance();
            if (apiManager != null) {
                SpotifyWrappedSummary newSummary = apiManager.generateSpotifyWrapped(title.getText().toString(), actualTimeRange, friendsList);

                if(newSummary != null) {
                    DatabaseManager.addSpotifyWrapped(newSummary);
                    SpotifyWrappedListActivity.ls_summaries.add(newSummary);
                    //spotifyWrappedList.getAdapter().notifyItemInserted(SpotifyWrappedListActivity.ls_summaries.size() - 1);
                    Toast.makeText(this, "Spotify Wrap Successfully Generated", Toast.LENGTH_SHORT).show();

                    Intent generateIntent = new Intent(this, SpotifyWrappedListActivity.class);
                    startActivity(generateIntent);
                    } else {
                        Toast.makeText(this, "Spotify Wrap Generation failed...\nPlease try again later.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "SpotifyAPIManager not initialized.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please Enter a Title!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

