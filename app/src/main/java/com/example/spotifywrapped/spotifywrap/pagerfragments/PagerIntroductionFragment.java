package com.example.spotifywrapped.spotifywrap.pagerfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spotifywrapped.R;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PagerIntroductionFragment extends Fragment {
    private LocalDateTime startTime, endTime;

    public PagerIntroductionFragment(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_introduction, container, false);

        TextView fromToDate = view.findViewById(R.id.fromToDates);

        StringBuilder str = new StringBuilder("From ");
        str.append(startTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());
        str.append(" to ");
        str.append(endTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());

        fromToDate.setText(str);

        return view;
    }
}