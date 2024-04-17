package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.content.res.ColorStateList;
import android.media.Image;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotifywrapped.R;
import com.squareup.picasso.Picasso;

public class SWPagerTopTrackFragment extends Fragment {
    private ImageView topTrackImg;
    private TextView topTrackNameText, topTrackArtistText;
    private String isChristmasHoliday;
    private String isHalloween;

    String topTrackImgUrl, topTrackName, topTrackArtistName;

    public SWPagerTopTrackFragment(String topTrackImgUrl, String topTrackName, String topTrackArtistName, String isChristmasHoliday, String isHalloween) {
        this.topTrackImgUrl = topTrackImgUrl;
        this.topTrackName = topTrackName;
        this.topTrackArtistName = topTrackArtistName;
        this.isChristmasHoliday = isChristmasHoliday;
        this.isHalloween = isHalloween;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sw_pager_top_track, container, false);

        topTrackNameText = view.findViewById(R.id.sw_top_track_trackName);
        topTrackArtistText = view.findViewById(R.id.sw_top_track_trackArtistName);
        topTrackImg = view.findViewById(R.id.sw_top_track_img);

        Picasso.get().load(topTrackImgUrl).into(topTrackImg);
        topTrackNameText.setText(topTrackName);
        topTrackArtistText.setText(topTrackArtistName);


        if ("isChristmas".equals(isChristmasHoliday)) {
            ImageView reindeer = view.findViewById(R.id.imageViewReindeer);
            reindeer.setVisibility(View.VISIBLE);
            ImageView garland = view.findViewById(R.id.garlandImage);
            garland.setVisibility(View.VISIBLE);
            int greenColor = getResources().getColor(R.color.green, null);
            view.setBackgroundColor(greenColor);
            topTrackImg.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.red)));
            topTrackArtistText.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            topTrackNameText.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }
        if("isHalloween".equals(isHalloween)) {
            int blackColor = getResources().getColor(R.color.black, null);
            view.setBackgroundColor(blackColor);
            topTrackImg.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.orange)));
            topTrackArtistText.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
            topTrackNameText.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            ImageView pumpkin = view.findViewById(R.id.pumpkin);
            pumpkin.setVisibility(View.VISIBLE);
        }

        // Inflate the layout for this fragment
        return view;
    }
}