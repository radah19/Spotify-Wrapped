package com.example.spotifywrapped.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.accountscreen.CreateAccountActivity;
import com.example.spotifywrapped.accountscreen.LoginActivity;
import com.example.spotifywrapped.navbar.NavbarClass;

public class Settings extends AppCompatActivity {

    public EditText oldPassword, newPassword;
    public Button updatePassword_btn;
    public Button logOut_btn;
    public Button clearAccountData_btn;
    public Button deleteAccount_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        NavbarClass.initializeNavbar(this);

        //updatePassword method

        oldPassword = findViewById(R.id.oldPassword_input);
        newPassword = findViewById(R.id.newPassword_input);

        String oldPassword_input = oldPassword.getText().toString();
        String newPassword_input = newPassword.getText().toString();

        //log out method

        //delete account method

        //clear data method
    }
}