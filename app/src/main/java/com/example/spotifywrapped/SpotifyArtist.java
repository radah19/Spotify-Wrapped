package com.example.spotifywrapped;

import java.util.List;

public class SpotifyArtist {
    private List<String> artistGenres;
    private String artistName, artistLink, artistImageLink, id;
    private int artistPopularity, artistFollowers;

    public SpotifyArtist(String id, List<String> artistGenres, String artistName, String artistLink, String artistImageLink, int artistPopularity, int artistFollowers) {
        this.id = id;
        this.artistGenres = artistGenres;
        this.artistName = artistName;
        this.artistLink = artistLink;
        this.artistImageLink = artistImageLink;
        this.artistPopularity = artistPopularity;
        this.artistFollowers = artistFollowers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getArtistImageLink() {
        return artistImageLink;
    }

    public void setArtistImageLink(String artistImageLink) {
        this.artistImageLink = artistImageLink;
    }
}
