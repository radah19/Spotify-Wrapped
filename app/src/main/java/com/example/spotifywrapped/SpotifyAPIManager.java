package com.example.spotifywrapped;

import android.os.NetworkOnMainThreadException;

import com.example.spotifywrapped.useraccounts.User;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

    public static SpotifyTrack loadSpotifyTrackById(String id) {
        String url = "https://api.spotify.com/v1/tracks/" + id;
        String data = makeRequest(url);

        if(!data.equals("TRANSACTION FAILED")) {
            try {
                JSONObject json = new JSONObject(data);

                SpotifyTrack track = createSpotifyTrackFromJson(json);

                return track;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static SpotifyTrack createSpotifyTrackFromJson(JSONObject json) throws JSONException {
        return new SpotifyTrack(
                json.getString("id"),
                json.getJSONArray("artists").getJSONObject(0).getString("name"),
                json.getString("name"),
                json.getJSONObject("external_urls").getString("spotify"),
                json.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url"),
                SpotifyTrack.generateTrackLengthFromInt(json.getInt("duration_ms")),
                LocalDate.parse(
                        json.getJSONObject("album").getString("release_date"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                ),
                json.getInt("popularity")
        );
    }

    public static SpotifyArtist loadSpotifyArtistById(String id) {
        String url = "https://api.spotify.com/v1/artists/" + id;
        String data = makeRequest(url);

        if(!data.equals("TRANSACTION FAILED")) {
            try {
                JSONObject json = new JSONObject(data);

                SpotifyArtist artist = createSpotifyArtistFromJson(json);

                return artist;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static SpotifyArtist createSpotifyArtistFromJson(JSONObject json) throws JSONException {
        return new SpotifyArtist(
                json.getString("id"),
                SpotifyAPIManager.convertJsonArrToStringList(json.getJSONArray("genres")),
                json.getString("name"),
                json.getJSONObject("external_urls").getString("spotify"),
                json.getJSONArray("images").getJSONObject(0).getString("url"),
                json.getInt("popularity"),
                json.getJSONObject("followers").getInt("total")
        );
    }

    public static SpotifyWrappedSummary generateSpotifyWrapped(String title, String term, List<String> invitedUsers) {
        List<SpotifyTrack> topTracks = new ArrayList<>();
        List<SpotifyTrack> recommendedTracks = new ArrayList<>();
        List<SpotifyArtist> topArtists = new ArrayList<>();
        List<SpotifyArtist> recommendedArtists = new ArrayList<>();
        List<String> topTrackIds = new ArrayList<>();
        List<String> rTrackIds = new ArrayList<>();
        List<String> topArtistIds = new ArrayList<>();
        List<String> rArtistIds = new ArrayList<>();
        HashSet<String> topGenres = new HashSet<>();

        String dataHolder;

        try {
            //Get User's Top Tracks --------------------------------------------------------
            if (!(term.equals("medium_range") || term.equals("short_term") || term.equals("long_term"))) {
                term = "medium_range";
            }



            dataHolder = makeRequest("https://api.spotify.com/v1/me/top/tracks?" +
                    "time_range=" + term + "&limit=20");

            if(!dataHolder.equals("TRANSACTION FAILED")) {
                JSONArray topTracksData = new JSONObject(dataHolder).getJSONArray("items");

                while (topTracksData.length() > 0){
                    SpotifyTrack t = createSpotifyTrackFromJson(topTracksData.getJSONObject(0));
                    topTracks.add(t);
                    topTrackIds.add(t.getId());
                    topTracksData.remove(0);
                }
            }

            //Get User's Top Artists & Genres ----------------------------------------------------
            dataHolder = makeRequest("https://api.spotify.com/v1/me/top/artists?" +
                    "time_range=" + term + "&limit=20");

            if(!dataHolder.equals("TRANSACTION FAILED")) {
                JSONArray topArtistsData = new JSONObject(dataHolder).getJSONArray("items");

                while (topArtistsData.length() > 0){
                    SpotifyArtist a = createSpotifyArtistFromJson(topArtistsData.getJSONObject(0));
                    topArtists.add(a);
                    topArtistIds.add(a.getId());
                    topGenres.addAll(a.getArtistGenres());
                    topArtistsData.remove(0);
                }
            }

            //Get User's Recommended Tracks -------------------------------------------------------------------
            StringBuilder rTracksStr = new StringBuilder("https://api.spotify.com/v1/recommendations?limit=20&seed_genres=");
            for(String s: topGenres) rTracksStr.append(s + "%2C");
            rTracksStr.replace(rTracksStr.length()-3, rTracksStr.length(), "&seed_tracks=");
            rTracksStr.append(topTracks.get(0) + "&seed_artists=");
            rTracksStr.append(topArtists.get(0));
            dataHolder = makeRequest(rTracksStr.toString());

            if(!dataHolder.equals("TRANSACTION FAILED")) {
                JSONArray rTracksData = new JSONObject(dataHolder).getJSONArray("tracks");

                while (rTracksData.length() > 0){
                    SpotifyTrack t = createSpotifyTrackFromJson(rTracksData.getJSONObject(0));
                    recommendedTracks.add(t);
                    rTrackIds.add(t.getId());
                    rTracksData.remove(0);
                }
            }

            //Get User's Recommended Artists ----------------------------------------------------------------
            for(int i = 0; i < 3; i++){
                if(i < topArtistIds.size()){
                    dataHolder = makeRequest("https://api.spotify.com/v1/artists/" + topArtistIds.get(i) + "/related-artists");

                    if(!dataHolder.equals("TRANSACTION FAILED")) {
                        JSONArray topArtistsData = new JSONObject(dataHolder).getJSONArray("artists");

                        while (topArtistsData.length() > 0){
                            recommendedArtists.add(createSpotifyArtistFromJson(topArtistsData.getJSONObject(0)));
                            topArtistsData.remove(0);
                        }
                    }
                }
            }

            for(SpotifyArtist a: recommendedArtists) rArtistIds.add(a.getId());

            return new SpotifyWrappedSummary(
                    0,
                    User.getUsername(),
                    title,
                    LocalDateTime.now(),
                    invitedUsers,
                    topTracks,
                    recommendedTracks,
                    topArtists,
                    recommendedArtists,
                    new ArrayList<String>(topGenres),
                    LocalDateTime.now().minusDays(1),
                    LocalDateTime.now()
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
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

        while(mResponse == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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

    public static List<String> convertJsonArrToStringList(JSONArray arr) throws JSONException {
        List<String> ls = new ArrayList<>();

        for(int i = 0; i < arr.length(); i++){
            ls.add((String) arr.get(i));
        }

        return ls;
    }
}