package com.example.spotifywrapped.navbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.accountscreen.LoginActivity;
import com.example.spotifywrapped.friendslist.FriendListActivity;
import com.example.spotifywrapped.settings.Settings;
import com.example.spotifywrapped.spotifywrap.SpotifyWrapActivity;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;

public class NavbarClass {
    public static void initializeNavbar(Activity a){
        // Spotify Wrap Bar
        a.findViewById(R.id.navbarSpotifyWraps).setOnClickListener(view -> {
            if(a.getClass() != SpotifyWrappedListActivity.class) {
                Intent myIntent = new Intent(a, SpotifyWrappedListActivity.class);
                a.startActivity(myIntent);
            }
        });

        // Settings
        a.findViewById(R.id.navbarSettings).setOnClickListener(view -> {
            if(a.getClass() != Settings.class) {
                Intent myIntent = new Intent(a, Settings.class);
                a.startActivity(myIntent);
            }
        });

        // Log Out
//        a.findViewById(R.id.navbarLogout).setOnClickListener(view -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(a);
//            builder
//                    .setMessage("Are you sure you want to log out?")
//                    .setPositiveButton("Yes", (dialogInterface, i) -> {
//                        Intent myIntent = new Intent(a, LoginActivity.class);
//                        a.startActivity(myIntent);
//                        dialogInterface.dismiss();
//                    })
//                    .setNegativeButton("No", (dialogInterface, i) -> {
//                        dialogInterface.dismiss();
//                    })
//                    .show();
//        });

    }
}