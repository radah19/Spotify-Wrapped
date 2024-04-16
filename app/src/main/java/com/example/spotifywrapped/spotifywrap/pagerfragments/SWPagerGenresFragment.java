package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

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

    public SWPagerGenresFragment(List<String> ls_genres) {
        this.ls_genres = ls_genres;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s1 = new StringBuilder("");
        s2 = new StringBuilder("");
        s3 = new StringBuilder("");

        for(int i = 0; i < ls_genres.size(); i += 2) {
            String spacing = (ls_genres.get(i).length() >= 12 || ls_genres.get(i + 1).length() >= 12) ? "\n\n" : "\n\n\n";
            if (i + 1 != ls_genres.size()) {
                s1.append(ls_genres.get(i) + spacing);
                s2.append(ls_genres.get(i + 1) + spacing);
            } else {
                s3.append(ls_genres.get(i) + spacing);
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

        return view;
    }
}