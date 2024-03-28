package com.example.spotifywrapped.spotifywrap;

import static com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity.ls_summaries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyWrappedSummary;

public class SpotifyWrapActivity extends FragmentActivity {
    private SpotifyWrappedSummary mSummary;

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
    }
}