package com.example.spotifywrapped.settings;

import static com.example.spotifywrapped.DatabaseManager.deleteSpotifyWrapListForUser;
import static com.example.spotifywrapped.DatabaseManager.deleteUser;
import static com.example.spotifywrapped.DatabaseManager.logOut;
import static com.example.spotifywrapped.DatabaseManager.updateAccountPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.accountscreen.CreateAccountActivity;
import com.example.spotifywrapped.accountscreen.LoginActivity;
import com.example.spotifywrapped.navbar.NavbarClass;

public class Settings extends AppCompatActivity {

    public EditText oldPassword, newPassword;
    public Button updatePassword_btn;
    public Button logOut_btn;
    public Button deleteAccount_btn;
    public Button clearAccountData_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        NavbarClass.initializeNavbar(this);
        oldPassword = findViewById(R.id.oldPassword_input);
        newPassword = findViewById(R.id.newPassword_input);
        String oldPassword_input = oldPassword.getText().toString();
        String newPassword_input = newPassword.getText().toString();
        Toast.makeText(this, "Are you sure?", Toast.LENGTH_SHORT).show();

        updatePassword_btn.setOnClickListener(v -> {
            Toast.makeText(this, "Are you sure?", Toast.LENGTH_SHORT).show();
            updatePassword_btn.setOnClickListener(u -> {
                updateAccountPassword(newPassword_input, this);
            });
        });

        logOut_btn.setOnClickListener(v -> {
            Toast.makeText(this, "Are you sure?", Toast.LENGTH_SHORT).show();
            logOut_btn.setOnClickListener(u -> {
                logOut();
            });
        });

        deleteAccount_btn.setOnClickListener(v -> {
            Toast.makeText(this, "Are you sure?", Toast.LENGTH_SHORT).show();
            deleteAccount_btn.setOnClickListener(u -> {
                deleteUser(this);
            });
        });

        clearAccountData_btn.setOnClickListener(v -> {
            Toast.makeText(this, "Are you sure?", Toast.LENGTH_SHORT).show();
            clearAccountData_btn.setOnClickListener(u -> {
                deleteSpotifyWrapListForUser();
            });
        });

    }
}