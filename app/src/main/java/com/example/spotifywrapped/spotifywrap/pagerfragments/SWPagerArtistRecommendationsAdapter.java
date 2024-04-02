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
import com.example.spotifywrapped.SpotifyArtist;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SWPagerArtistRecommendationsAdapter extends RecyclerView.Adapter<SWPagerArtistRecommendationsAdapter.MyViewHolder>{
    private List<SpotifyArtist> ls_artists;
    private Context context;

    public SWPagerArtistRecommendationsAdapter(List<SpotifyArtist> ls_artists, Context context) {
        this.ls_artists = ls_artists;
        this.context = context;
    }

    @NonNull
    @Override
    public SWPagerArtistRecommendationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spotify_wrap_pager_rartists_item,parent,false);
        SWPagerArtistRecommendationsAdapter.MyViewHolder holder = new SWPagerArtistRecommendationsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SWPagerArtistRecommendationsAdapter.MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    ls_artists.get(position).getArtistLink()
            ));
            context.startActivity(browserIntent);
        });

        Picasso.get().load(ls_artists.get(position).getArtistImageLink()).into(holder.sw_artists_img);
        holder.sw_artists_name.setText(ls_artists.get(position).getArtistName());

        StringBuilder genresStr = new StringBuilder();
        for(int i = 0; i < 5; i++){
            if (i < ls_artists.get(position).getArtistGenres().size()) {
                genresStr.append(ls_artists.get(position).getArtistGenres().get(i));

                if (i != 4 && i != ls_artists.get(position).getArtistGenres().size() - 1) {
                    genresStr.append(", ");
                }
                if (i == 4 && ls_artists.get(position).getArtistGenres().size() > 5) {
                    genresStr.append("...");
                }
            }
        }
        holder.sw_artists_genres.setText(genresStr);

        holder.sw_artists_popularity.setText(
                new DecimalFormat("###,###,###,###").format(ls_artists.get(position).getArtistPopularity())
        );
        holder.sw_artists_followers.setText(
                new DecimalFormat("###,###,###,###").format(ls_artists.get(position).getArtistFollowers())
        );
    }

    @Override
    public int getItemCount() {
        return this.ls_artists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sw_artists_img;
        TextView sw_artists_name, sw_artists_genres;
        TextView sw_artists_popularity, sw_artists_followers;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sw_artists_img = itemView.findViewById(R.id.sw_artists_img);
            sw_artists_name = itemView.findViewById(R.id.sw_artists_artistName);
            sw_artists_genres = itemView.findViewById(R.id.sw_artists_genres);

            sw_artists_popularity = itemView.findViewById(R.id.sw_artists_popularity);
            sw_artists_followers = itemView.findViewById(R.id.sw_artists_followers);
        }
    } // MyViewHolder
}
