package com.example.spotifywrapped.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.accountscreen.CreateAccountActivity;
import com.example.spotifywrapped.accountscreen.LoginActivity;
import com.example.spotifywrapped.navbar.NavbarClass;

public class Settings extends AppCompatActivity {

    public EditText newPassword;
    public Button updatePassword_btn;
    public Button logOut_btn;
    public Button deleteAccount_btn;
    public Button clearAccountData_btn;
    public RelativeLayout loadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        NavbarClass.initializeNavbar(this);
        newPassword = findViewById(R.id.newPassword_input);

        updatePassword_btn = findViewById(R.id.updatePassword_btn);
        logOut_btn = findViewById(R.id.logOut_btn);
        deleteAccount_btn = findViewById(R.id.deleteAccount_btn);
        clearAccountData_btn = findViewById(R.id.clearAccountData_btn);

        loadingScreen = findViewById(R.id.loadingScreen);
        loadingScreen.setVisibility(View.INVISIBLE);

        updatePassword_btn.setOnClickListener(v -> {
            if(newPassword.getText().toString().length() >= 6) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setMessage("Are you sure you want to update your password?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            loadingScreen.setVisibility(View.VISIBLE);
                            DatabaseManager.updateAccountPassword(newPassword.getText().toString(), this);
                            loadingScreen.setVisibility(View.INVISIBLE);
                            dialogInterface.dismiss();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        })
                        .show();
            } else {
                if(newPassword.getText().toString().length() == 0)
                    Toast.makeText(this, "Please enter a valid string in the field.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "New password is too short! Please enter a new one.", Toast.LENGTH_SHORT).show();
            }
        });

        logOut_btn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        DatabaseManager.logOut();
                        Intent myIntent = new Intent(this, LoginActivity.class);
                        this.startActivity(myIntent);
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();
        });

        deleteAccount_btn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                .setMessage("Are you sure you want to clear account data?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    loadingScreen.setVisibility(View.VISIBLE);
                    DatabaseManager.deleteUser(this);
                    loadingScreen.setVisibility(View.INVISIBLE);
                    dialogInterface.dismiss();
                    Intent myIntent = new Intent(this, LoginActivity.class);
                    this.startActivity(myIntent);
                })
                .setNegativeButton("No", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
        });

        clearAccountData_btn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setMessage("Are you sure you want to clear account data?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        loadingScreen.setVisibility(View.VISIBLE);
                        DatabaseManager.deleteSpotifyWrapListForUser(this);
                        loadingScreen.setVisibility(View.INVISIBLE);
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();
        });

    }
}