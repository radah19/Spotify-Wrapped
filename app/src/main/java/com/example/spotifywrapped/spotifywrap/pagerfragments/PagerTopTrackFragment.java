package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotifywrapped.R;
import com.squareup.picasso.Picasso;

public class PagerTopTrackFragment extends Fragment {
    private ImageView topTrackImg;
    private TextView topTrackNameText, topTrackArtistText;

    String topTrackImgUrl, topTrackName, topTrackArtistName;

    public PagerTopTrackFragment(String topTrackImgUrl, String topTrackName, String topTrackArtistName) {
        this.topTrackImgUrl = topTrackImgUrl;
        this.topTrackName = topTrackName;
        this.topTrackArtistName = topTrackArtistName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_top_track, container, false);

        topTrackNameText = view.findViewById(R.id.sw_top_track_trackName);
        topTrackArtistText = view.findViewById(R.id.sw_top_track_trackArtistName);
        topTrackImg = view.findViewById(R.id.sw_top_track_img);

        Picasso.get().load(topTrackImgUrl).into(topTrackImg);
        topTrackNameText.setText(topTrackName);
        topTrackArtistText.setText(topTrackArtistName);

        // Inflate the layout for this fragment
        return view;
    }
}