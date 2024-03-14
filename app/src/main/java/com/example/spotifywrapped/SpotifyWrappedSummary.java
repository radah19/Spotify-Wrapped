package com.example.spotifywrapped;

import com.example.spotifywrapped.SpotifyTrack;

import java.time.LocalDateTime;
import java.util.List;

public class SpotifyWrappedSummary {
    private String createdBy;
    private List<String> invitedUsers;
    private List<SpotifyTrack> tracks;
    private String title;
    private LocalDateTime createdAt;

    //Filters & Customizations
    public LocalDateTime startTime, endTime;
    public List<String> filteredGenres, filteredArtists, filteredTracks;

    public SpotifyWrappedSummary(String createdBy, List<String> invitedUsers, List<SpotifyTrack> tracks, String title, LocalDateTime createdAt) {
        this.createdBy = createdBy;
        this.invitedUsers = invitedUsers;
        this.tracks = tracks;
        this.title = title;
        this.createdAt = createdAt;
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

    public List<SpotifyTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<SpotifyTrack> tracks) {
        this.tracks = tracks;
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
}
