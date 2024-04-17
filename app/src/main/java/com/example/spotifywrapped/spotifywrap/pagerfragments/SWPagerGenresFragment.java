package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spotifywrapped.R;

import java.util.List;

public class SWPagerGenresFragment extends Fragment {
    private TextView sw_s1, sw_s2, sw_s3;
    private StringBuilder s1, s2, s3;
    private List<String> ls_genres;
    private String isChristmasHoliday;
    private String isHalloween;

    public SWPagerGenresFragment(List<String> ls_genres, String isChristmasHoliday, String isHalloween) {
        this.ls_genres = ls_genres;
        this.isChristmasHoliday = isChristmasHoliday;
        this.isHalloween = isHalloween;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s1 = new StringBuilder("");
        s2 = new StringBuilder("");
        s3 = new StringBuilder("");

        for(int i = 0; i < ls_genres.size(); i++) {
            String spacing = (ls_genres.get(i).length() >= 12) ? "\n\n" : "\n\n\n";
            if (i % 2 == 0) {
                if (i == ls_genres.size() - 1) {
                    s3.append(ls_genres.get(i) + spacing);
                } else {
                    s1.append(ls_genres.get(i) + spacing);
                }
            } else {
                s2.append(ls_genres.get(i) + spacing);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sw_pager_genres, container, false);

        sw_s1 = view.findViewById(R.id.sw_top_genres_s1);
        sw_s2 = view.findViewById(R.id.sw_top_genres_s2);
        sw_s3 = view.findViewById(R.id.sw_top_genres_s3);

        sw_s1.setText(s1);
        sw_s2.setText(s2);
        sw_s3.setText(s3);

        if("isChristmas".equals(isChristmasHoliday)) {
            view.setBackgroundResource(R.drawable.holiday_theme_2);
            int redColor = ContextCompat.getColor(getContext(), R.color.red);
            sw_s1.setTextColor(redColor);
            sw_s2.setTextColor(redColor);
            sw_s3.setTextColor(redColor);
        }
        if("isHalloween".equals(isHalloween)) {
            view.setBackgroundResource(R.drawable.halloween_background_2);
            int orangeColor = ContextCompat.getColor(getContext(), R.color.orange);
            sw_s1.setTextColor(orangeColor);
            sw_s2.setTextColor(orangeColor);
            sw_s3.setTextColor(orangeColor);
        }

        return view;

    }
}