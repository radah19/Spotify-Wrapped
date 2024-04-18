package com.example.spotifywrapped.spotifywrappedlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.SpotifyWrappedSummary;
import com.example.spotifywrapped.friendslist.FriendListActivity;
import com.example.spotifywrapped.spotifywrap.SpotifyWrapActivity;
import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SpotifyWrappedListAdapter extends RecyclerView.Adapter<SpotifyWrappedListAdapter.MyViewHolder>{
    public List<SpotifyWrappedSummary> spotifyWrappedSummaries;

    public SpotifyWrappedListAdapter(List<SpotifyWrappedSummary> spotifyWrappedSummaries) {
        this.spotifyWrappedSummaries = spotifyWrappedSummaries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spotify_wrapped_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<SpotifyTrack> mTracks = spotifyWrappedSummaries.get(position).topTracks;

        holder.sw_img.setImageResource(R.drawable.default_track_img);
        holder.sw_title.setText(spotifyWrappedSummaries.get(position).getTitle());

        //Generate Created At Date
        StringBuilder strDate = new StringBuilder();
        strDate.append("Created ");
        strDate.append(
                spotifyWrappedSummaries.get(position).getCreatedAt().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString()
        );
        holder.sw_createdAtDate.setText(strDate);

        //Genenerate Date Range
        StringBuilder strRange = new StringBuilder("");
        strRange.append(spotifyWrappedSummaries.get(position).startTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());
        strRange.append(" to ");
        strRange.append(spotifyWrappedSummaries.get(position).endTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")).toString());
        holder.sw_datesRange.setText(strRange);

        StringBuilder str = new StringBuilder();
        boolean foundImg = false;
        for(int i = 0; i < mTracks.size(); i++) {

            //Find & Load Track Image
            if(!foundImg && mTracks.get(i).getTrackImageLink() != null) {
                Picasso.get().load(mTracks.get(i).getTrackImageLink()).into(holder.sw_img);
                foundImg = true;
            }

            if(str.length() < 120) {
                str.append(mTracks.get(i).getTrackName());
                str.append(" • ");
                str.append(mTracks.get(i).getTrackArtist());
                str.append(", ");
            }
        }
        str.delete(str.length()-2, str.length());
        str.append("...");

        holder.sw_trackNameList.setText(str);

        //Navigate to Selected Spotify Wrap
        holder.itemView.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), SpotifyWrapActivity.class);
            myIntent.putExtra("spotifyWrapId", spotifyWrappedSummaries.get(position).getId());
            v.getContext().startActivity(myIntent);
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseManager.deleteSpotifyWrap("Spotify Wrapped");
            }
        });
    }

    @Override
    public int getItemCount() {
        return spotifyWrappedSummaries.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sw_img;
        TextView sw_title;
        TextView sw_createdAtDate;
        TextView sw_trackNameList;
        TextView sw_datesRange;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sw_img = itemView.findViewById(R.id.sw_img);
            sw_title = itemView.findViewById(R.id.sw_title);
            sw_createdAtDate = itemView.findViewById(R.id.sw_createdAtDate);
            sw_trackNameList = itemView.findViewById(R.id.sw_trackNameList);
            sw_datesRange = itemView.findViewById(R.id.sw_datesRange);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    } // MyViewHolder
}
