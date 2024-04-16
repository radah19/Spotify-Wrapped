package com.example.spotifywrapped.spotifywrap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.spotifywrap.pagerfragments.SWPagerArtistRecommendationsFragment;
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
            "Top Artists Fragment", "Top Genres Fragment", "Recommended Tracks", "Recommended Artists", "Conclusion"
    });

    public SpotifyWrapAdapter(@NonNull FragmentActivity fragmentActivity, SpotifyWrappedSummary mSummary) {
        super(fragmentActivity);
        this.mSummary = mSummary;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        boolean isHoliday = mSummary.isHoliday();

        switch(position) {
            //Introduction Screen
            case 0:
                return new SWPagerIntroductionFragment(mSummary.startTime, mSummary.endTime, isHoliday);

            //Top Track
            case 1:
                return new SWPagerTopTrackFragment(
                        mSummary.topTracks.get(0).getTrackImageLink(),
                        mSummary.topTracks.get(0).getTrackName(),
                        mSummary.topTracks.get(0).getTrackArtist(),
                        isHoliday
                        );

            //Top 10 Tracks
            case 2:
                return new SWPagerTracksFragment(mSummary.topTracks, isHoliday);

            //Top Artist
            case 3:
                return new SWPagerTopArtistFragment(
                        mSummary.topArtists.get(0).getArtistImageLink(),
                        mSummary.topArtists.get(0).getArtistName(),
                        isHoliday
                        );

            //Top 10 Artists
            case 4:
                return new SWPagerTopArtistsFragment(mSummary.topArtists, isHoliday);

            //Top Genres
            case 5:
                return new SWPagerGenresFragment(mSummary.topGenres, isHoliday);

            //Recommended Tracks
            case 6:
                return new SWPagerTrackRecommendationsFragment(mSummary.trackRecommendations, isHoliday);

            case 7:
                return new SWPagerArtistRecommendationsFragment(mSummary.artistRecommendations, isHoliday);

            //Conclusion
            case 8:
                return new SWPagerConclusionFragment(mSummary.startTime, mSummary.endTime, isHoliday);

            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return myFragments.size();
    }
}
