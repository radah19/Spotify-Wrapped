package com.example.spotifywrapped.spotifywrap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerGenresFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerIntroductionFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerTopArtistFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerTopArtistsFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerTopTrackFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerTracksFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerConclusionFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerTrackRecommendationsFragment;

import java.util.Arrays;
import java.util.List;

public class SpotifyWrapAdapter extends FragmentStateAdapter {
    private SpotifyWrappedSummary mSummary;

    public List<String> myFragments = Arrays.asList(new String[]{
            "Intro Fragment", "Top Track Fragment", "Top Tracks Fragment", "Top Artist Fragment",
            "Top Artists Fragment", "Top Genres Fragment", "Recommended Tracks", "Conclusion"
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
                return new SWPagerIntroductionFragment(mSummary.startTime, mSummary.endTime);

            //Top Track
            case 1:
                return new SWPagerTopTrackFragment(
                        mSummary.topTracks.get(0).getTrackImageLink(),
                        mSummary.topTracks.get(0).getTrackName(),
                        mSummary.topTracks.get(0).getTrackArtist()
                        );

            //Top 10 Tracks
            case 2:
                return new SWPagerTracksFragment(mSummary.topTracks);

            //Top Artist
            case 3:
                return new SWPagerTopArtistFragment(
                        mSummary.topArtists.get(0).getArtistImageLink(),
                        mSummary.topArtists.get(0).getArtistName()
                        );

            //Top 10 Artists
            case 4:
                return new SWPagerTopArtistsFragment(mSummary.topArtists);

            //Top Genres
            case 5:
                return new SWPagerGenresFragment(mSummary.topGenres);

            //Recommended Tracks
            case 6:
                return new SWPagerTrackRecommendationsFragment(mSummary.trackRecommendations);

            //Conclusion
            case 7:
                return new SWPagerConclusionFragment(mSummary.startTime, mSummary.endTime);

            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return myFragments.size();
    }
}
