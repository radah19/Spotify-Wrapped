
package com.example.spotifywrapped;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.spotifywrapped.accountscreen.LoginActivity;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;
import com.example.spotifywrapped.useraccounts.User;
import com.google.android.gms.common.PackageVerificationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DatabaseManager {

    private static FirebaseAuth mAuth;
    private static FirebaseDatabase rootNode;
    private static DatabaseReference reference;
    private static OkHttpClient mOkHttpClient;
    private static Call mCall;
    private static String mResponse;

    private static String PROJECT_ID = "spotifywrapped-846f0-default-rtdb";

    public DatabaseManager() {
    } // DatabaseManager

    public static void setFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
    } // setFirebaseAuth

    public static void createNewAccount(String username, String password, Activity activity) {
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(activity, "Registering user successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Registration failed!", Toast.LENGTH_SHORT).show();
                } // if
            }
        });
        User.setUsername(username);
    } // registerUser

    public static boolean isLoggedin() {
        if(mAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        } // if
    } // isLoggedin

    public static boolean createAccountVerification(String email, String password, Activity activity) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(activity, "Empty credentials!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6){
            Toast.makeText(activity, "Password too short!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            createNewAccount(email, password, activity);
            return true;
        } // if
    } // inputVerification

    public static boolean loginVerification(String email, String password, LoginActivity activity) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(activity, "Empty credentials!", Toast.LENGTH_SHORT).show();
            activity.loadingScreen.setVisibility(View.INVISIBLE);
            return false;
        } else {
            loginUser(email, password, activity);
            return true;
        } // if
    } // inputVerification
    public static void loginUser(String email, String password, LoginActivity activity) {
        User.setEmail(email);
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(activity, "Login Successful!", Toast.LENGTH_SHORT).show();
                activity.initiateSpotifyLogin();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Login Failed!", Toast.LENGTH_SHORT).show();
                activity.loadingScreen.setVisibility(View.INVISIBLE);
            }
        });
    } // loginUser

    public static void logOut() {
        FirebaseAuth.getInstance().signOut();
    } // logOut

    public static void addUser(String username, String password, ArrayList<String> friendsList, int spotifyUserID) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Password", password);
        map.put("Friends List", friendsList);
        map.put("SpotifyUserID", spotifyUserID);
        FirebaseDatabase.getInstance().getReference().child("Users").child(username).setValue(map);
    } // addUser

    public static void retrieveUser(String username, Activity activity) {
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User.setUsername(username);
                User.setPassword(snapshot.child("Password").getValue().toString());
                Toast.makeText(activity, User.getPassword(), Toast.LENGTH_SHORT).show();
                String spotifyUserId = snapshot.child("SpotifyUserID").getValue().toString();
                User.setSpotifyUserId(spotifyUserId);
                Toast.makeText(activity, String.valueOf(User.getSpotifyUserId()), Toast.LENGTH_SHORT).show();
                User.setFriendsList(((ArrayList<String>)snapshot.child("Friends List").getValue()));
                Toast.makeText(activity, User.getFriendsList().get(0), Toast.LENGTH_SHORT).show();
                Toast.makeText(activity, User.getFriendsList().get(1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    } // retrieveUser

    public static void addSpotifyWrapped(SpotifyWrappedSummary s) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Created by", s.getCreatedBy());
        //map.put("Invited users", s.getInvitedUsers());;
        map.put("Title", s.getTitle());
        map.put("Created on", s.getCreatedAt().toString());
        map.put("Top Artists", s.topArtists.stream().map(SpotifyArtist::getId).collect(Collectors.toList()));
        map.put("Top Tracks", s.topTracks.stream().map(SpotifyTrack::getId).collect(Collectors.toList()));
        map.put("Top Genres", s.topGenres);
        map.put("Recommended Tracks", s.trackRecommendations.stream().map(SpotifyTrack::getId).collect(Collectors.toList()));
        map.put("Recommended Artists", s.artistRecommendations.stream().map(SpotifyArtist::getId).collect(Collectors.toList()));
        map.put("Start Time", s.startTime.toString());
        map.put("End Time", s.endTime.toString());
        map.put("Theme", s.isHoliday().toString());
        FirebaseDatabase.getInstance().getReference().child("Spotify Wrapped").child(s.getId()).setValue(map);
    } // addSpotifyWrapped


    public static String generateFirebaseApiRequest(String s){
        if(mOkHttpClient == null){
            mOkHttpClient = new OkHttpClient();
        }

        Request request = new Request.Builder()
                .url("https://" + PROJECT_ID + ".firebaseio.com/" + s + ".json").build();
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
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return mResponse;
    }

    public static SpotifyWrappedSummary loadSpotifyWrappedById(String id){
        try {
            JSONObject data = new JSONObject(generateFirebaseApiRequest("Spotify Wrapped/" + id));

            List<SpotifyTrack> lsTracks = new ArrayList<>(), lsRTracks = new ArrayList<>();
            List<SpotifyArtist> lsArtists = new ArrayList<>(), lsRArtists = new ArrayList<>();
            boolean hasFriends = false;

            try {
                data.getJSONArray("Invited users");
                hasFriends = true;
            } catch (JSONException e) {
                hasFriends = false;
            }

            for(String i: SpotifyAPIManager.convertJsonArrToStringList(data.getJSONArray("Top Tracks"))){
                lsTracks.add(SpotifyAPIManager.loadSpotifyTrackById(i));
            }
            for(String i: SpotifyAPIManager.convertJsonArrToStringList(data.getJSONArray("Recommended Tracks"))){
                lsRTracks.add(SpotifyAPIManager.loadSpotifyTrackById(i));
            }
            for(String i: SpotifyAPIManager.convertJsonArrToStringList(data.getJSONArray("Top Artists"))){
                lsArtists.add(SpotifyAPIManager.loadSpotifyArtistById(i));
            }
            for(String i: SpotifyAPIManager.convertJsonArrToStringList(data.getJSONArray("Recommended Artists"))){
                lsRArtists.add(SpotifyAPIManager.loadSpotifyArtistById(i));
            }

            String theme;
            try {
                theme = data.getString("Theme").toString();
            } catch (JSONException e) {
                theme = "No Holiday";
            }

            return new SpotifyWrappedSummary(
                    id,
                    data.getString("Created by").toString(),
                    data.getString("Title").toString(),
                    LocalDateTime.parse(data.getString("Created on").toString()),
                    lsTracks ,
                    lsRTracks ,
                    lsArtists,
                    lsRArtists,
                    SpotifyAPIManager.convertJsonArrToStringList(data.getJSONArray("Top Genres")),
                    LocalDateTime.parse(data.getString("Start Time").toString()),
                    LocalDateTime.parse(data.getString("End Time").toString()),
                    theme
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<SpotifyWrappedSummary> loadSpotifyWrapListForUser(){
        String user = User.getEmail();
        List<SpotifyWrappedSummary> ls = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(generateFirebaseApiRequest("Spotify Wrapped"));
            Iterator<String> i = data.keys();
            while(i.hasNext()){
                String id = i.next();
                String userId = generateFirebaseApiRequest("Spotify Wrapped/" + id + "/Created by").replace("\"", "");
                if(userId.equals(user)){
                    ls.add(loadSpotifyWrappedById(id));
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return ls;
    }

    public static void addCurrentUser(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("User", username);
        FirebaseDatabase.getInstance().getReference().child("Current Users").child(username).setValue(map);
    } // addCurrentUser

    public static void updateAccountPassword(String password, Activity activity) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser.updatePassword(password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(activity, "Successfully updated password!", Toast.LENGTH_SHORT).show();
            }
        });
    } // updateAccountPassword

    public static void deleteUserFromList(Activity activity) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        // now change the ind friends list
        // loop thru users, loop trhus friends list, add new list

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()) {
                    String user = data.getValue().toString();
                    ArrayList<String> friendsList = (ArrayList<String>)data.child("Friends List").getValue();
                    for (int i = 0; i < friendsList.size(); i++) {
                        if (friendsList.get(i).compareTo(User.getUsername()) == 0) {
                            friendsList.remove(i);
                        } // if
                    } // for
                    reference.child(user).child("Friends List").setValue(friendsList);
                } // for
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void deleteUser(Activity activity) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(activity, "Account successfully deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    } // deleteUser

    public static void deleteSpotifyWrapListForUser(Activity activity){
        String user = User.getEmail();

        try {
            JSONObject data = new JSONObject(generateFirebaseApiRequest("Spotify Wrapped"));
            Iterator<String> i = data.keys();
            while(i.hasNext()){
                String id = i.next();
                String userId = generateFirebaseApiRequest("Spotify Wrapped/" + id + "/Created by").replace("\"", "");
                if(userId.equals(user)){
                    FirebaseDatabase.getInstance().getReference().child("Spotify Wrapped").child(id).removeValue();
                }
            }
        } catch (JSONException e) {
            Toast.makeText(activity, "Account could not be deleted, please try again later.", Toast.LENGTH_SHORT).show();
        }

        SpotifyWrappedListActivity.ls_summaries = new ArrayList<>();
        Toast.makeText(activity, "Account data has been cleared.", Toast.LENGTH_SHORT).show();
    }

    public static void deleteSpotifyWrapById(String id){
        FirebaseDatabase.getInstance().getReference().child("Spotify Wrapped").child(id).removeValue();
    }
} // DatabaseManager
