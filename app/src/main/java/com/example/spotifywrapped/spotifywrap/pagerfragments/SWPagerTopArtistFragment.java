package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.ColorStateList;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotifywrapped.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class SWPagerTopArtistFragment extends Fragment {
    private ImageView topArtistImg;
    private TextView topArtistText;
    private String isHoliday;

    private String topArtistImgUrl, topArtistName, topArtistSpotifyUrl;

    public SWPagerTopArtistFragment(String topArtistImgUrl, String topArtistName, String topArtistSpotifyUrl, String isHoliday) {
        this.topArtistImgUrl = topArtistImgUrl;
        this.topArtistName = topArtistName;
        this.topArtistSpotifyUrl = topArtistSpotifyUrl;
        this.isHoliday = isHoliday;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sw_pager_top_artist, container, false);

        topArtistText = view.findViewById(R.id.sw_top_artist_artistName);
        topArtistImg = view.findViewById(R.id.sw_top_artist_img);
        topArtistImg.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    topArtistSpotifyUrl
            ));
            this.startActivity(browserIntent);
        });

        Picasso.get().load(topArtistImgUrl).into(topArtistImg);
        topArtistText.setText(topArtistName);

        // Inflate the layout for this fragment
        ImageView artistImage = view.findViewById(R.id.sw_top_artist_img);
        TextView artistName = view.findViewById(R.id.sw_top_artist_artistName);
        if ("Christmas".equals(isHoliday)) {
            ImageView reindeer = view.findViewById(R.id.imageViewReindeer);
            reindeer.setVisibility(View.VISIBLE);
            ImageView garland = view.findViewById(R.id.garlandImage);
            garland.setVisibility(View.VISIBLE);
            int greenColor = getResources().getColor(R.color.green, null);
            view.setBackgroundColor(greenColor);
            artistImage.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.red)));
            artistName.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }
        if("Halloween".equals(isHoliday)) {
            int blackColor = getResources().getColor(R.color.black, null);
            view.setBackgroundColor(blackColor);
            artistImage.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.orange)));
            artistName.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
            ImageView pumpkin = view.findViewById(R.id.pumpkin);
            pumpkin.setVisibility(View.VISIBLE);
        }
        return view;
    }
}