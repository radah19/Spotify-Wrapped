package com.example.spotifywrapped.spotifywrap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spotifywrapped.SpotifyArtist;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerGenresFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerIntroductionFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerTopArtistFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerTopArtistsFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerTopTrackFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerTracksFragment;

import java.util.Arrays;
import java.util.List;

public class SpotifyWrapAdapter extends FragmentStateAdapter {
    private SpotifyWrappedSummary mSummary;

    public List<String> myFragments = Arrays.asList(new String[]{
            "Intro Fragment", "Top Track Fragment", "Top Tracks Fragment", "Top Artist Fragment",
            "Top Artists Fragment", "Top Genres Fragment"
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

            //Top Track
            case 1:
                return new PagerTopTrackFragment(
                        mSummary.topTracks.get(0).getTrackImageLink(),
                        mSummary.topTracks.get(0).getTrackName(),
                        mSummary.topTracks.get(0).getTrackArtist()
                        );

            //Top 10 Tracks
            case 2:
                return new PagerTracksFragment(mSummary.topTracks);

            //Top Artist
            case 3:
                return new PagerTopArtistFragment(
                        mSummary.topArtists.get(0).getArtistImageLink(),
                        mSummary.topArtists.get(0).getArtistName()
                        );

            //Top 10 Artists
            case 4:
                return new PagerTopArtistsFragment(mSummary.topArtists);

            //Top Genres
            case 5:
                return new PagerGenresFragment(mSummary.topGenres);

            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return myFragments.size();
    }
}
