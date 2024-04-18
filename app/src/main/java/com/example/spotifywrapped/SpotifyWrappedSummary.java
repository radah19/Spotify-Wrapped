package com.example.spotifywrapped;

import java.time.LocalDateTime;
import java.util.List;
import java.time.Month;

public class SpotifyWrappedSummary {
    private String id;
    private String createdBy;
    private String title;
    private LocalDateTime createdAt;
    private List<String> invitedUsers;
    public List<SpotifyTrack> topTracks, trackRecommendations;
    public List<SpotifyArtist> topArtists, artistRecommendations;
    public List<String> topGenres;
    private String isChristmasHoliday;
    private String isHalloween;

    //Filters & Customizations
    public LocalDateTime startTime, endTime;

    public SpotifyWrappedSummary(String id, String createdBy, String title, LocalDateTime createdAt, List<String> invitedUsers, List<SpotifyTrack> topTracks, List<SpotifyTrack> trackRecommendations, List<SpotifyArtist> topArtists, List<SpotifyArtist> artistRecommendations, List<String> topGenres, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.createdBy = createdBy;
        this.title = title;
        this.createdAt = createdAt;
        this.invitedUsers = invitedUsers;
        this.topTracks = topTracks;
        this.trackRecommendations = trackRecommendations;
        this.topArtists = topArtists;
        this.artistRecommendations = artistRecommendations;
        this.topGenres = topGenres;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isChristmasHoliday = "isNotChristmas";
        this.isHalloween = "isNotHalloween";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<String> getInvitedUsers() {
        return invitedUsers;
    }

    public void setInvitedUsers(List<String> invitedUsers) {
        this.invitedUsers = invitedUsers;
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

    public String isChristmasHoliday() {
        return isChristmasHoliday;
    }
    public void setChristmasHoliday(String isChristmasHoliday) {
        this.isChristmasHoliday = isChristmasHoliday;
    }
    public String isHalloween(){ return isHalloween;}
    public void setIsHalloween(String isHalloween) {
        this.isHalloween = isHalloween;
    }

}
