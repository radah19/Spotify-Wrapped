package com.example.spotifywrapped.accountscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotifywrapped.DatabaseManager;
import com.example.spotifywrapped.R;
import com.example.spotifywrapped.spotifywrappedlist.SpotifyWrappedListActivity;

public class CreateAccountActivity extends AppCompatActivity {

    public EditText email, password;
    public Button createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        createAccount = findViewById(R.id.createAccount_btn);

        createAccount.setOnClickListener((View v) -> {
            String email_input = email.getText().toString();
            String password_input = password.getText().toString();

            DatabaseManager.setFirebaseAuth();
            if (DatabaseManager.createAccountVerification(email_input, password_input, CreateAccountActivity.this) == true) {
                Intent myIntent = new Intent(this, LoginActivity.class);
                this.startActivity(myIntent);
            } // if

        });
    }
}