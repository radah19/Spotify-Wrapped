package com.example.spotifywrapped.useraccounts;

import android.content.Intent;

import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static String username;
    public static List<String> myFriends;

    public static String email;
    private static String password;

    public static ArrayList<String> friendsList;

    private static String spotifyUserId;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public static void setFriendsList(ArrayList<String> friendsList) {
        User.friendsList = friendsList;
    }

    public static String getSpotifyUserId() {
        return spotifyUserId;
    }

    public static void setSpotifyUserId(String spotifyUserId) {
        User.spotifyUserId = spotifyUserId;
    }
}
