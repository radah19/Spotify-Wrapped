package com.example.spotifywrapped.spotifywrappedlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyAPIManager;
import com.example.spotifywrapped.SpotifyWrappedSummary;

public class SpotifyWrappedCreationHalloweenActivity extends AppCompatActivity {
    private boolean activated;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_wrapped_creation_halloween);

        AutoCompleteTextView timeRangeDropDown = findViewById(R.id.time_range);
        EditText title = findViewById(R.id.spotify_wrapped_title);
        String[] timeRanges = {"1 Year", "6 Months", "1 Month"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, timeRanges);
        timeRangeDropDown.setAdapter(adapter);

        timeRangeDropDown.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) timeRangeDropDown.showDropDown();
        });
        timeRangeDropDown.setOnClickListener(v -> timeRangeDropDown.showDropDown());

        //RecyclerView friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        //friendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, SpotifyWrappedListActivity.class);
            this.startActivity(myIntent);
        });

        activated = false;
        Button createButton = findViewById(R.id.create_spotify_wrapped_button);
        createButton.setOnClickListener(v -> {
            if(!activated) {
                if (title.getText().length() > 0) {
                    activated = true;
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
                        SpotifyWrappedSummary newSummary = apiManager.generateSpotifyWrapped(title.getText().toString(), actualTimeRange, "Halloween");

                        if (newSummary != null) {
                            DatabaseManager.addSpotifyWrapped(newSummary);
                            SpotifyWrappedListActivity.ls_summaries.add(newSummary);
                            //spotifyWrappedList.getAdapter().notifyItemInserted(SpotifyWrappedListActivity.ls_summaries.size() - 1);
                            Toast.makeText(this, "Your Spotify Wrap was Successfully Generated!", Toast.LENGTH_SHORT).show();

                            Intent generateIntent = new Intent(this, SpotifyWrappedListActivity.class);
                            startActivity(generateIntent);
                        } else {
                            Toast.makeText(this, "Spotify Wrap Generation failed...\nPlease try again later.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "SpotifyAPIManager not initialized...\nPlease try again later.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please Enter a Title!", Toast.LENGTH_SHORT).show();
                }
            }});

    }
}