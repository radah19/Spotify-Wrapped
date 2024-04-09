
package com.example.spotifywrapped;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionHelper;

import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;
import com.example.spotifywrapped.useraccounts.User;
import com.google.android.gms.common.PackageVerificationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.Length;

import java.lang.reflect.GenericArrayType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DatabaseManager {

    private static FirebaseAuth mAuth;
    private static DatabaseReference reference;

    public static ArrayList<String> otherUsers;

    public DatabaseManager() {
    } // DatabaseManager

    public static void setFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
    } // setFirebaseAuth

    public static void createNewAccount(String email, String password, String username, Activity activity) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(activity, "Registering user successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Registration failed!", Toast.LENGTH_SHORT).show();
                } // if
            }
        });
        Toast.makeText(activity, username, Toast.LENGTH_SHORT).show();
        User.setUsername(username);
    } // registerUser

    public static boolean isLoggedin() {
        if(mAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        } // if
    } // isLoggedin

    public static boolean inputVerification(String email, String password, String username, Activity activity) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(activity, "Empty credentials!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 6){
            Toast.makeText(activity, "Password too short!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            createNewAccount(email, password, username, activity);
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

    public static void addSpotifyWrapped(int id, String createdBy, ArrayList<String> invitedUsers,
                                         String title, LocalDateTime createdOn,
                                         ArrayList<Integer> topArtists, ArrayList<Integer> topTracks, ArrayList<String> topGenres,
                                         ArrayList<Integer> trackRecommendations, ArrayList<Integer> artistRecommendations) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("Created by", createdBy);
        map.put("Invited users", invitedUsers);
        map.put("Title", title);
        map.put("Created on", createdOn);
        map.put("Top Artists", topArtists);
        map.put("Top Tracks", topTracks);
        map.put("Top Genres", topGenres);
        map.put("Recommended Tracks", trackRecommendations);
        map.put("Recommended Artists", artistRecommendations);
        FirebaseDatabase.getInstance().getReference().child("Spotify Wrapped").child(String.valueOf(id)).setValue(map);
    } // addSpotifyWrapped

    public static void retrieveSpotifyWrapped(int id, Activity activity) {
        reference = FirebaseDatabase.getInstance().getReference().child("Spotify Wrapped").child(String.valueOf(id));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String createdBy = snapshot.child("Created by").getValue().toString();
                //  Toast.makeText(activity, createdBy, Toast.LENGTH_SHORT).show();

                String title = snapshot.child("Title").getValue().toString();
                //   Toast.makeText(activity, title, Toast.LENGTH_SHORT).show();

                LocalDateTime createdOn = (LocalDateTime)snapshot.child("Created on").getValue();
                //    Toast.makeText(activity, String.valueOf(createdOn), Toast.LENGTH_SHORT).show();

                ArrayList<String> invitedUsers =  (ArrayList<String>)snapshot.child("Invited users").getValue();
                //   Toast.makeText(activity, invitedUsers.get(0), Toast.LENGTH_SHORT).show();

                ArrayList<Integer> topTracks =  (ArrayList<Integer>)snapshot.child("Top Tracks").getValue();
                //  Toast.makeText(activity, String.valueOf(topTracks.get(0)), Toast.LENGTH_SHORT).show();

                ArrayList<Integer> recommendedTracks =  (ArrayList<Integer>)snapshot.child("Recommended Tracks").getValue();
                //  Toast.makeText(activity, String.valueOf(recommendedTracks.get(0)), Toast.LENGTH_SHORT).show();

                ArrayList<Integer> topArtists =  (ArrayList<Integer>)snapshot.child("Top Artists").getValue();
                //   Toast.makeText(activity, String.valueOf(topArtists.get(0)), Toast.LENGTH_SHORT).show();

                ArrayList<Integer> recommendedArtists =  (ArrayList<Integer>)snapshot.child("Recommended Artists").getValue();
                //   Toast.makeText(activity, String.valueOf(recommendedArtists.get(0)), Toast.LENGTH_SHORT).show();

                ArrayList<String> topGenres =  (ArrayList<String>)snapshot.child("Top Genres").getValue();
                //    Toast.makeText(activity, topGenres.get(0), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    } // retrieveSpotifyWrapped

    public static ArrayList<String> getOtherUsers(String username, Activity activity) {
        reference = FirebaseDatabase.getInstance().getReference().child("Current Users");
        if (DatabaseManager.otherUsers == null) {
            DatabaseManager.otherUsers = new ArrayList<>();
        } // if
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String value = dataSnapshot.child("User").getValue().toString();
                    if (value.compareTo(username) != 0) {
                        Toast.makeText(activity, value, Toast.LENGTH_SHORT).show();
                        DatabaseManager.otherUsers.add(value);
                        //Toast.makeText(activity, otherUsers.size(), Toast.LENGTH_SHORT).show();
                    } // if
                } // for
            };

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return DatabaseManager.otherUsers;
    } // getOtherUsers

    public static void addFriend(String friend, Activity activity) {
        ArrayList<String> newFriendsList = User.getFriendsList();
        newFriendsList.add(friend);
        FirebaseDatabase.getInstance().getReference().child("Users").child(User.getUsername()).child("Friends List").setValue(newFriendsList);
    } // addFriend

    public static void addCurrentUser(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("User", username);
        FirebaseDatabase.getInstance().getReference().child("Current Users").child(username).setValue(map);
    } // addCurrentUser

    public static void updateAccountPassword(String password, Activity activity) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(activity, "Successfully updated password!", Toast.LENGTH_SHORT).show();
                } // if
            }
        });
    } // updateAccountPassword

    public static void deleteUser(Activity activity) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(User.getUsername());
        reference.setValue(null);
        reference = FirebaseDatabase.getInstance().getReference().child("Current Users").child(User.getUsername());
        reference.setValue(null);
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        // now change the ind friends list
        // loop thru users, loop trhus friends list, add new list


        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(activity, "Account successfully deleted!", Toast.LENGTH_SHORT).show();
                } // if
            }
        });
// WHAT I HAVE TO DO: DEL FROM FRIENDS LIST AND USER TABLE
    } // deleteUser


} // DatabaseManager
