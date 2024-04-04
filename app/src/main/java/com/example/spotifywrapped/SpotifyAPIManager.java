package com.example.spotifywrapped;

import android.os.NetworkOnMainThreadException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class SpotifyAPIManager {
    private static SpotifyAPIManager instance;
    private static OkHttpClient mOkHttpClient;
    private static Call mCall;
    private static String mResponse;

    private static String accessToken, accessCode;

    private SpotifyAPIManager() {
        mOkHttpClient = new OkHttpClient();
    }

    public static synchronized SpotifyAPIManager getInstance() {
        if (instance == null) {
            instance = new SpotifyAPIManager();
        }
        return instance;
    }

    public static String getUserData() {
        String url = "https://api.spotify.com/v1/me";
        return makeRequest(url);
    }

    public static List<String> getUserTopTracks() {
        String url = "https://api.spotify.com/v1/me/top/tracks";
        String response = makeRequest(url);
        // Parse JSON response to extract track names and return them.
        return new ArrayList<>(); // Placeholder for parsed data
    }

    public static List<String> getUserTopArtists() {
        String url = "https://api.spotify.com/v1/me/top/artists";
        String response = makeRequest(url);
        // Parse JSON response to extract artist names and return them.
        return new ArrayList<>(); // Placeholder for parsed data
    }

    // Note: Spotify API does not directly provide top genres data.
    // This method would require a more complex implementation.
    public static List<String> getUserTopGenres() {
        // Implementation would involve fetching top tracks or artists and aggregating genre data.
        return new ArrayList<>(); // Placeholder
    }

    public static String getSpotifyTrack(String songName) {
        String url = "https://api.spotify.com/v1/search?q=" + songName + "&type=track";
        return makeRequest(url);
    }

    private static String makeRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getAccessToken())
                .build();
        if(mCall != null) mCall.cancel();
        mCall = mOkHttpClient.newCall(request);

        mResponse = null;

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mResponse = "TRANSACTION FAILED";
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mResponse = response.body().string();
            }
        });

        while (mResponse == null || mResponse != "TRANSACTION FAILED") {
            //Don't do anything
        }

        return mResponse;
    }

    public static List<String> parseJsonData(String jsonData, String key) {
    List<String> results = new ArrayList<>();
    try {
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray itemsArray = jsonObject.getJSONArray("items");

        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.getJSONObject(i);
            String name = item.getJSONObject(key).getString("name");
            results.add(name);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
        return results;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        SpotifyAPIManager.accessToken = accessToken;
    }

    public static String getAccessCode() {
        return accessCode;
    }

    public static void setAccessCode(String accessCode) {
        SpotifyAPIManager.accessCode = accessCode;
    }
}