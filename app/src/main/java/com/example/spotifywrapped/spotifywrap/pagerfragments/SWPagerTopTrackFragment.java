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
    private boolean isHoliday;

    String topTrackImgUrl, topTrackName, topTrackArtistName;

    public SWPagerTopTrackFragment(String topTrackImgUrl, String topTrackName, String topTrackArtistName, boolean isHoliday) {
        this.topTrackImgUrl = topTrackImgUrl;
        this.topTrackName = topTrackName;
        this.topTrackArtistName = topTrackArtistName;
        this.isHoliday = isHoliday;
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


        if (isHoliday) {
            int greenColor = getResources().getColor(R.color.green, null);
            view.setBackgroundColor(greenColor);
            topTrackImg.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.red)));
            topTrackArtistText.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            topTrackNameText.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

        // Inflate the layout for this fragment
        return view;
    }
}