package com.example.spotifywrapped;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SpotifyTrack {
    private String trackArtist, trackName, trackLink, trackImageLink, id;
    private LocalTime trackLen;
    private LocalDate trackPublishDate;
    private int trackPopularity;

    public SpotifyTrack(String id, String trackArtist, String trackName, String trackLink, String trackImageLink, LocalTime trackLen, LocalDate trackPublishDate, int trackPopularity) {
        this.id = id;
        this.trackArtist = trackArtist;
        this.trackName = trackName;
        this.trackLink = trackLink;
        this.trackImageLink = trackImageLink;
        this.trackLen = trackLen;
        this.trackPublishDate = trackPublishDate;
        this.trackPopularity = trackPopularity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTrackImageLink() {
        return trackImageLink;
    }

    public void setTrackImageLink(String trackImageLink) {
        this.trackImageLink = trackImageLink;
    }

    public LocalTime getTrackLen() {
        return trackLen;
    }

    public void setTrackLen(LocalTime trackLen) {
        this.trackLen = trackLen;
    }

    /*
    *   Provided Amn should be in milliseconds
    *   Returns LocalTime Object to Provide for Spotify Wraps
    */
    public static LocalTime generateTrackLengthFromInt(int amn) {
        return LocalTime.of((amn/1000)/3600, (amn/1000)/60, (amn/1000)%60);
    }

    public LocalDate getTrackPublishDate() {
        return trackPublishDate;
    }

    public void setTrackPublishDate(LocalDate trackPublishDate) {
        this.trackPublishDate = trackPublishDate;
    }

    public int getTrackPopularity() {
        return trackPopularity;
    }

    public void setTrackPopularity(int trackPopularity) {
        this.trackPopularity = trackPopularity;
    }
}
