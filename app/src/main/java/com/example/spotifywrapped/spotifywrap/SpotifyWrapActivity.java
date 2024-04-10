package com.example.spotifywrapped.spotifywrap;

import static com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity.ls_summaries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.accountscreen.CreateAccountActivity;
import com.example.spotifywrapped.accountscreen.LoginActivity;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedCreation;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;
import com.google.android.material.button.MaterialButton;

public class SpotifyWrapActivity extends FragmentActivity {
    private SpotifyWrappedSummary mSummary;
    private MaterialButton exitButton;

    public SpotifyWrapActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_wrap);

        //Retrieve Id of Selected Spotify Wrap
        Bundle b = getIntent().getExtras();
        if(b != null) {
            int wrapId = b.getInt("spotifyWrapId");

            //Query database to get Spotify Wrap Information
            mSummary = ls_summaries.get(wrapId); //temporary
        }

        ViewPager2 pager = findViewById(R.id.viewPager);
        pager.setAdapter(new SpotifyWrapAdapter(this, mSummary));

        exitButton = findViewById(R.id.sw_exitSpotifyWrapButton);
        exitButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setMessage("Go back to Spotify Wrap List?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        Intent myIntent = new Intent(this, SpotifyWrappedListActivity.class);
                        this.startActivity(myIntent);
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();
        });
    }
}