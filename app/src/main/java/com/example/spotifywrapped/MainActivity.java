package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spotifywrapped.accountscreen.LoginActivity;
import com.example.spotifywrapped.spotifywrap.SpotifyWrapActivity;
import com.example.spotifywrapped.friendslist.FriendListActivity;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = new Intent(this, SpotifyWrapActivity.class);
        this.startActivity(myIntent);
    }
}