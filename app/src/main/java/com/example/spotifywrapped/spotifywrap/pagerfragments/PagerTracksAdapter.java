package com.example.spotifywrapped.spotifywrap.pagerfragments;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.SpotifyTrack;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListAdapter;
import com.squareup.picasso.Picasso;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PagerTracksAdapter extends RecyclerView.Adapter<PagerTracksAdapter.MyViewHolder> {
    private List<SpotifyTrack> ls_tracks;
    private Context context;

    public PagerTracksAdapter(List<SpotifyTrack> ls_tracks, Context context) {
        this.ls_tracks = ls_tracks;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spotify_wrap_pager_tracks_item,parent,false);
        PagerTracksAdapter.MyViewHolder holder = new PagerTracksAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    ls_tracks.get(position).getTrackLink()
            ));
             context.startActivity(browserIntent);
        });

        Picasso.get().load(ls_tracks.get(position).getTrackImageLink()).into(holder.sw_tracks_img);

        holder.sw_tracks_trackTitle.setText(ls_tracks.get(position).getTrackName());
        holder.sw_tracks_trackArtist.setText(ls_tracks.get(position).getTrackArtist());
        holder.sw_tracks_trackRelease.setText(
                ls_tracks.get(position).getTrackPublishDate().format(
                        DateTimeFormatter.ofPattern("MMMM dd, yyyy")
                ).toString()
        );

        holder.sw_tracks_popularity.setText(String.valueOf(ls_tracks.get(position).getTrackPopularity()));

        LocalTime len = ls_tracks.get(position).getTrackLen();

        if(len.isAfter( LocalTime.of(0,59,59) )) {
            holder.sw_tracks_trackRelease.setText(
                    len.format(DateTimeFormatter.ofPattern("H:mm:ss")).toString()
            );
        } else {
            holder.sw_tracks_trackRelease.setText(
                    len.format(DateTimeFormatter.ofPattern("m:ss")).toString()
            );
        }

    }

    @Override
    public int getItemCount() {
        return this.ls_tracks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sw_tracks_img;
        TextView sw_tracks_trackTitle, sw_tracks_trackArtist, sw_tracks_trackRelease;
        TextView sw_tracks_popularity, sw_tracks_duration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sw_tracks_img = itemView.findViewById(R.id.sw_tracks_img);
            sw_tracks_trackTitle = itemView.findViewById(R.id.sw_tracks_trackTitle);
            sw_tracks_trackArtist = itemView.findViewById(R.id.sw_tracks_trackArtist);
            sw_tracks_trackRelease = itemView.findViewById(R.id.sw_tracks_trackRelease);

            sw_tracks_popularity = itemView.findViewById(R.id.sw_tracks_popularity);
            sw_tracks_duration = itemView.findViewById(R.id.sw_tracks_duration);
        }
    } // MyViewHolder
}
