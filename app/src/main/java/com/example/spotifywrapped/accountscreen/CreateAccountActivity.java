package com.example.spotifywrapped.accountscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;

public class CreateAccountActivity extends AppCompatActivity {

    public EditText email, password, username;
    public Button createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.email_input);
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        createAccount = findViewById(R.id.login_button);

        String email_input = email.getText().toString();
        String username_input = username.getText().toString();
        String password_input = password.toString();


        createAccount.setOnClickListener((View v) -> {
            DatabaseManager.setFirebaseAuth();
            DatabaseManager.createNewAccount(email_input, password_input, CreateAccountActivity.this);
        });
    }
}