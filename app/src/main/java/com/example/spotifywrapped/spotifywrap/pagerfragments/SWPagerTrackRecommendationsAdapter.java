package com.example.spotifywrapped.spotifywrap.pagerfragments;

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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SWPagerTrackRecommendationsAdapter extends RecyclerView.Adapter<SWPagerTrackRecommendationsAdapter.MyViewHolder> {
    private List<SpotifyTrack> ls_rTracks;
    private Context context;

    public SWPagerTrackRecommendationsAdapter(List<SpotifyTrack> ls_rTracks, Context context) {
        this.ls_rTracks = ls_rTracks;
        this.context = context;
    }

    @NonNull
    @Override
    public SWPagerTrackRecommendationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spotify_wrap_pager_rtracks_item,parent,false);
        SWPagerTrackRecommendationsAdapter.MyViewHolder holder = new SWPagerTrackRecommendationsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SWPagerTrackRecommendationsAdapter.MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    ls_rTracks.get(position).getTrackLink()
            ));
            context.startActivity(browserIntent);
        });

        Picasso.get().load(ls_rTracks.get(position).getTrackImageLink()).into(holder.sw_tracks_img);

        holder.sw_tracks_trackTitle.setText(ls_rTracks.get(position).getTrackName());
        holder.sw_tracks_trackArtist.setText(ls_rTracks.get(position).getTrackArtist());
        holder.sw_tracks_trackRelease.setText(
                ls_rTracks.get(position).getTrackPublishDate().format(
                        DateTimeFormatter.ofPattern("MMMM d, yyyy")
                ).toString()
        );

        holder.sw_tracks_popularity.setText(String.valueOf(
                new DecimalFormat("###,###,###,###").format(ls_rTracks.get(position).getTrackPopularity()))
        );

        LocalTime len = ls_rTracks.get(position).getTrackLen();

        if(len.isAfter( LocalTime.of(0,59,59) )) {
            holder.sw_tracks_duration.setText(
                    len.format(DateTimeFormatter.ofPattern("H:mm:ss")).toString()
            );
        } else {
            holder.sw_tracks_duration.setText(
                    len.format(DateTimeFormatter.ofPattern("m:ss")).toString()
            );
        }
    }

    @Override
    public int getItemCount() {
        return ls_rTracks.size();
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
