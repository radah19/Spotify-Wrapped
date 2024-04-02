package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyArtist;

import java.util.List;

public class SWPagerArtistRecommendationsFragment extends Fragment {
    private List<SpotifyArtist> ls_artists;
    private RecyclerView artistList;

    public SWPagerArtistRecommendationsFragment(List<SpotifyArtist> ls_artists) {
        this.ls_artists = ls_artists;
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

        return view;
    }
}