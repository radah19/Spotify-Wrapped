package com.example.spotifywrapped.wrappedlist;

import java.time.LocalDateTime;
import java.util.List;

public class SpotifyTrack {
    private List<String> trackGenres;
    private String trackArtist, trackName, trackLink;
    private int trackLen;
    private LocalDateTime trackPublishDate;

    public SpotifyTrack(List<String> trackGenres, String trackArtist, String trackName, String trackLink, int trackLen, LocalDateTime trackPublishDate) {
        this.trackGenres = trackGenres;
        this.trackArtist = trackArtist;
        this.trackName = trackName;
        this.trackLink = trackLink;
        this.trackLen = trackLen;
        this.trackPublishDate = trackPublishDate;
    }

    public List<String> getTrackGenres() {
        return trackGenres;
    }

    public void setTrackGenres(List<String> trackGenres) {
        this.trackGenres = trackGenres;
    }

    public String getTrackArtist() {
        return trackArtist;
    }

    public void setTrackArtist(String trackArtist) {
        this.trackArtist = trackArtist;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackLink() {
        return trackLink;
    }

    public void setTrackLink(String trackLink) {
        this.trackLink = trackLink;
    }

    public int getTrackLen() {
        return trackLen;
    }

    public void setTrackLen(int trackLen) {
        this.trackLen = trackLen;
    }

    public LocalDateTime getTrackPublishDate() {
        return trackPublishDate;
    }

    public void setTrackPublishDate(LocalDateTime trackPublishDate) {
        this.trackPublishDate = trackPublishDate;
    }
}
