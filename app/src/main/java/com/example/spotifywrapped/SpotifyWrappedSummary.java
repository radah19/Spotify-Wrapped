package com.example.spotifywrapped;

import java.time.LocalDateTime;
import java.util.List;

public class SpotifyWrappedSummary {
    private String id;
    private String createdBy;
    private String title;
    private LocalDateTime createdAt;
    public List<SpotifyTrack> topTracks, trackRecommendations;
    public List<SpotifyArtist> topArtists, artistRecommendations;
    public List<String> topGenres;
    private String isHoliday;

    //Filters & Customizations
    public LocalDateTime startTime, endTime;

    public SpotifyWrappedSummary(String id, String createdBy, String title, LocalDateTime createdAt, List<SpotifyTrack> topTracks, List<SpotifyTrack> trackRecommendations, List<SpotifyArtist> topArtists, List<SpotifyArtist> artistRecommendations, List<String> topGenres, LocalDateTime startTime, LocalDateTime endTime, String theme) {
        this.id = id;
        this.createdBy = createdBy;
        this.title = title;
        this.createdAt = createdAt;
        this.topTracks = topTracks;
        this.trackRecommendations = trackRecommendations;
        this.topArtists = topArtists;
        this.artistRecommendations = artistRecommendations;
        this.topGenres = topGenres;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isHoliday = theme;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<SpotifyTrack> getTrackRecommendations() {
        return trackRecommendations;
    }

    public void setTrackRecommendations(List<SpotifyTrack> trackRecommendations) {
        this.trackRecommendations = trackRecommendations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String isHoliday() {
        return isHoliday;
    }
    public void setChristmasHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

}
