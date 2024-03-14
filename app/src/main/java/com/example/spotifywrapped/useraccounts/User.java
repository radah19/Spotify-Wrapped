package com.example.spotifywrapped.useraccounts;

import android.content.Intent;

import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.List;

public class User {
    public static String username;
    private static String accessCode, accessToken;
    public static List<String> myFriends;

    public static String getAccessCode() {
        return accessCode;
    }

    public static void setAccessCode(String accessCode) {
        User.accessCode = accessCode;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        User.accessToken = accessToken;
    }


}
