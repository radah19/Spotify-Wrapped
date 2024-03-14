package com.example.spotifywrapped;

import java.util.List;

public class SpotifyArtist {
    private List<String> artistGenres;
    private String artistName, artistLink;
    private int artistPopularity, artistFollowers;

    public SpotifyArtist(List<String> artistGenres, String artistName, String artistLink, int artistPopularity, int artistFollowers) {
        this.artistGenres = artistGenres;
        this.artistName = artistName;
        this.artistLink = artistLink;
        this.artistPopularity = artistPopularity;
        this.artistFollowers = artistFollowers;
    }

    public List<String> getArtistGenres() {
        return artistGenres;
    }

    public void setArtistGenres(List<String> artistGenres) {
        this.artistGenres = artistGenres;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistLink() {
        return artistLink;
    }

    public void setArtistLink(String artistLink) {
        this.artistLink = artistLink;
    }

    public int getArtistPopularity() {
        return artistPopularity;
    }

    public void setArtistPopularity(int artistPopularity) {
        this.artistPopularity = artistPopularity;
    }

    public int getArtistFollowers() {
        return artistFollowers;
    }

    public void setArtistFollowers(int artistFollowers) {
        this.artistFollowers = artistFollowers;
    }
}
