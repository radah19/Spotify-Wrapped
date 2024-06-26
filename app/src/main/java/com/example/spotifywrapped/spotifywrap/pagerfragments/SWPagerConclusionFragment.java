package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spotifywrapped.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SWPagerConclusionFragment extends Fragment {
    private LocalDateTime startTime, endTime;
    private String isHoliday;

    public SWPagerConclusionFragment(LocalDateTime startTime, LocalDateTime endTime, String isHoliday) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isHoliday = isHoliday;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sw_pager_conclusion, container, false);

        TextView fromToDate = view.findViewById(R.id.conclusionFromToDates);

        StringBuilder str = new StringBuilder("That was your Spotify Wrap from  ");
        str.append(startTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());
        str.append(" to ");
        str.append(endTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());

        fromToDate.setText(str);

        if("Christmas".equals(isHoliday)) {
            view.setBackgroundResource(R.drawable.holiday_theme);
        }
        if("Halloween".equals(isHoliday)) {
            view.setBackgroundResource(R.drawable.halloween_background);
        }

        return view;
    }
}