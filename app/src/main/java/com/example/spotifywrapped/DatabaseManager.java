
package com.example.spotifywrapped;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;
import com.example.spotifywrapped.useraccounts.User;
import com.google.android.gms.common.PackageVerificationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DatabaseManager {

    private static FirebaseAuth mAuth;
    private static FirebaseDatabase rootNode;
    private static DatabaseReference reference;

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
    } // registerUser

    public static boolean isLoggedin() {
        if(mAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        } // if
    } // isLoggedin

    public static boolean inputVerification(String email, String password, Activity activity) {
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

    public static void loginUser(String email, String password, Activity activity) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(activity, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity, SpotifyWrappedListActivity.class);
                activity.startActivity(myIntent);
                // need to switch activities and also call finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Login Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    } // loginUser

    public static void logOut() {
        FirebaseAuth.getInstance().signOut();
    } // logOut

    public static void initializeUserTree() {
        // reference = rootNode.getReference("Users");
    } // initializeUserTree

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
                int spotifyUserId = snapshot.child("SpotifyUserID").getValue(Integer.class);
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

    public static void addSpotifyWrapped(int id, String createdBy, List<String> invitedUsers,
                                         List<Integer> tracks, String title, Date createdOn,
                                         List<Integer> topArtists, List<Integer> topTracks, List<String> topGenres,
                                         List<Integer> trackRecommendations) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("Created by", createdBy);
        map.put("Invited users", invitedUsers);
        map.put("Tracks", tracks);
        map.put("Title", title);
        map.put("Created on", createdOn);
        map.put("Top Artists", topArtists);
        map.put("Top Tracks", topTracks);
        map.put("Top Genres", topGenres);
        map.put("Recommended Tracks", trackRecommendations);
        FirebaseDatabase.getInstance().getReference().child("Spotify Wrapped").child(String.valueOf(id)).setValue(map);
    } // addSpotifyWrapped

    public static void retrieveSpotifyWrapped(String username, Activity activity) {
        reference = FirebaseDatabase.getInstance().getReference().child("Spotify Wrapped").child(username);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*
                User.setUsername(username);
                User.setPassword(snapshot.child("Password").getValue().toString());
                Toast.makeText(activity, User.getPassword(), Toast.LENGTH_SHORT).show();
                int spotifyUserId = snapshot.child("SpotifyUserID").getValue(Integer.class);
                User.setSpotifyUserId(spotifyUserId);
                Toast.makeText(activity, String.valueOf(User.getSpotifyUserId()), Toast.LENGTH_SHORT).show();
                User.setFriendsList(((ArrayList<String>)snapshot.child("Friends List").getValue()));
                Toast.makeText(activity, User.getFriendsList().get(0), Toast.LENGTH_SHORT).show();
                Toast.makeText(activity, User.getFriendsList().get(1), Toast.LENGTH_SHORT).show();
                 */
                String createdBy = snapshot.child("Created by").getValue().toString();
                ArrayList<String> invitedUsers =  (ArrayList<String>)snapshot.child("Invited users").getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    } // retrieveSpotifyWrapped



} // DatabaseManager
