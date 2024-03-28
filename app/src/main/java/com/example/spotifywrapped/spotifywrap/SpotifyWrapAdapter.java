package com.example.spotifywrapped.spotifywrap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spotifywrapped.SpotifyArtist;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerIntroductionFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerTopTrackFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerTracksFragment;

import java.util.Arrays;
import java.util.List;

public class SpotifyWrapAdapter extends FragmentStateAdapter {
    private SpotifyWrappedSummary mSummary;

    public List<String> myFragments = Arrays.asList(new String[]{
            "Intro Fragment", "Top Track Fragment", "Top Tracks Fragment"
    });

    public SpotifyWrapAdapter(@NonNull FragmentActivity fragmentActivity, SpotifyWrappedSummary mSummary) {
        super(fragmentActivity);
        this.mSummary = mSummary;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            //Introduction Screen
            case 0:
                return new PagerIntroductionFragment();

            //Top Artist and Top Track Screen
            case 1:
                return new PagerTopTrackFragment(
                        mSummary.topTracks.get(0).getTrackImageLink(),
                        mSummary.topTracks.get(0).getTrackName(),
                        mSummary.topTracks.get(0).getTrackArtist()
                        );

            //Top 5 Artists
            case 2:
                return new PagerTracksFragment(mSummary.topTracks);

            //Top 5 Tracks

            //Favorite Genre List

            //Track Recommendations combined with some friend's favorite tracks

            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return myFragments.size();
    }
}
