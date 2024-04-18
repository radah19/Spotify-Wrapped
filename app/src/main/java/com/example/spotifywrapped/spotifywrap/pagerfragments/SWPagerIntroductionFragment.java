package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spotifywrapped.R;
import androidx.core.content.ContextCompat;


import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SWPagerIntroductionFragment extends Fragment {
    private LocalDateTime startTime, endTime;
    private String isHoliday;

    public SWPagerIntroductionFragment(LocalDateTime startTime, LocalDateTime endTime, String isHoliday) {
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
        View view = inflater.inflate(R.layout.fragment_sw_pager_introduction, container, false);

        TextView fromToDate = view.findViewById(R.id.fromToDates);

        StringBuilder str = new StringBuilder("From ");
        str.append(startTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());
        str.append(" to ");
        str.append(endTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());

        fromToDate.setText(str);

        TextView date = view.findViewById(R.id.fromToDates);
        if("Christmas".equals(isHoliday)) {
            view.setBackgroundResource(R.drawable.holiday_theme);
            int greenColor = ContextCompat.getColor(getContext(), R.color.green);
            date.setTextColor(greenColor);
        }
        if("Halloween".equals(isHoliday)) {
            view.setBackgroundResource(R.drawable.halloween_background);
        }

        return view;
    }
}