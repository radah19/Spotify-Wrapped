package com.example.spotifywrapped.spotifywrap;

import static com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity.ls_summaries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView currentSong;

    public SpotifyWrapActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_wrap);

        //Retrieve Id of Selected Spotify Wrap
        Bundle b = getIntent().getExtras();
        if(b != null) {
            String wrapId = b.getString("spotifyWrapId");

            //Query database to get Spotify Wrap Information
            for(SpotifyWrappedSummary s: ls_summaries){
                if(s.getId().equals(wrapId)){
                    mSummary = s;
                    break;
                }
            }
        }

        ViewPager2 pager = findViewById(R.id.viewPager);
        pager.setAdapter(new SpotifyWrapAdapter(this, mSummary, exitButton, currentSong));

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