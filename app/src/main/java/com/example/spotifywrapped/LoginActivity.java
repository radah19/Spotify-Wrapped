package com.example.spotifywrapped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

        private EditText usernameInput;
        private EditText passwordInput;

        private final user validUser = new user("admin", "admin123");

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

            usernameInput = findViewById(R.id.username_input);
            passwordInput = findViewById(R.id.password_input);
            Button loginButton = findViewById(R.id.login_button);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateCredentials(usernameInput.getText().toString(), passwordInput.getText().toString());
                }
            });
        }

        private void validateCredentials(String username, String password) {
            // Check if the credentials are valid.
            if (usernameInput.equals(username) && passwordInput.equals(password)) {
                // Credentials are valid.
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Proceed with logging in the user or navigating to the next screen.
            } else {
                // Credentials are invalid.
                Toast.makeText(LoginActivity.this, "Incorrect Password or Username", Toast.LENGTH_LONG).show();
            }
        }

    }
