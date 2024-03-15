package com.example.spotifywrapped.spotifywrap;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerIntroductionFragment;
import com.example.spotifywrapped.spotifywrap.pagerfragments.PagerTopTrackAndArtistFragment;

import java.util.List;

public class SpotifyWrapAdapter extends FragmentStateAdapter {
    public List<String> topArtistIds, topTrackIds, topGenres;
    public List<String> topFriendArtistIds, topFriendTrackIds;

    public List<Fragment> myFragments;

    public SpotifyWrapAdapter(@NonNull Fragment fragment, List<Fragment> myFragments, List<String> topArtistIds, List<String> topTrackIds, List<String> topGenres, List<String> topFriendArtistIds, List<String> topFriendTrackIds) {
        super(fragment);
        this.myFragments = myFragments;

        this.topArtistIds = topArtistIds;
        this.topTrackIds = topTrackIds;
        this.topGenres = topGenres;
        this.topFriendArtistIds = topFriendArtistIds;
        this.topFriendTrackIds = topFriendTrackIds;
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
                return new PagerTopTrackAndArtistFragment();

            //Top 5 Artists

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
