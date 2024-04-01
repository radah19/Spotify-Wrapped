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

public class PagerTopArtistFragment extends Fragment {
    ImageView topArtistImg;
    TextView topArtistText;

    String topArtistImgUrl, topArtistName;

    public PagerTopArtistFragment(String topArtistImgUrl, String topArtistName) {
        this.topArtistImgUrl = topArtistImgUrl;
        this.topArtistName = topArtistName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_top_artist, container, false);

        topArtistText = view.findViewById(R.id.sw_top_artist_artistName);
        topArtistImg = view.findViewById(R.id.sw_top_artist_img);

        Picasso.get().load(topArtistImgUrl).into(topArtistImg);
        topArtistText.setText(topArtistName);

        // Inflate the layout for this fragment
        return view;
    }
}