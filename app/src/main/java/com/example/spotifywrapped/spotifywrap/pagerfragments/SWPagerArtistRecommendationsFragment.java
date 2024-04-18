package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyArtist;
import android.widget.ImageView;

import java.util.List;

public class SWPagerArtistRecommendationsFragment extends Fragment {
    private List<SpotifyArtist> ls_artists;
    private RecyclerView artistList;
    private String isHoliday;

    public SWPagerArtistRecommendationsFragment(List<SpotifyArtist> ls_artists, String isHoliday) {
        this.ls_artists = ls_artists;
        this.isHoliday = isHoliday;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sw_pager_artist_recommendations, container, false);

        artistList = view.findViewById(R.id.spotifyWrapTopArtistsList);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        artistList.setLayoutManager(llm);

        artistList.setAdapter(new SWPagerArtistRecommendationsAdapter(this.ls_artists, getContext()));

        if ("Christmas".equals(isHoliday)) {
            ImageView lights = view.findViewById(R.id.lightsImageView);
            lights.setVisibility(View.VISIBLE);
        }
        if("Halloween".equals(isHoliday)) {
            ImageView halloween_garland = view.findViewById(R.id.halloween_garland);
            halloween_garland.setVisibility(View.VISIBLE);
        }

        return view;
    }
}