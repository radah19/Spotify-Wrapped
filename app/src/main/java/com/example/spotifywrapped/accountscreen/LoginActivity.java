package com.example.spotifywrapped.accountscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotifywrapped.R;
import com.example.spotifywrapped.useraccounts.User;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1337; // Any arbitrary number for the request code
    private static final String CLIENT_ID = "your_client_id_here";
    private static final String REDIRECT_URI = "your_redirect_uri_here";
    private static final String CLIENT_SECRET = "your_client_secret_here";

    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(v -> {
            // Start CreateAccount activity
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            // Consider validating the email and password before proceeding
            initiateSpotifyLogin();
        });
    }

    private void initiateSpotifyLogin() {
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"}); // Define your scopes here
        AuthorizationRequest request = builder.build();

        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);
            switch (response.getType()) {
                case TOKEN:
                    // Use the token to make requests on behalf of the user
                    String accessToken = response.getAccessToken();
                    proceedWithLoggedInUser(accessToken);
                    break;

                case ERROR:
                    // Handle error response
                    Toast.makeText(this, "Authentication error: " + response.getError(), Toast.LENGTH_LONG).show();
                    break;

                default:
                    // Most likely auth flow was cancelled
                    Toast.makeText(this, "Authentication cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void proceedWithLoggedInUser(String accessToken) {
        // Here you can update your User class with the access token and proceed
        User.setAccessToken(accessToken);

        // Navigate to the next screen or perform further user setup
        //Intent myIntent = new Intent(this, SpotifyWrappedListActivity?.class);
        //startActivity(myIntent);
    }
}
