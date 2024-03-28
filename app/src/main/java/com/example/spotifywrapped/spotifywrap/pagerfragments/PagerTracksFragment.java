package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerTracksFragment extends Fragment {
    private List<SpotifyTrack> ls_tracks;
    private RecyclerView trackList;

    public PagerTracksFragment(List<SpotifyTrack> ls_tracks) {
        this.ls_tracks = ls_tracks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_tracks, container, false);

        trackList = view.findViewById(R.id.spotifyWrapTopTracksList);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        trackList.setLayoutManager(llm);

        trackList.setAdapter(new PagerTracksAdapter(this.ls_tracks, getContext()));

        return view;
    }
}