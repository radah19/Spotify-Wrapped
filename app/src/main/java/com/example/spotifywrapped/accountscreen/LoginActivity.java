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
            Button loginButton = findViewById(R.id.login_button);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateCredentials(emailInput.getText().toString(), passwordInput.getText().toString());
                }
            });
        }

        private void validateCredentials(String username, String password) {
            // Check if the credentials are valid.
            if (emailInput.equals(username) && passwordInput.equals(password)) {
                // Credentials are valid.
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Proceed with logging in the user or navigating to the next screen.
            } else {
                // Credentials are invalid.
                Toast.makeText(LoginActivity.this, "Incorrect Email or Password. Try again!", Toast.LENGTH_LONG).show();
            }
        }

    }
