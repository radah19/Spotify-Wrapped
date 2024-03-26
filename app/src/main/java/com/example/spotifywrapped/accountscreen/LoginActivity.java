package com.example.spotifywrapped.accountscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;
import com.example.spotifywrapped.useraccounts.User;

public class LoginActivity extends AppCompatActivity {

        private EditText emailInput;
        private EditText passwordInput;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            Button signUpButton = findViewById(R.id.sign_up_button);
            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start CreateAccount activity
                    Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startActivity(intent);
                }
            });

            emailInput = findViewById(R.id.email_input);
            passwordInput = findViewById(R.id.password_input);
            Button loginButton = findViewById(R.id.createAccount_btn);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateCredentials(emailInput.getText().toString(), passwordInput.getText().toString());
                }
            });
        }

        private void validateCredentials(String username, String password) {
            String trueUsername = "";
            String truePassword = "";

            //Retrieve Correct Credentials from Database
            
            // Check if the credentials are valid.
            if (trueUsername.equals(username) && truePassword.equals(password)) {
                // Credentials are valid.
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Get Spotify Login Using SpotifyAPIManager & dump into User class
                User.setAccessCode("");
                User.setAccessToken("");

                // Dump data into User class
                User.username = emailInput.getText().toString();
                // Proceed with logging in the user or navigating to the next screen.
                Intent myIntent = new Intent(this, SpotifyWrappedListActivity.class);
                this.startActivity(myIntent);
            } else {
                // Credentials are invalid.
                Toast.makeText(LoginActivity.this, "Incorrect Email or Password. Try again!", Toast.LENGTH_LONG).show();
            }
        }

    }
