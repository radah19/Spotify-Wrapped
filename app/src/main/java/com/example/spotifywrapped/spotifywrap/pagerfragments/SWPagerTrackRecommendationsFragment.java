package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyTrack;

import java.util.List;

public class SWPagerTrackRecommendationsFragment extends Fragment {
    private List<SpotifyTrack> ls_r_tracks;
    private RecyclerView rTracksList;
    private String isHoliday;

    public SWPagerTrackRecommendationsFragment(List<SpotifyTrack> ls_r_tracks, String isHoliday) {
        this.ls_r_tracks = ls_r_tracks;
        this.isHoliday = isHoliday;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sw_pager_track_recommendations, container, false);

        rTracksList = view.findViewById(R.id.spotifyWrapRecomendedTracksList);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rTracksList.setLayoutManager(llm);

        rTracksList.setAdapter(new SWPagerTrackRecommendationsAdapter(this.ls_r_tracks, getContext()));

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