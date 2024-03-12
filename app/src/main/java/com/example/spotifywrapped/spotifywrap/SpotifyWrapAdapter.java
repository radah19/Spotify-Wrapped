package com.example.spotifywrapped.spotifywrap;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.spotifywrapped.R;

import java.util.List;

public class SpotifyWrapAdapter extends RecyclerView.Adapter<SpotifyWrapAdapter.MyViewHolder> {
    public List<String> topArtistIds, topTrackIds, topGenres;
    public List<String> topFriendArtistIds, topFriendTrackIds;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sw_img;
        TextView sw_title;
        TextView sw_createdAtDate;
        TextView sw_trackNameList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sw_img = itemView.findViewById(R.id.sw_img);
            sw_title = itemView.findViewById(R.id.sw_title);
            sw_createdAtDate = itemView.findViewById(R.id.sw_createdAtDate);
            sw_trackNameList = itemView.findViewById(R.id.sw_trackNameList);
        }
    } // MyViewHolder

}
